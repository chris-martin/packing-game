package packinggame;

import processing.core.PVector;

interface DragHandler {
  void press(PVector mouse);
  void release(PVector mouse);
  void drag(DragInfo drag_info);
}
