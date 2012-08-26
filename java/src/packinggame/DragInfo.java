package packinggame;

import packinggame.canvas.P2;

interface DragInfo {

  P2 drag_start();
  P2 drag_diff();

}
