package packinggame;

import packinggame.canvas.P2;

import java.awt.*;

class Disk {

  Color c;
  P2 center;
  float radius;
  boolean ghost;

  void draw(packinggame.canvas.Canvas canvas) {
    if (c != null && center != null) {
      Color c = this.c;
      Color stroke;
      if (ghost) {
        c = new Color(c.getRed(), c.getGreen(), c.getBlue(), 30);
        stroke = Color.black;
      } else {
        stroke = c.darker().darker();
      }
      canvas.circle(center, radius, c, stroke);
    }
  }

  boolean contains(P2 p) {
    return p.dist(center) < radius;
  }

  boolean overlaps(Disk d) {
    return center.dist(d.center) < radius + d.radius;
  }

  Disk copy() {
    Disk copy = new Disk();
    copy.c = c;
    copy.center = center;
    copy.radius = radius;
    copy.ghost = ghost;
    return copy;
  }

}
