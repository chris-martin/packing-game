package packinggame.mouse;

import packinggame.canvas.P2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class CompositeDragHandler implements DragHandler {

  public final List<DragHandler> handlers = newArrayList();

  @Override
  public void press(P2 mouse) {
    for (DragHandler handler : handlers) {
      handler.press(mouse);
    }
  }

  @Override
  public void release(P2 mouse) {
    for (DragHandler handler : handlers) {
      handler.release(mouse);
    }
  }

  @Override
  public void drag(P2 diff) {
    for (DragHandler handler : handlers) {
      handler.drag(diff);
    }
  }

}
