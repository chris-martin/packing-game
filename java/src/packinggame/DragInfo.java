package packinggame;

import processing.core.PVector;

interface DragInfo {

  PVector drag_start();
  PVector drag_diff();

}
