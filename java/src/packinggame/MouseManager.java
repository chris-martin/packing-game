package packinggame;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

class MouseManager
    implements LoopController, DragInfo {

  PVector drag_start;
  final ArrayList<DragHandler> drag_handlers = new ArrayList<DragHandler>();
  final LoopManager loop_manager;
  final PApplet applet;

  MouseManager(PApplet applet, LoopManager loop_manager) {
    this.applet = applet;
    this.loop_manager = loop_manager;
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
    loop_manager.maybe_loop();
  }

  void release() {
    PVector mouse = new PVector(applet.mouseX, applet.mouseY);
    for (DragHandler x : drag_handlers) {
      x.release(mouse);
    }
    drag_start = null;
    loop_manager.maybe_loop();
  }

  void drag() {
    for (DragHandler x : drag_handlers) {
      x.drag(this);
    }
    loop_manager.maybe_loop();
  }

  @Override public boolean loop() {
    return Looping.check_loop(drag_handlers);
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