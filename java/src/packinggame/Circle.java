package packinggame;

import com.google.common.collect.ImmutableList;
import processing.core.PApplet;

import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

class Circle {

  P2 center;
  float radius;

  Circle withRadius(float radius) {
    Circle c = new Circle();
    c.center = center;
    c.radius = radius;
    return c;
  }

  boolean contains(P2 p) {
    return p.dist(center) < radius;
  }

  boolean overlaps(Circle that) {
    return P2.dist(center, that.center) < radius + that.radius;
  }

  List<P2> intersect(Circle that) {
    return intersect(this, that);
  }

  // http://paulbourke.net/geometry/2circle/
  static List<P2> intersect(Circle i, Circle j) {

    float d = P2.dist(i.center, j.center);

    // circles are separate
    if (d > i.radius + j.radius) return ImmutableList.of();

    // one circle contains the other
    if (d < abs(i.radius - j.radius)) return ImmutableList.of();

    // circles are identica;
    if (d == 0 && i.radius == j.radius) return ImmutableList.of();

    float a = (float) (pow(i.radius, 2) - pow(j.radius, 2) + pow(d, 2)) / (2 * d);

    float h = (float) sqrt(pow(i.radius, 2) - pow(a, 2));

    P2 m = i.center.add(j.center.sub(i.center).mult(a).mult(1/d));

    return ImmutableList.of(
        new P2(
            m.x + h * (j.center.y - i.center.y) / d,
            m.y - h * (j.center.x - i.center.x) / d
        ),
        new P2(
            m.x - h * (j.center.y - i.center.y) / d,
            m.y + h * (j.center.x - i.center.x) / d
        )
    );
  }

}
