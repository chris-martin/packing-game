package packinggame;

import processing.core.PVector;

import java.awt.*;
import java.util.Random;

class DiskFactory {

  Random rand = new Random(0);

  Disk next() {
    Disk d = new Disk();
    d.c = Color.getHSBColor(rand.nextFloat(), .8f, .6f);
    d.center = new PVector(100, 100);
    d.radius = 50;
    return d;
  }

}
