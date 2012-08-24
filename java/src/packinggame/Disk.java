package packinggame;

import processing.core.PVector;

import java.awt.*;

class Disk {

  Color c;
  PVector center;
  float radius;
  boolean ghost;

  void draw(Canvas canvas) {
    Color c = this.c;
    if (ghost) {
      c = new Color(c.getRed(), c.getGreen(), c.getBlue(), 30);
    }
    canvas.circle(c, center, radius);
  }

  boolean contains(PVector p) {
    return p.dist(center) < radius;
  }

  boolean overlaps(Disk d) {
    return center.dist(d.center) < radius + d.radius;
  }

}
