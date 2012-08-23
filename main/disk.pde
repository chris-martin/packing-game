class Disks implements DragHandler, LoopController {

  ArrayList<Disk> disks = new ArrayList();
  DiskFactory factory = new DiskFactory();

  void add() {
    disks.add(factory.next());
  }

  void draw() {
    for (Disk d : disks) {
      d.draw();
    }
  }

  Disk dragging_disk;
  PVector dragging_disk_start;

  void press() {
    PVector mouse = new PVector(mouseX, mouseY);
    for (Disk d : disks) {
      if (d.contains(mouse)) {
        dragging_disk = d;
        dragging_disk_start = d.center;
        return;
      }
    }
  }

  void release() {
    dragging_disk = null;
    dragging_disk_start = null;
  }

  void drag(DragInfo drag_info) {
    if (dragging_disk != null) {
      /*desired_center = PVector.add(
        dragging_disk_start,
        drag_info.drag_diff()
      );
      Disk hypothetical = new Disk();
      hypothetical.radius = d.radius;
      hypothetical.center = desired_center;
      PVector push = new PVector(0, 0);
      while (PVector.)
      dragging_disk.center = desired_center + push;*/
    }
  }

  boolean loop() {
    return dragging_disk != null;
  }

}

class DiskFactory {

  Random rand = new Random();

  Disk next() {
    Disk d = new Disk();
    colorMode(HSB, 255, 100, 100, 100);
    d.c = color(int(rand.get(255)), 80, 60);
    d.center = new PVector(100, 100);
    d.radius = 20;
    return d;
  }

}

class Disk {

  color c;
  PVector center;
  float radius;

  void draw() {
    fill(c);
    ellipse(center.x, center.y, 2*radius, 2*radius);
  }

  boolean contains(PVector p) {
    return p.dist(center) < radius;
  }

}
