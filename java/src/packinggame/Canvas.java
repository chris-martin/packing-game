package packinggame;

import processing.core.PVector;

import java.awt.*;

interface Canvas {

  void fill(Color color);

  void circle(PVector center, float radius);

  void circle(Color color, PVector center, float radius);

}
