package packinggame.canvas;

public class IntSize {

  public final int x;
  public final int y;

  public IntSize(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }

}
