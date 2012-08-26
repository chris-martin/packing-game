package packinggame.mouse;

import packinggame.canvas.P2;
import packinggame.mouse.DragHandler;
import packinggame.mouse.DragInfo;
import processing.core.PApplet;

import java.util.ArrayList;

public class MouseManager {

  P2 drag_start;
  public final ArrayList<DragHandler> drag_handlers = new ArrayList<DragHandler>();
  public final PApplet applet;

  public MouseManager(PApplet applet) {
    this.applet = applet;
  }

  public void add(Object x) {
    if (x instanceof DragHandler) {
      drag_handlers.add((DragHandler) x);
    }
  }

  public void press() {
    drag_start = new P2(applet.mouseX, applet.mouseY);
    for (DragHandler x : drag_handlers) {
      x.press(drag_start);
    }
  }

  public void release() {
    P2 mouse = new P2(applet.mouseX, applet.mouseY);
    for (DragHandler x : drag_handlers) {
      x.release(mouse);
    }
    drag_start = null;
  }

  public void drag() {
    for (DragHandler x : drag_handlers) {
      x.drag(new P2(applet.mouseX, applet.mouseY).sub(drag_start));
    }
  }

}