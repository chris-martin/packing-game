class LoopManager {

  ArrayList loop_controllers = new ArrayList();

  void add(Object x) {
    loop_controllers.add(x);
  }

  LoopManager() {
    noLoop();
  }

  void maybe_loop() {
    maybe_loop(check_loop(loop_controllers));
  }

  void maybe_loop(boolean x) {
    if (x) {
      loop();
    } else {
      noLoop();
    }
  }

}

interface LoopController {
  boolean loop();
}

boolean check_loop(Object x) {

  if (x instanceof LoopController) {
    LoopController lc = (LoopController) x;
    return lc.loop();
  }

  if (x instanceof ArrayList) {
    for (Object o : (ArrayList) x) {
      if (check_loop(o)) {
        return true;
      }
    }
  }

  return false;
}
