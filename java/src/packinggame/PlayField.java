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

  void line_up_disks() {
    float y = 0;
    for (Disk d : disks.stationary_disks) {
      y += d.circle.radius;
    }
    y = get_canvas().size().y / 2.f - y;
    for (Disk d : disks.stationary_disks) {
      d.circle.center = new P2(
        side == Side.left ? 50 : get_canvas().size().x - 50,
        y + d.circle.radius
      );
      y += 2 * d.circle.radius;
    }
    disks.circumscribe();
    disks.loop_request.loop(true);
  }

}
