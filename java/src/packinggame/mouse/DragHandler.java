package packinggame.mouse;

import packinggame.canvas.P2;

public interface DragHandler {
  void press(P2 mouse);
  void release(P2 mouse);
  void drag(P2 diff);
}
