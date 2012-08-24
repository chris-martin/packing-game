package packinggame;

import packinggame.loop.LoopRequestAggregator;
import packinggame.loop.Looping;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

public class Main extends PApplet {

  float phi = (1 + sqrt(5)) / 2;
  int window_width = 800;
  int window_height = (int) (800 / phi);

  Disks disks;
  MouseManager mouse_manager;
  LoopRequestAggregator loop_request = Looping.aggregator(Looping.papplet(this));

  @Override public void setup() {
    size(window_width, window_height);

    mouse_manager = new MouseManager(this);

    disks = new Disks();
    disks.loop_request = loop_request.newLoopRequest();
    disks.add();
    disks.add();
    disks.add();
    disks.add();
    disks.add();
    mouse_manager.add(disks);
  }

  @Override public void mousePressed() {
    mouse_manager.press();
  }

  @Override public void mouseReleased() {
    mouse_manager.release();
  }

  @Override public void mouseDragged() {
    mouse_manager.drag();
  }

  @Override public void draw() {
    colorMode(HSB, 255, 100, 100);
    background(150, 10, 95);
    fill(0, 0, 0);
    text("hello diskworld", 300, 300);
    disks.draw(canvas);
  }

  final Canvas canvas = new Canvas() {

    @Override
    public void fill(Color color) {
      Main.this.fill(color.getRGB());
    }

    @Override
    public void circle(PVector center, float radius) {
      float diameter = 2 * radius;
      Main.this.ellipse(center.x, center.y, diameter, diameter);
    }

    @Override
    public void circle(Color color, PVector center, float radius) {
      fill(color);
      circle(center, radius);
    }

  };

}
