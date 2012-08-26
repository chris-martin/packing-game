package packinggame.loop;

import processing.core.PApplet;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Looping {

  private Looping() { }

  public static LoopRequest papplet(PApplet applet) {
    return new PAppletLoopRequest(applet);
  }

  static final class PAppletLoopRequest implements LoopRequest {

    final PApplet applet;

    public PAppletLoopRequest(PApplet applet) {
      this.applet = applet;
    }

    @Override
    public void loop(boolean loop) {
      if (loop) {
        applet.loop();
      } else {
        applet.noLoop();
      }
    }

  }

  public static LoopRequestAggregator aggregator(LoopRequest loopRequest) {
    return new LoopRequestAggregatorImpl(loopRequest);
  }

  static final class LoopRequestAggregatorImpl implements LoopRequestAggregator {

    final LoopRequest parent;
    final List<Child> children = newArrayList();

    public LoopRequestAggregatorImpl(LoopRequest parent) {
      this.parent = parent;
    }

    class Child implements LoopRequest {

      boolean loop;

      @Override
      public void loop(boolean loop) {
        if (loop != this.loop) {
          this.loop = loop;
          LoopRequestAggregatorImpl.this.update();
        }
      }

    }

    @Override
    public LoopRequest newLoopRequest() {
      Child child = new Child();
      children.add(child);
      return child;
    }

    void update() {
      parent.loop(test());
    }

    boolean test() {
      for (Child child : children) {
        if (child.loop) {
          return true;
        }
      }
      return false;
    }

  }

}
