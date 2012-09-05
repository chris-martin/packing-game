package packinggame;

import packinggame.canvas.Canvas;
import packinggame.canvas.IntSize;
import packinggame.canvas.PAppletCanvas;
import packinggame.loop.LoopRequestAggregator;
import packinggame.loop.Looping;
import packinggame.mouse.MouseManager;
import processing.core.PApplet;

import java.awt.event.KeyEvent;

public class Main extends PApplet {

  public static void main(String[] args) {
    PApplet.main(new String[]{ "packinggame.Main" });
  }

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
    noCursor();
  }

  @Override public void mouseReleased() {
    mouse_manager.release();
    cursor();
  }

  @Override public void mouseDragged() {
    mouse_manager.drag();
  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    switch (keyEvent.getKeyChar()) {
      case 'c':
        game.playFields.get(1).disks.pack();
        break;
      case 'r':
        game.line_up_disks();
        break;
      case 'g':
        game.start();
        break;
    }
  }

  @Override public void draw() {
    game.draw();
  }

}
