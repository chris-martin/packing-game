package packinggame;

import processing.core.PVector;

class Circle {

  PVector center;
  float radius;

  boolean contains(PVector p) {
    return p.dist(center) < radius;
  }

}
