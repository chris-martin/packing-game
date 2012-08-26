package packinggame.mouse;

import packinggame.canvas.P2;

public class ShiftedDragHandler implements DragHandler {

  final DragHandler handler;
  final P2 shift;

  public ShiftedDragHandler(DragHandler handler, P2 shift) {
    this.handler = handler;
    this.shift = shift;
  }

  @Override
  public void press(P2 mouse) {
    handler.press(mouse.sub(shift));
  }

  @Override
  public void release(P2 mouse) {
    handler.release(mouse.sub(shift));
  }

  @Override
  public void drag(P2 diff) {
    handler.drag(diff);
  }

}
