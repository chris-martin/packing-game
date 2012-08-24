package packinggame;

import packinggame.loop.LoopRequest;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;

class Disks implements DragHandler {

  final float padding = .001f;

  LoopRequest loop_request;

  DiskFactory factory = new DiskFactory();

  Set<Disk> stationary_disks = new HashSet<Disk>();

  List<Circle> overlap_boundaries = newArrayList();
  List<P2> boundary_intersections = newArrayList();

  Disk dragging_disk;
  P2 dragging_disk_start;

  Disk ghost_disk;

  void add() {
    stationary_disks.add(factory.next());
  }

  void draw(Canvas canvas) {
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
      dragging_disk_start = dragging_disk.center;
      stationary_disks.remove(dragging_disk);
      for (Disk disk : stationary_disks) {
        Circle c = new Circle();
        c.center = disk.center;
        c.radius = disk.radius + dragging_disk.radius;
        overlap_boundaries.add(c);
      }
      for (int i = 0; i < overlap_boundaries.size(); i++) {
        Circle I = overlap_boundaries.get(i);
        for (int j = 0; j < i; j++) {
          Circle J = overlap_boundaries.get(j);
          boundary_intersections.addAll(I.intersect(J));
        }
      }
      ghost_disk = new Disk();
      ghost_disk.ghost = true;
      ghost_disk.c = dragging_disk.c;
      ghost_disk.center = dragging_disk_start;
      ghost_disk.radius = dragging_disk.radius;
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

  @Override public void release(P2 mouse) {
    if (dragging_disk != null) {
      if (dragging_disk.center == null) {
        dragging_disk.center = dragging_disk_start;
      }
      stationary_disks.add(dragging_disk);
      dragging_disk = null;
      dragging_disk_start = null;
      ghost_disk = null;
      overlap_boundaries.clear();
      boundary_intersections.clear();
    }
  }

  @Override public void drag(DragInfo drag_info) {
    if (dragging_disk != null) {
      ghost_disk.center = dragging_disk.center = dragging_disk_start.add(drag_info.drag_diff());
      List<Circle> overlaps = get_overlaps();

      // No overlaps
      if (overlaps.size() == 0) {
        return;
      }

      // A single overlap where we can simply move
      // to the closest edge of the overlapped disk
      if (overlaps.size() == 1) {
        Circle overlap = overlaps.get(0);
        P2 v = dragging_disk.center.sub(overlap.center)
            .normalize().mult(overlap.radius);
        v = v.scaleTo(v.mag()).add(overlap.center);
        dragging_disk.center = v;
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
        pwd.d = p.dist(ghost_disk.center);
        points.add(pwd);
      }
      Collections.sort(points);
      for (PointWithDistance pwd : points) {
        dragging_disk.center = pwd.p;
        if (get_overlaps().size() == 0) {
          return;
        }
      }

      dragging_disk.center = null;

    }
  }

  List<Circle> get_overlaps() {
    List<Circle> overlaps = newArrayList();
    if (dragging_disk != null) {
      for (Circle c : overlap_boundaries) {
        if (c.withRadius(c.radius - padding).contains(dragging_disk.center)) {
          overlaps.add(c);
        }
      }
    }
    return overlaps;
  }

}
