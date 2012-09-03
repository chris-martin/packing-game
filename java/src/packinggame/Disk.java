package packinggame;

import packinggame.canvas.P2;

import java.awt.*;
import java.util.*;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

class Disk {

  Color color;
  Circle circle = new Circle();
  boolean ghost;

  void draw(packinggame.canvas.Canvas canvas) {
    if (color != null && circle != null && circle.center != null && circle.radius > 0) {
      if (ghost) {
        canvas.circle(circle.center, circle.radius,
            new Color(color.getRed(), color.getGreen(), color.getBlue(), 50),
            new Color(0, 0, 0, 180), 1);
      } else {
        canvas.circle(circle.center, circle.radius, color);
      }
    }
  }

  boolean contains(P2 p) {
    return circle.contains(p);
  }

  boolean overlaps(Disk d) {
    return overlaps(d.circle);
  }

  boolean overlaps(Circle c) {
    return circle.overlaps(c);
  }

  Disk copy() {
    Disk copy = new Disk();
    copy.color = color;
    copy.circle = circle.copy();
    copy.ghost = ghost;
    return copy;
  }

  static List<Circle> circles(Collection<Disk> disks) {
    List<Circle> circles = newArrayList();
    for (Disk d : disks) {
      circles.add(d.circle);
    }
    return circles;
  }

  static List<Float> radii(Collection<Disk> disks) {
    List<Float> radii = newArrayList();
    for (Disk d : disks) {
      radii.add(d.circle.radius);
    }
    return radii;
  }

}
