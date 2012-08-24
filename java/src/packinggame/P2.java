package packinggame;

import processing.core.PVector;

class P2 {

  final float x, y;

  P2(float x, float y) {
    this.x = x;
    this.y = y;
  }

  P2(PVector p) {
    this(p.x, p.y);
  }

  PVector pVector() {
    return new PVector(x, y);
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  float dist(P2 that) {
    return dist(this, that);
  }

  static float dist(P2 a, P2 b) {
    return PVector.dist(a.pVector(), b.pVector());
  }

  P2 normalize() {
    PVector v = pVector();
    v.normalize();
    return new P2(v);
  }

  P2 mult(float f) {
    PVector v = pVector();
    v.mult(f);
    return new P2(v);
  }

  P2 add(P2 p) {
    PVector v = pVector();
    v.add(p.pVector());
    return new P2(v);
  }

  P2 rotate(float angle) {
    PVector v = pVector();
    v.rotate(angle);
    return new P2(v);
  }

  P2 sub(P2 p) {
    PVector v = pVector();
    v.sub(p.pVector());
    return new P2(v);
  }

  float mag() {
    return pVector().mag();
  }

  P2 scaleTo(float f) {
    PVector v = pVector();
    v.scaleTo(f);
    return new P2(v);
  }

}
