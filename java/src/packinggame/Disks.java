package packinggame;

import packinggame.loop.LoopRequest;
import processing.core.PVector;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;

class Disks implements DragHandler {

  LoopRequest loop_request;

  DiskFactory factory = new DiskFactory();

  Set<Disk> stationary_disks = new HashSet<Disk>();

  List<Circle> overlap_boundaries = newArrayList();

  Disk dragging_disk;
  PVector dragging_disk_start;

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

  @Override public void press(PVector mouse) {
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
      ghost_disk = new Disk();
      ghost_disk.ghost = true;
      ghost_disk.c = dragging_disk.c;
      ghost_disk.center = dragging_disk_start;
      ghost_disk.radius = dragging_disk.radius;
      loop_request.loop(true);
    }
  }

  Disk at_position(PVector p) {
    for (Disk d : stationary_disks) {
      if (d.contains(p)) {
        return d;
      }
    }
    return null;
  }

  @Override public void release(PVector mouse) {
    stationary_disks.add(dragging_disk);
    dragging_disk = null;
    dragging_disk_start = null;
    ghost_disk = null;
    overlap_boundaries.clear();
  }

  @Override public void drag(DragInfo drag_info) {
    if (dragging_disk != null) {
      ghost_disk.center = dragging_disk.center = PVector.add(
        dragging_disk_start,
        drag_info.drag_diff()
      );
      List<Circle> overlaps = get_overlaps();
      if (overlaps.size() != 0) {
        if (overlaps.size() == 1) {
          Circle overlap = overlaps.get(0);
          PVector v = PVector.sub(dragging_disk.center, overlap.center);
          v.normalize();
          v.mult(overlap.radius);
          v.add(overlap.center);
          dragging_disk.center = v;
        } else {
          dragging_disk.center = new PVector(0, 0);
        }
      }
    }
  }

  List<Circle> get_overlaps() {
    List<Circle> overlaps = newArrayList();
    if (dragging_disk != null) {
      for (Circle c : overlap_boundaries) {
        if (c.contains(dragging_disk.center)) {
          overlaps.add(c);
        }
      }
    }
    return overlaps;
  }

}
