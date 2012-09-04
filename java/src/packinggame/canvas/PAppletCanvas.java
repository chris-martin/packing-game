package packinggame.canvas;

import processing.core.PApplet;
import processing.core.PFont;

import java.awt.*;

public class PAppletCanvas extends BaseCanvas {

  final PApplet applet;

  public PAppletCanvas(PApplet applet) {
    this.applet = applet;
  }

  @Override
  public IntSize size() {
    return new IntSize(applet.getWidth(), applet.getHeight());
  }

  @Override
  public void fill(Color color) {
    if (color.getAlpha() == 0) {
      applet.noFill();
    } else {
      applet.fill(color.getRGB());
    }
  }

  @Override
  public void stroke(Color color) {
    applet.stroke(color.getRGB());
  }

  @Override
  public void stroke(float weight) {
    if (weight == 0) {
      applet.noStroke();
    } else {
      applet.strokeWeight(weight);
    }
  }

  @Override
  public void circle(P2 center, float radius) {
    float diameter = 2 * radius;
    applet.ellipse(center.x, center.y, diameter, diameter);
  }

  @Override
  public void background(Color background) {
    applet.background(background.getRGB());
  }

  @Override
  public void rectangle(P2 position, P2 size) {
    stroke(0);
    applet.rect(position.x, position.y, size.x, size.y);
  }

  @Override
  public void text(String text, H_align align, P2 position) {
    int align_int;
    switch (align) {
      case left: align_int = PApplet.LEFT; break;
      case right: align_int = PApplet.RIGHT; break;
      default: align_int = PApplet.CENTER;
    }
    applet.textSize(36);
    fill(Color.black);
    applet.textAlign(align_int);
    applet.text(text, position.x, position.y);
  }

  @Override
  public void line(P2 a, P2 b, Color color, float thickness) {
    stroke(color);
    stroke(thickness);
    applet.strokeCap(PApplet.SQUARE);
    applet.line(a.x, a.y, b.x, b.y);
  }

}
