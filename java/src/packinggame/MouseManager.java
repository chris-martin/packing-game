package packinggame;

import packinggame.loop.LoopRequest;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

class MouseManager implements DragInfo {

  PVector drag_start;
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
    drag_start = new PVector(applet.mouseX, applet.mouseY);
    for (DragHandler x : drag_handlers) {
      x.press(drag_start);
    }
  }

  void release() {
    PVector mouse = new PVector(applet.mouseX, applet.mouseY);
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

  @Override public PVector drag_start() {
    return drag_start;
  }

  @Override public PVector drag_diff() {
    PVector x = new PVector(applet.mouseX, applet.mouseY);
    x.sub(drag_start);
    return x;
  }

}