package packinggame;

import packinggame.canvas.Canvas;
import packinggame.canvas.IntSize;
import packinggame.canvas.PAppletCanvas;
import packinggame.loop.LoopRequestAggregator;
import packinggame.loop.Looping;
import packinggame.mouse.MouseManager;
import processing.core.PApplet;

public class Main extends PApplet {

  public static Main instance;
  { instance = this; }

  static IntSize canvas_size() {
    float phi = (1 + sqrt(5)) / 2;
    int x = 1100;
    int y = (int) (x / phi);
    return new IntSize(x, y);
  }
  IntSize canvas_size = canvas_size();

  Game game = new Game();
  MouseManager mouse_manager = new MouseManager(this);
  LoopRequestAggregator loop_request = Looping.aggregator(Looping.papplet(this));
  Canvas canvas = new PAppletCanvas(this) {
    @Override
    public IntSize size() {
      return canvas_size;
    }
  };

  @Override public void setup() {
    size(canvas_size.x, canvas_size.y);
    smooth();

    mouse_manager.add(game.drag_handler);
    game.set_loop_request(loop_request.newLoopRequest());
    game.set_canvas(canvas);
    game.start();
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
    game.draw();
  }

}
