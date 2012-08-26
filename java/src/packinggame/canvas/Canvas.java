package packinggame.canvas;

import java.awt.*;

public interface Canvas {

  void fill(Color color);

  void stroke(Color stroke);

  void background(Color background);

  void circle(P2 center, float radius);

  void circle(P2 center, float radius, Color fill);

  void circle(P2 center, float radius, Color fill, Color stroke);

  void rectangle(P2 position, P2 size);

  IntSize size();

  Canvas subsection(P2 position, IntSize size);

}
