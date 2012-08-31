package packinggame;

import com.google.common.collect.Lists;
import packinggame.canvas.P2;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.google.common.collect.Lists.newArrayList;

class Packing {

  static List<P2> pack(List<Float> radii) {
    return new Packing(radii).pack();
  }

  List<Float> radii;

  Packing(List<Float> radii) {
    this.radii = radii;
  }

  float area_sum() {
    float area_sum = 0;
    for (float r : radii) {
      area_sum += Math.PI * Math.pow(r, 2);
    }
    return area_sum;
  }

  float radius_sum() {
    float radius_sum = 0;
    for (float r : radii) {
      radius_sum += r;
    }
    return radius_sum;
  }

  List<P2> pack() {

    List<P2> best_pack = null;

    List<Float> asc = newArrayList(radii);
    Collections.sort(asc);
    List<Float> desc = Lists.reverse(asc);

    float min_radius = radii.size() == 0 ? 0 : Math.max(
      // the area of the MEC is at least the sum of the disks' areas
      (float) Math.sqrt(area_sum()),
      // the radius of the MEC is at least the sum of the first two disks' radii
      desc.size() == 1 ? desc.get(0) : desc.get(0) + desc.get(1)
    );

    float max_radius = radius_sum();

    while (max_radius - min_radius > 1) {
      float r = (max_radius + min_radius) / 2;
      List<P2> pack = pack(r);
      if (pack != null) {
        max_radius = r;
        best_pack = pack;
      } else {
        min_radius = r;
      }
    }

    return best_pack;
  }

  List<P2> pack(float enclosing_radius) {
    for (int i = 0; i < 20; i++) {
      List<Circle> circles = circles(),
          permutation = newArrayList(circles);
      Collections.shuffle(permutation, new Random(i));
      if (pack(permutation, enclosing_radius)) {
        return Circle.centers(circles);
      }
    }
    return null;
  }

  List<Circle> circles() {
    List<Circle> circles = newArrayList();
    for (float r : radii) {
      Circle c = new Circle();
      c.radius = r;
      circles.add(c);
    }
    return circles;
  }

  boolean pack(List<Circle> circles, float enclosing_radius) {
    int i = 0;
    for (Circle c : circles) {
      if (i == 0) {
        c.center = new P2(0, 0);
      } else if (i == 1) {
        c.center = new P2(circles.get(0).radius + c.radius, 0);
      } else {
        P2 best_position;
        float best_radius = Float.MAX_VALUE;
        for (P2 intersection : Circle.intersect(circles)) {
          //float radius =
        }
      }
      i++;
    }
    return false;
  }

}
