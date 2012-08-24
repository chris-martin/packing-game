package packinggame;

import processing.core.PVector;

import java.awt.*;

interface Canvas {

  void fill(Color color);

  void stroke(Color stroke);

  void circle(P2 center, float radius);

  void circle(P2 center, float radius, Color fill);

  void circle(P2 center, float radius, Color fill, Color stroke);

}
