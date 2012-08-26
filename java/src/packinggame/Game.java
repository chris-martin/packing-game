package packinggame;

import packinggame.canvas.P2;
import packinggame.color.ColorSequence;
import packinggame.color.RandomColorSequence;

import java.util.ArrayList;

class Game {

  final Disks disks;

  Game(Disks disks) {
    this.disks = disks;
  }

  void start() {
    disks.clear();
    ColorSequence colors = new RandomColorSequence(0);
    for (int i = 0; i < 10; i++) {
      Disk d = new Disk();
      d.c = colors.next();

    }
  }

  final DragHandler drag_handler = new DragHandler() {
    @Override
    public void press(P2 mouse) {
    }

    @Override
    public void release(P2 mouse) {
    }

    @Override
    public void drag(DragInfo drag_info) {
    }
  };

}
