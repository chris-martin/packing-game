package packinggame;

import com.google.common.collect.ImmutableList;
import processing.core.PApplet;

import java.util.List;

class Circle {

  P2 center;
  float radius;

  boolean contains(P2 p) {
    return p.dist(center) < radius;
  }

  boolean overlaps(Circle that) {
    return P2.dist(center, that.center) < radius + that.radius;
  }

  List<P2> intersect(Circle that) {
    return intersect(this, that);
  }

  static List<P2> intersect(Circle i, Circle j) {
    if (!i.overlaps(j)) {
      return ImmutableList.of();
    }
    float z = P2.dist(i.center, j.center);
    float beta = i.radius / j.radius;
    float alpha = (float) Math.pow(beta, 2);
    float x_j = z / (1 + alpha);
    float y = x_j * beta;
    P2 v = i.center;
    v = v.normalize();
    P2 u_1 = v, u_2 = v;
    u_1 = u_1.rotate(PApplet.HALF_PI);
    u_2 = u_2.rotate(PApplet.HALF_PI * -1);
    v = v.mult(x_j);
    u_1 = u_1.mult(y);
    u_2 = u_2.mult(y);
    u_1 = u_1.add(v);
    u_2 = u_2.add(v);
    return ImmutableList.of(u_1, u_2);
  }

}
