package packinggame.canvas;

import java.awt.*;

public class ShiftedCanvas extends CanvasWrapper {

  final P2 shift;
  final IntSize size;

  public ShiftedCanvas(Canvas canvas, P2 shift, IntSize size) {
    super(canvas);
    this.shift = shift;
    this.size = size;
  }

  @Override
  public void circle(P2 center, float radius) {
    super.circle(center.add(shift), radius);
  }

  @Override
  public Canvas subsection(P2 position, IntSize size) {
    return super.subsection(position.add(shift), size);
  }

  @Override
  public IntSize size() {
    return size;
  }

}
