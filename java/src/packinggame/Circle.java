package packinggame;

import com.google.common.collect.ImmutableList;
import packinggame.canvas.P2;

import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newArrayListWithCapacity;
import static java.lang.Math.*;

class Circle {

  P2 center;
  float radius;

  Circle withCenter(P2 center) {
    Circle c = new Circle();
    c.center = center;
    c.radius = radius;
    return c;
  }

  Circle withRadius(float radius) {
    Circle c = new Circle();
    c.center = center;
    c.radius = radius;
    return c;
  }

  Circle withRadiusOffset(float off) {
    return withRadius(radius + off);
  }

  Circle copy() {
    Circle copy = new Circle();
    copy.center = center;
    copy.radius = radius;
    return copy;
  }

  boolean contains(P2 p) {
    return p.dist(center) < radius;
  }

  boolean contains(Circle that) {
    return P2.dist(center, that.center) < abs(radius - that.radius);
  }

  static boolean contains(List<Circle> circles, P2 p) {
    for (Circle c : circles) {
      if (c.center != null && c.contains(p)) {
        return true;
      }
    }
    return false;
  }

  boolean overlaps(Circle that) {
    return P2.dist(center, that.center) < radius + that.radius;
  }

  static List<Circle> expanded(List<Circle> circles, float addend) {
    List<Circle> expanded_circles = newArrayListWithCapacity(circles.size());
    for (Circle c : circles) {
      expanded_circles.add(c.withRadius(c.radius + addend));
    }
    return expanded_circles;
  }

  // Precondition: containing_circle.contains(this).
  // The smallest vector that pushes this up to the edge of containing_circle.
  P2 to_containing_circle(Circle containing_circle) {
    P2 v = center.sub(containing_circle.center);
    return v.scaleTo(containing_circle.radius - radius - v.mag());
  }

  List<P2> intersect(Circle that) {
    return intersect(this, that);
  }

  static List<P2> intersect(List<Circle> circles) {
    List<P2> intersections = newArrayList();
    for (int i = 0; i < circles.size(); i++) {
      Circle I = circles.get(i);
      for (int j = 0; j < i; j++) {
        Circle J = circles.get(j);
        intersections.addAll(I.intersect(J));
      }
    }
    return intersections;
  }

  // http://paulbourke.net/geometry/2circle/
  static List<P2> intersect(Circle i, Circle j) {

    if (i.center == null || j.center == null) {
      return ImmutableList.of();
    }

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

  public static List<P2> centers(List<Circle> circles) {
    List<P2> centers = newArrayList();
    for (Circle c : circles) {
      centers.add(c.center);
    }
    return centers;
  }

}
