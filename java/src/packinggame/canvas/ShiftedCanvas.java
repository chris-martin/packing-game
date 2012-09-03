package packinggame.canvas;

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
  public void rectangle(P2 position, P2 size) {
    super.rectangle(position.add(shift), size);
  }

  @Override
  public IntSize size() {
    return size;
  }

  @Override
  public void text(String text, H_align align, P2 position) {
    super.text(text, align, position.add(shift));
  }

}
