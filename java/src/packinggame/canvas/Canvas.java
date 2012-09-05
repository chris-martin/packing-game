package packinggame.canvas;

import processing.core.PImage;

import java.awt.*;

public interface Canvas {

  enum H_align { left, center, right }

  void text(String text, Color color, float size, H_align align, P2 position);

  void fill(Color color);

  void stroke(Color stroke);

  void stroke(float weight);

  void background(Color background);

  void circle(P2 center, float radius);

  void circle(P2 center, float radius, Color fill);

  void circle(P2 center, float radius, Color fill, Color stroke, float strokeWeight);

  void circle(P2 center, float radius, Color stroke, float strokeWeight);

  void rectangle(P2 position, P2 size, Color fill, Color stroke, float strokeWeight, float round);

  void line(P2 a, P2 b, Color color, float thickness);

  void image(PImage image, P2 p, P2 size);

  IntSize size();

  Canvas subsection(P2 position, IntSize size);

}
