float phi = (1 + sqrt(5)) / 2;
int window_width = 800;
int window_height = int(800 / phi);

Disks disks;
MouseManager mouse_manager;
LoopManager loop_manager;

void setup() {
  size(window_width, window_height);

  loop_manager = new LoopManager();
  mouse_manager = new MouseManager(loop_manager);
  loop_manager.add(mouse_manager);

  disks = new Disks();
  disks.add();
  disks.add();
  mouse_manager.add(disks);
}

void mousePressed() {
  mouse_manager.press();
}

void mouseReleased() {
  mouse_manager.release();
}

void mouseDragged() {
  mouse_manager.drag();
}

void draw() {
  colorMode(HSB, 255, 100, 100);
  background(150, 10, 95);
  fill(0, 0, 0);
  text("hello diskworld", 300, 300);
  disks.draw();
}
