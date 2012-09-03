package packinggame;

import packinggame.canvas.Canvas;
import packinggame.canvas.P2;
import packinggame.loop.LoopRequest;

import java.io.PipedWriter;

class PlayField {

  enum Side { left, right }
  Side side;

  final Disks disks = new Disks();

  private Canvas canvas;

  void set_canvas(Canvas canvas) {
    this.canvas = canvas;
    disks.canvas = canvas;
  }

  Canvas get_canvas() {
    return canvas;
  }

  void set_loop_request(LoopRequest loop_request) {
    disks.loop_request = loop_request;
  }

  void clear() {
    disks.clear();
  }

  void draw() {

    disks.draw();

    Circle mec = disks.circumscribing_circle;
    if (mec != null) {
      P2 p = new P2(20, 50);
      if (side == Side.left) {
        p = p.x(canvas.size().x - p.x);
      }
      Canvas.H_align align = side == Side.left ? Canvas.H_align.right : Canvas.H_align.left;
      canvas.text(Integer.toString((int) mec.radius), align, p);
    }
  }

}
