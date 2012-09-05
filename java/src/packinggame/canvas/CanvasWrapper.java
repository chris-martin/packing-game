package packinggame.canvas;

import processing.core.PImage;

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
  public void rectangle(P2 position, P2 size, Color fill, Color stroke, float strokeWeight, float round) {
    canvas.rectangle(position, size, fill, stroke, strokeWeight, round);
  }

  @Override
  public void image(PImage image, P2 p, P2 size) {
    canvas.image(image, p, size);
  }

  @Override
  public IntSize size() {
    return canvas.size();
  }

  @Override
  public void text(String text, Color color, float size, H_align align, P2 position) {
    canvas.text(text, color, size, align, position);
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
