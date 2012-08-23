package packinggame;

import processing.core.PVector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static com.google.common.collect.Lists.newArrayList;

class Disks implements DragHandler, LoopController {

  ArrayList<Disk> disks = new ArrayList<Disk>();
  DiskFactory factory = new DiskFactory();

  void add() {
    disks.add(factory.next());
  }

  void draw(Canvas canvas) {
    for (Disk d : disks) {
      d.draw(canvas);
    }
  }

  Disk dragging_disk;
  PVector dragging_disk_start;

  @Override public void press(PVector mouse) {
    for (Disk d : disks) {
      if (d.contains(mouse)) {
        dragging_disk = d;
        dragging_disk_start = d.center;
        return;
      }
    }
  }

  @Override public void release(PVector mouse) {
    dragging_disk = null;
    dragging_disk_start = null;
  }

  @Override public void drag(DragInfo drag_info) {
    if (dragging_disk != null) {
      PVector desired_center = PVector.add(
        dragging_disk_start,
        drag_info.drag_diff()
      );
      PVector test_center = desired_center;
      Iterator<PVector> pushes = offset_iterator(3 * dragging_disk.radius);
      boolean overlap = false;
      while (pushes.hasNext() && (overlap = overlaps_anything(dragging_disk, test_center))) {
        test_center = PVector.add(desired_center, pushes.next());
      }
      dragging_disk.center = overlap ? desired_center : test_center;
    }
  }

  @Override public boolean loop() {
    return dragging_disk != null;
  }

  // would moving disk d to position center cause any overlap?
  boolean overlaps_anything(Disk d, PVector center) {
    for (Disk e : disks) {
      if (d != e && center.dist(e.center) < d.radius + e.radius) {
        return true;
      }
    }
    return false;
  }

  Iterator<PVector> offset_iterator(Disk dragging_disk) {

    class Circle {

      PVector center;
      float radius;

      List<PVector> intersection(Circle o) {

      }

    }

    List<Circle> circles = newArrayList();
    for (Disk d : disks) {
      if (d != dragging_disk) {
        Circle c = new Circle();
        c.center = d.center;
        c.radius = d.radius + dragging_disk.radius;
        circles.add(c);
      }
    }

    return new Iterator<PVector>() {

      @Override
      public boolean hasNext() {
        return false;
      }

      @Override
      public PVector next() {
        return null;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }

    };
  }

/*

  Iterator<PVector> offset_iterator(final float r_max) {
    return new Iterator<PVector>() {

      float r = 1, r_inc = .5f;
      float t = 0, t_max = (float) (2 * Math.PI);
      float t_inc = t_inc();
      boolean finished;

      float t_inc() {
        return r / 50f;
      }

      @Override
      public boolean hasNext() {
        return !finished;
      }

      @Override
      public PVector next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        PVector v = new PVector(
          (float) (r * Math.cos(t)),
          (float) (r * Math.sin(t))
        );
        t += t_inc;
        System.out.println(t);
        if (t > t_max) {
          t = 0;
          r += r_inc;
          t_inc = t_inc();
          if (r > r_max) {
            finished = true;
          }
        }
        return v;
      }

      @Override public void remove() {
        throw new UnsupportedOperationException();
      }

    };
  }
*/

}
