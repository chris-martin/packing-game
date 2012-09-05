package packinggame;

import packinggame.canvas.Canvas;
import packinggame.canvas.P2;
import packinggame.loop.LoopRequest;

import java.awt.*;
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
      P2 p = new P2(50, 100);
      if (side == Side.left) {
        p = p.x(canvas.size().x - p.x);
      }
      Canvas.H_align align = side == Side.left ? Canvas.H_align.right : Canvas.H_align.left;
      canvas.text(Integer.toString((int) mec.radius), new Color(0, 0, 0, 120), 88, align, p);
    }
  }

  void line_up_disks() {
    float y = 0;
    float max_r = 0;
    for (Disk d : disks.stationary_disks) {
      float r = d.circle.radius;
      y += r;
      max_r = Math.max(max_r, r);
    }
    y = get_canvas().size().y / 2.f - y;
    float x = side == Side.left ? max_r : get_canvas().size().x - max_r;
    for (Disk d : disks.stationary_disks) {
      float r = d.circle.radius;
      d.circle.center = new P2(x, y + r);
      y += 2 * r;
    }
    disks.circumscribe();
    disks.loop_request.loop(true);
  }

}
