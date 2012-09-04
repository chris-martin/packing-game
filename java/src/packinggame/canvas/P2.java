package packinggame.canvas;

import processing.core.PVector;

import java.util.Random;

public class P2 {

  public final float x, y;

  public P2(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public P2(PVector p) {
    this(p.x, p.y);
  }

  public PVector pVector() {
    return new PVector(x, y);
  }

  public P2 x(float x) {
    return new P2(x, y);
  }

  public P2 y(float y) {
    return new P2(x, y);
  }

  public boolean isZero() {
    return x == 0 && y == 0;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  public float dist(P2 that) {
    return dist(this, that);
  }

  public static float dist(P2 a, P2 b) {
    return PVector.dist(a.pVector(), b.pVector());
  }

  public P2 normalize() {
    PVector v = pVector();
    v.normalize();
    return new P2(v);
  }

  public P2 mult(float f) {
    PVector v = pVector();
    v.mult(f);
    return new P2(v);
  }

  public P2 add(P2 p) {
    PVector v = pVector();
    v.add(p.pVector());
    return new P2(v);
  }

  public P2 rotate(float angle) {
    PVector v = pVector();
    v.rotate(angle);
    return new P2(v);
  }

  public P2 sub(P2 p) {
    PVector v = pVector();
    v.sub(p.pVector());
    return new P2(v);
  }

  public float mag() {
    return pVector().mag();
  }

  public P2 scaleTo(float f) {
    PVector v = pVector();
    v.scaleTo(f);
    return new P2(v);
  }

  public static P2 random(Random r) {
    float angle = r.nextFloat() * 2 * (float) Math.PI;
    return new P2((float) Math.cos(angle), (float) Math.sin(angle));
  }

  public static P2 random(float scale, Random r) {
    return random(r).scaleTo(scale);
  }

}
