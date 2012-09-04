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
  boolean highlight;

  void draw(packinggame.canvas.Canvas canvas) {
    if (color != null && circle != null && circle.center != null && circle.radius > 0) {
      float r = circle.radius;
      P2 center = circle.center;
      if (ghost) {
        canvas.circle(center, r, new Color(0, 0, 0, 0), new Color(0, 0, 0, 180), 1);
      } else {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        float hue = hsb[0];
        float sat = hsb[1];
        float bri = hsb[2];
        canvas.circle(center, r, color);
        canvas.circle(center, r * 0.85f, Color.getHSBColor(hue, sat, Math.min(1, bri * (highlight ? 4f : 1.15f))));
        canvas.circle(center, r * 0.6f, color);
        int line_count = 7;
        for (int i = 0; i < line_count; i++) {
          float angle = (float) (i * 2 * Math.PI / line_count + 0.15);
          P2 p1 = new P2(0, r * 0.9f).rotate(angle);
          P2 p2 = new P2(0, r * 0.2f).rotate(angle - 0.8f);
          canvas.line(center.add(p1), center.add(p2), color, r * 0.1f);
        }
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
