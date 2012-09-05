package packinggame.canvas;

import java.awt.*;

public abstract class BaseCanvas implements Canvas {

  @Override
  public Canvas subsection(P2 position, IntSize size) {
    return new ShiftedCanvas(this, position, size);
  }

  @Override
  public void background(Color background) {
    IntSize size = size();
    rectangle(new P2(0, 0), new P2(size.x, size.y), background, new Color(0, 0, 0, 0), 0, 0);
  }

  @Override
  public void circle(P2 center, float radius, Color fill) {
    fill(fill);
    stroke(0);
    circle(center, radius);
  }

  @Override
  public void circle(P2 center, float radius, Color fill, Color stroke, float strokeWeight) {
    stroke(stroke);
    stroke(strokeWeight);
    fill(fill);
    circle(center, radius);
  }

  @Override
  public void circle(P2 center, float radius, Color stroke, float strokeWeight) {
    stroke(stroke);
    stroke(strokeWeight);
    fill(new Color(0, 0, 0, 0));
    circle(center, radius, stroke);
  }

}
