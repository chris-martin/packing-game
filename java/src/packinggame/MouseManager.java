package packinggame;

import packinggame.canvas.P2;
import processing.core.PApplet;

import java.util.ArrayList;

class MouseManager implements DragInfo {

  P2 drag_start;
  final ArrayList<DragHandler> drag_handlers = new ArrayList<DragHandler>();
  final PApplet applet;

  MouseManager(PApplet applet) {
    this.applet = applet;
  }

  void add(Object x) {
    if (x instanceof DragHandler) {
      drag_handlers.add((DragHandler) x);
    }
  }

  void press() {
    drag_start = new P2(applet.mouseX, applet.mouseY);
    for (DragHandler x : drag_handlers) {
      x.press(drag_start);
    }
  }

  void release() {
    P2 mouse = new P2(applet.mouseX, applet.mouseY);
    for (DragHandler x : drag_handlers) {
      x.release(mouse);
    }
    drag_start = null;
  }

  void drag() {
    for (DragHandler x : drag_handlers) {
      x.drag(this);
    }
  }

  @Override public P2 drag_start() {
    return drag_start;
  }

  @Override public P2 drag_diff() {
    return new P2(applet.mouseX, applet.mouseY).sub(drag_start);
  }

}