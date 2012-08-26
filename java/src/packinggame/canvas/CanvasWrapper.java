package packinggame.canvas;

import java.awt.*;

public class CanvasWrapper implements Canvas {

  final Canvas canvas;

  public CanvasWrapper(Canvas canvas) {
    this.canvas = canvas;
  }

  @Override
  public void fill(Color color) {
    canvas.fill(color);
  }

  @Override
  public void stroke(Color stroke) {
    canvas.stroke(stroke);
  }

  @Override
  public void circle(P2 center, float radius) {
    canvas.circle(center, radius);
  }

  @Override
  public void circle(P2 center, float radius, Color fill) {
    canvas.circle(center, radius, fill);
  }

  @Override
  public void circle(P2 center, float radius, Color fill, Color stroke) {
    canvas.circle(center, radius, fill, stroke);
  }

  @Override
  public IntSize size() {
    return canvas.size();
  }

  @Override
  public Canvas subsection(P2 position, IntSize size) {
    return canvas.subsection(position, size);
  }

}
