package packinggame;

import packinggame.canvas.Canvas;
import packinggame.canvas.IntSize;
import packinggame.canvas.P2;
import packinggame.color.ColorSequence;
import packinggame.color.RandomColorSequence;
import packinggame.loop.LoopRequest;
import packinggame.loop.LoopRequestAggregator;
import packinggame.loop.Looping;
import packinggame.mouse.CompositeDragHandler;
import packinggame.mouse.ShiftedDragHandler;

import java.util.List;
import java.util.Random;

import static com.google.common.collect.Lists.newArrayList;

class Game {

  final CompositeDragHandler drag_handler = new CompositeDragHandler();

  List<PlayField> playFields = newArrayList();
  {
    for (int i = 0; i < 2; i++) {
      PlayField playField = new PlayField();
      playField.side = i == 0 ? PlayField.Side.left : PlayField.Side.right;
      playFields.add(playField);
    }
  }

  void set_loop_request(LoopRequest loop_request) {
    LoopRequestAggregator aggregator = Looping.aggregator(loop_request);
    for (PlayField playField : playFields) {
      playField.set_loop_request(aggregator.newLoopRequest());
    }
  }

  void set_canvas(Canvas canvas) {
    IntSize canvasSize = canvas.size();
    int height = canvasSize.y;
    int split = canvasSize.x / 2;

    playFields.get(0).set_canvas(canvas.subsection(new P2(0, 0), new IntSize(split, height)));
    playFields.get(1).set_canvas(canvas.subsection(new P2(split, 0), new IntSize(canvasSize.x - split, height)));

    drag_handler.handlers.add(playFields.get(0).disks);
    drag_handler.handlers.add(new ShiftedDragHandler(playFields.get(1).disks, new P2(split, 0)));
  }

  void draw() {
    RandomColorSequence colors = new RandomColorSequence(3);
    colors.brightness = .9f;
    colors.saturation = .2f;
    for (PlayField playField : playFields) {
      playField.get_canvas().background(colors.next());
    }
    for (PlayField playField : playFields) {
      playField.draw();
    }
  }

  void start() {
    for (PlayField playField : playFields) {
      playField.clear();
    }
    ColorSequence colors = Config.deterministic ? new RandomColorSequence(0) : new RandomColorSequence();
    Random radiusRandom = Config.deterministic ? new Random(0) : new Random();
    int y = 0;
    for (int i = 0; i < 10; i++) {
      Disk d = new Disk();
      d.color = colors.next();
      d.circle.radius = 10 + radiusRandom.nextInt(40);
      d.circle.center = new P2(0, y + d.circle.radius);

      List<Disk> ds = newArrayList();
      for (int j = 0; j < 2; j++) {
        ds.add(d.copy());
      }
      ds.get(0).circle.center = ds.get(1).circle.center.x(50);
      ds.get(1).circle.center = ds.get(0).circle.center.x(playFields.get(0).get_canvas().size().x - 50);

      for (int j = 0; j < 2; j++) {
        playFields.get(j).disks.add(ds.get(j));
      }
      y += 2 * d.circle.radius;
    }
    playFields.get(1).disks.pack();
  }

}
