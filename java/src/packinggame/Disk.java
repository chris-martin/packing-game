package packinggame;

import processing.core.PVector;

import java.awt.*;

class Disk {

  Color c;
  PVector center;
  float radius;

  void draw(Canvas canvas) {
    canvas.circle(c, center, radius);
  }

  boolean contains(PVector p) {
    return p.dist(center) < radius;
  }

}
