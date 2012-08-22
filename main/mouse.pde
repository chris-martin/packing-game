class MouseManager
    implements LoopController, DragInfo {

  PVector drag_start;
  ArrayList<DragHandler> drag_handlers = new ArrayList();
  LoopManager loop_manager;

  MouseManager(LoopManager loop_manager) {
    this.loop_manager = loop_manager;
  }

  void add(Object x) {
    if (x instanceof DragHandler) {
      drag_handlers.add((DragHandler) x);
    }
  }

  void press() {
    if (drag_handlers instanceof DragHandler) {
      fill(0,0,0);
      text("what", 300, 30);
    }
    drag_start = new PVector(mouseX, mouseY);
    for (DragHandler x : drag_handlers) {
      x.press();
    }
    loop_manager.maybe_loop();
  }

  void release() {
    for (DragHandler x : drag_handlers) {
      x.release();
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

  boolean loop() {
    return check_loop(drag_handlers);
  }

  PVector drag_start() {
    return drag_start;
  }

  PVector drag_diff() {
    PVector v = new PVector(mouseX, mouseY);
    v.sub(drag_start);
    return v;
  }

}

interface DragInfo {
  PVector drag_start();
  PVector drag_diff();
}

interface DragHandler {
  void press();
  void release();
  void drag(DragInfo drag_info);
}
