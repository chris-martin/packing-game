package packinggame.color;

import java.awt.*;
import java.util.Random;

public class RandomColorSequence implements ColorSequence {

  final Random random;

  public RandomColorSequence() {
    random = new Random();
  }

  public RandomColorSequence(int seed) {
    random = new Random(seed);
  }

  @Override
  public Color next() {
    return Color.getHSBColor(random.nextFloat(), .8f, .6f);
  }

}
