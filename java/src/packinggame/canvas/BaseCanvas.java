package packinggame.canvas;

import java.awt.*;

public abstract class BaseCanvas implements Canvas {

  @Override
  public Canvas subsection(P2 position, IntSize size) {
    return new ShiftedCanvas(this, position, size);
  }

  @Override
  public void circle(P2 center, float radius, Color fill) {
    fill(fill);
    circle(center, radius);
  }

  @Override
  public void circle(P2 center, float radius, Color fill, Color stroke) {
    stroke(stroke);
    circle(center, radius, fill);
  }

}
