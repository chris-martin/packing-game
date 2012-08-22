class Random {

  int seed = 0;

  float get(float high) {
    randomSeed(seed);
    float x = random(high);
    randomSeed(seed);
    seed = int(random(Integer.MAX_VALUE));
    return x;
  }

  float get(float low, float high) {
    return low + get(high - low);
  }

}
