package packinggame;

import java.util.ArrayList;

class Looping {

  static boolean check_loop(Object x) {

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

}
