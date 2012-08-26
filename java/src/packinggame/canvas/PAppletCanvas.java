package packinggame.canvas;

import processing.core.PApplet;

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
    applet.fill(color.getRGB());
  }

  @Override
  public void stroke(Color color) {
    applet.stroke(color.getRGB());
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
    applet.rect(position.x, position.y, size.x, size.y);
  }

}
