package packinggame;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

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
    if (!loop()) return;
    dragging_disk.center = PVector.add(
         dragging_disk_start,
         drag_info.drag_diff()
       );
/*
    if (dragging_disk != null) {
      PVector desired_center = PVector.add(
          dragging_disk_start,
          drag_info.drag_diff()
      );
      Disk hypothetical = new Disk();
      hypothetical.radius = dragging_disk.radius;
      hypothetical.center = desired_center;
      PVector push = new PVector(0, 0);
      while (PVector.dist())
        dragging_disk.center = desired_center + push;
    }*/
  }

  @Override public boolean loop() {
    return dragging_disk != null;
  }

}
