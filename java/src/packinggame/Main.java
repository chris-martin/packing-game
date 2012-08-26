package packinggame;

import packinggame.canvas.Canvas;
import packinggame.canvas.IntSize;
import packinggame.canvas.PAppletCanvas;
import packinggame.loop.LoopRequestAggregator;
import packinggame.loop.Looping;
import processing.core.PApplet;

public class Main extends PApplet {

  static IntSize canvas_size() {
    float phi = (1 + sqrt(5)) / 2;
    int x = 800;
    int y = (int) (x / phi);
    return new IntSize(x, y);
  }
  IntSize canvas_size = canvas_size();

  Disks disks;
  Game game;
  MouseManager mouse_manager;
  LoopRequestAggregator loop_request = Looping.aggregator(Looping.papplet(this));
  Canvas canvas = new PAppletCanvas(this);

  @Override public void setup() {
    size(canvas_size.x, canvas_size.y);
    smooth();

    mouse_manager = new MouseManager(this);

    disks = new Disks();
    disks.loop_request = loop_request.newLoopRequest();
    mouse_manager.add(disks);
    game = new Game(disks);
  }

  @Override public void mousePressed() {
    mouse_manager.press();
  }

  @Override public void mouseReleased() {
    mouse_manager.release();
  }

  @Override public void mouseDragged() {
    mouse_manager.drag();
  }

  @Override public void draw() {
    colorMode(HSB, 255, 100, 100);
    background(150, 10, 95);
    fill(0, 0, 0);
    text("hello diskworld", 300, 300);
    disks.draw(canvas);
  }

}
