package packinggame;

interface DragHandler {
  void press(P2 mouse);
  void release(P2 mouse);
  void drag(DragInfo drag_info);
}
