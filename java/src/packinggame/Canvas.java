package packinggame;

import processing.core.PVector;

import java.awt.*;

interface Canvas {

  void fill(Color color);

  void stroke(Color stroke);

  void circle(PVector center, float radius);

  void circle(PVector center, float radius, Color fill);

  void circle(PVector center, float radius, Color fill, Color stroke);

}
