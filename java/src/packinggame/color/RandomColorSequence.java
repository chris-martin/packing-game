package packinggame.color;

import java.awt.*;
import java.util.Random;

public class RandomColorSequence implements ColorSequence {

  final Random random;
  public float saturation = .8f;
  public float brightness = .6f;

  public RandomColorSequence() {
    random = new Random();
  }

  public RandomColorSequence(int seed) {
    random = new Random(seed);
  }

  @Override
  public Color next() {
    return Color.getHSBColor(random.nextFloat(), saturation, brightness);
  }

}
