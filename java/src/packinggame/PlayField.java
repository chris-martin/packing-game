package packinggame;

import packinggame.canvas.Canvas;
import packinggame.loop.LoopRequest;

class PlayField {

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
  }

}
