package packinggame.canvas;

import processing.core.PImage;

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
  public void rectangle(P2 position, P2 size, Color fill, Color stroke, float strokeWeight, float round) {
    super.rectangle(position.add(shift), size, fill, stroke, strokeWeight, round);
  }

  @Override
  public void image(PImage image, P2 p, P2 size) {
    super.image(image, p.add(shift), size);
  }

  @Override
  public IntSize size() {
    return size;
  }

  @Override
  public void text(String text, Color color, float size, H_align align, P2 position) {
    super.text(text, color, size, align, position.add(shift));
  }

  @Override
  public void line(P2 a, P2 b, Color color, float thickness) {
    super.line(a.add(shift), b.add(shift), color, thickness);
  }

}
