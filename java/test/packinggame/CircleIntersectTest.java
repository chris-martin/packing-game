package packinggame;

import com.google.common.base.Joiner;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class CircleIntersectTest {

  @Test
  public void test() throws Exception {

    Circle a = new Circle();
    a.radius = 1;
    a.center = new P2(0, 0);

    Circle b = new Circle();
    b.radius = 1;
    b.center = new P2(1, 1);

    List<P2> intersections = a.intersect(b);

    assertEquals(intersections.size(), 2);

    List<P2> expected = newArrayList(new P2(0, 1), new P2(1, 0));

    for (P2 intersection : intersections) {
      Iterator<P2> expected_it = expected.iterator();
      while (expected_it.hasNext()) {
        if (expected_it.next().dist(intersection) < 0.001) {
          expected_it.remove();
        }
      }
    }

    if (expected.size() != 0) {
      fail("Missing: " + Joiner.on(", ").join(expected)
          + "\nGot instead: " + Joiner.on(", ").join(intersections));
    }

  }

}
