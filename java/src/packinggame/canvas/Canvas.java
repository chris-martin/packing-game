package packinggame.canvas;

import java.awt.*;

public interface Canvas {

  enum H_align { left, center, right }

  void text(String text, H_align align, P2 position);

  void fill(Color color);

  void stroke(Color stroke);

  void stroke(float weight);

  void background(Color background);

  void circle(P2 center, float radius);

  void circle(P2 center, float radius, Color fill);

  void circle(P2 center, float radius, Color fill, Color stroke, float strokeWeight);

  void circle(P2 center, float radius, Color stroke, float strokeWeight);

  void rectangle(P2 position, P2 size);

  void line(P2 a, P2 b, Color color, float thickness);

  IntSize size();

  Canvas subsection(P2 position, IntSize size);

}
