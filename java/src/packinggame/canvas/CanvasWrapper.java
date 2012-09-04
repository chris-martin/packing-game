package packinggame.canvas;

import java.awt.*;

public class CanvasWrapper extends BaseCanvas {

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
  public void stroke(float weight) {
    canvas.stroke(weight);
  }

  @Override
  public void circle(P2 center, float radius) {
    canvas.circle(center, radius);
  }

  @Override
  public void rectangle(P2 position, P2 size) {
    canvas.rectangle(position, size);
  }

  @Override
  public IntSize size() {
    return canvas.size();
  }

  @Override
  public void text(String text, H_align align, P2 position) {
    canvas.text(text, align, position);
  }

  @Override
  public Canvas subsection(P2 position, IntSize size) {
    return canvas.subsection(position, size);
  }

  @Override
  public void line(P2 a, P2 b, Color color, float thickness) {
    canvas.line(a, b, color, thickness);
  }

}
