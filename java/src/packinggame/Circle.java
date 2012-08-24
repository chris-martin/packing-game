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
    final float z = P2.dist(i.center, j.center);
    final float beta = i.radius / j.radius;
    final float alpha = (float) Math.pow(beta, 2);
    final float x_j = z / (1 + alpha);
    System.out.println(x_j);
    final float y = x_j * beta;
    System.out.println(y);
    final P2 v_direction = i.center.sub(j.center).normalize();
    System.out.println(v_direction);
    final P2 v = v_direction.scaleTo(x_j);
    final P2 u_1 = v_direction.rotate(PApplet.HALF_PI).mult(y).add(v).add(j.center);
    final P2 u_2 = v_direction.rotate(PApplet.HALF_PI * -1).mult(y).add(v).add(j.center);
    return ImmutableList.of(u_1, u_2);
  }

}
