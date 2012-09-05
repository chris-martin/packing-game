package packinggame.info;

import packinggame.canvas.Canvas;
import packinggame.canvas.P2;

import java.awt.*;

public class Info {

  final Canvas canvas;

  public Info(Canvas canvas) {
    this.canvas = canvas;
  }

  public void draw() {
    canvas.rectangle(
      new P2(0, 0),
      new P2(canvas.size().x, canvas.size().y),
      new Color(255, 224, 111, 245),
      new Color(0, 0, 0, 245), 4, 50);

    int i = 0;
    for (Author a : Author.all) {
      canvas.image(a.image(), new P2(canvas.size().x - 125, 50 + i * 115), new P2(100, 100));
      canvas.text(a.name, Color.black, 24, Canvas.H_align.right, new P2(canvas.size().x - 150, 80 + i * 115));
      canvas.text(a.email, Color.black, 18, Canvas.H_align.right, new P2(canvas.size().x - 150, 115 + i * 115));
      i++;
    }

    canvas.text("Key commands:\n\nr - Restart the current game\n\ng - Generate a new game\n\nc - Computer's turn:\n      automatically pack the\n      disks on the right",
      Color.black, 20, Canvas.H_align.left, new P2(25, 150));
  }

}
