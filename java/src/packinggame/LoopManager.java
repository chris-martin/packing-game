package packinggame;

import processing.core.PApplet;

import java.util.ArrayList;

class LoopManager {

  ArrayList<Object> loop_controllers = new ArrayList<Object>();

  void add(Object x) {
    loop_controllers.add(x);
  }

  final PApplet applet;

  LoopManager(PApplet applet) {
    this.applet = applet;
    applet.noLoop();
  }

  void maybe_loop() {
    maybe_loop(Looping.check_loop(loop_controllers));
  }

  void maybe_loop(boolean x) {
    if (x) {
      applet.loop();
    } else {
      applet.noLoop();
    }
  }

}
