package packinggame;

import packinggame.canvas.Canvas;
import packinggame.canvas.P2;
import packinggame.loop.LoopRequest;
import packinggame.mouse.DragHandler;

import java.awt.*;
import java.util.*;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

class Disks implements DragHandler {

  final float padding = .001f;

  LoopRequest loop_request;

  Set<Disk> stationary_disks = new HashSet<Disk>();

  List<Circle> overlap_boundaries;
  List<P2> boundary_intersections;

  Disk dragging_disk;
  P2 dragging_disk_start;

  Disk ghost_disk;

  Circle circumscribing_circle;

  void add(Disk d) {
    stationary_disks.add(d);
    circumscribe();
  }

  void clear() {
    stop_dragging();
    stationary_disks.clear();
    loop_request.loop(true);
    circumscribe();
  }

  void draw(Canvas canvas) {

    canvas.circle(circumscribing_circle.center, circumscribing_circle.radius, new Color(255, 255, 255, 127));

    for (Disk d : stationary_disks) {
      d.draw(canvas);
    }

    if (dragging_disk != null) {
      dragging_disk.draw(canvas);
      if (ghost_disk != null) {
        ghost_disk.draw(canvas);
      }
    } else {
      loop_request.loop(false);
    }

  }

  @Override public void press(P2 mouse) {
    dragging_disk = at_position(mouse);
    if (dragging_disk != null) {
      dragging_disk_start = dragging_disk.circle.center;
      stationary_disks.remove(dragging_disk);
      overlap_boundaries = Circle.expanded(
        Disk.circles(stationary_disks),
        dragging_disk.circle.radius);
      boundary_intersections = Circle.intersect(overlap_boundaries);
      ghost_disk = new Disk();
      ghost_disk.ghost = true;
      ghost_disk.color = dragging_disk.color;
      ghost_disk.circle.center = dragging_disk_start;
      ghost_disk.circle.radius = dragging_disk.circle.radius;
      loop_request.loop(true);
    }
  }

  Disk at_position(P2 p) {
    for (Disk d : stationary_disks) {
      if (d.contains(p)) {
        return d;
      }
    }
    return null;
  }

  void stop_dragging() {
    if (dragging_disk != null) {

      // If we found no legitimate place to drop the disk,
      // move it back to its original position
      if (dragging_disk.circle.center == null) {
        dragging_disk.circle.center = dragging_disk_start;
      }

      stationary_disks.add(dragging_disk);
      dragging_disk = null;
      dragging_disk_start = null;
      ghost_disk = null;
      overlap_boundaries = null;
      boundary_intersections = null;

      circumscribe();

    }
  }

  @Override public void release(P2 mouse) {
    stop_dragging();
  }

  @Override public void drag(P2 drag_diff) {
    if (dragging_disk != null) {
      _drag(drag_diff);
      circumscribe();
    }
  }

  void _drag(P2 drag_diff) {
    ghost_disk.circle.center = dragging_disk.circle.center = dragging_disk_start.add(drag_diff);
    List<Circle> overlaps = get_overlaps();

    // No overlaps
    if (overlaps.size() == 0) {
      return;
    }

    // A single overlap where we can simply move
    // to the closest edge of the overlapped disk
    if (overlaps.size() == 1) {
      Circle overlap = overlaps.get(0);

      // The direction from the overlapped disk center to the desired position
      P2 v = dragging_disk.circle.center.sub(overlap.center)
          .normalize().mult(overlap.radius);

      // If the direction is 0, the circles have exactly the same center,
      // so choose an arbitrary direction.
      if (v.isZero()) {
        v = new P2(0, -1);
      }

      v = v.scaleTo(overlap.radius).add(overlap.center);
      dragging_disk.circle.center = v;
      if (get_overlaps().size() == 0) {
        return;
      }
    }

    class PointWithDistance implements Comparable<PointWithDistance> {

      P2 p;
      float d;

      @Override
      public int compareTo(PointWithDistance o) {
        if (d == o.d) return 0;
        return d > o.d ? 1 : -1;
      }

    }
    List<PointWithDistance> points = newArrayList();
    for (P2 p : boundary_intersections) {
      PointWithDistance pwd = new PointWithDistance();
      pwd.p = p;
      pwd.d = p.dist(ghost_disk.circle.center);
      points.add(pwd);
    }
    Collections.sort(points);
    for (PointWithDistance pwd : points) {
      dragging_disk.circle.center = pwd.p;
      if (get_overlaps().size() == 0) {
        return;
      }
    }

    dragging_disk.circle.center = null;
  }

  List<Circle> get_overlaps() {
    List<Circle> overlaps = newArrayList();
    if (dragging_disk != null) {
      for (Circle c : overlap_boundaries) {
        if (c.withRadius(c.radius - padding).contains(dragging_disk.circle.center)) {
          overlaps.add(c);
        }
      }
    }
    return overlaps;
  }

  float enclosing_radius(final P2 center) {
    class X {
      float radius = 0;
      void consider(Disk d) {
        radius = Math.max(radius, center.dist(d.circle.center) + d.circle.radius);
      }
    }
    X x = new X();
    for (Disk d : stationary_disks) {
      x.consider(d);
    }
    if (dragging_disk != null) {
      x.consider(dragging_disk);
    }
    return x.radius;
  }

  void circumscribe() {
    new Runnable() {

      Circle x = new Circle();
      Random r = new Random(0);

      public void run() {

        x.center = new P2(200, 200);
        x.radius = enclosing_radius(x.center);
        for (float step_size = 100; step_size > 0; step_size -= .2) { try_shift(step_size); }
        for (float step_size = 1; step_size > 0; step_size -= 0.0001) { try_shift(step_size); }
        circumscribing_circle = x;
      }

      void try_shift(float step_size) {
        Circle shifted = x.withCenter(x.center.add(P2.random(step_size, r)));
        shifted.radius = enclosing_radius(shifted.center);
        if (shifted.radius < x.radius) {
          x = shifted;
        }
      }

    }.run();
  }

}
