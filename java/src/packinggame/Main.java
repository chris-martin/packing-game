package packinggame;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.io.CharStreams;
import packinggame.canvas.Canvas;
import packinggame.canvas.IntSize;
import packinggame.canvas.P2;
import packinggame.canvas.PAppletCanvas;
import packinggame.info.Info;
import packinggame.loop.LoopRequest;
import packinggame.loop.LoopRequestAggregator;
import packinggame.loop.Looping;
import packinggame.mouse.MouseManager;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.List;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.google.common.collect.Lists.newArrayList;

public class Main extends PApplet {

  public static void main(String[] args) {
    PApplet.main(new String[]{ "packinggame.Main" });
  }

  static Main instance;
  { instance = this; }
  public static PImage loadImageMT_(Image image) {
    return instance.loadImageMT(image);
  }

  static IntSize canvas_size() {
    float phi = (1 + sqrt(5)) / 2;
    int x = 1100;
    int y = (int) (x / phi);
    return new IntSize(x, y);
  }
  IntSize canvas_size = canvas_size();

  Game game = new Game();
  MouseManager mouse_manager = new MouseManager(this);
  LoopRequestAggregator loop_request_aggregator = Looping.aggregator(Looping.papplet(this));
  LoopRequest loop_request = loop_request_aggregator.newLoopRequest();
  Canvas canvas = new PAppletCanvas(this) {
    @Override
    public IntSize size() {
      return canvas_size;
    }
  };

  Info info;
  {
    IntSize size = new IntSize((int) (canvas_size.x * 0.7), (int) (canvas_size.y * 0.8));
    P2 position = new P2((canvas_size.x - size.x) / 2, (canvas_size.y - size.y) / 2);
    info = new Info(canvas.subsection(position, size));
  }
  boolean show_info;

  List<Float> radii() {
      List<String> lines;
      try {
          String x = CharStreams.toString(new InputStreamReader(Main.class.getResourceAsStream("disks.txt")));
          lines = newArrayList();
          for (String line : Splitter.on("\n").split(x)) {
              line = line.trim();
              if (line.length() != 0) {
                lines.add(line);
              }
          }
      } catch (IOException e) {
          return ImmutableList.of();
      }
      List<Float> radii = newArrayList();
      Integer n = Integer.parseInt(lines.get(0));
      for (int i = 1; i <= n; i++) {
          radii.add(Float.parseFloat(lines.get(i)));
      }
      return radii;
  }

  @Override public void setup() {
    size(canvas_size.x, canvas_size.y);
    smooth();

    mouse_manager.add(game.drag_handler);
    game.set_loop_request(loop_request_aggregator.newLoopRequest());
    game.set_canvas(canvas);
    game.start(radii());
    game.playFields.get(1).disks.pack();
  }

  @Override public void mousePressed() {
    mouse_manager.press();
  }

  @Override public void mouseReleased() {
    mouse_manager.release();
  }

  @Override public void mouseDragged() {
    mouse_manager.drag();
  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    switch (keyEvent.getKeyChar()) {
      case 'c':
        game.playFields.get(1).disks.pack();
        break;
      case 'r':
        game.line_up_disks();
        break;
      case 'g':
        game.start();
        break;
      case ' ':
        show_info = !show_info;
    }
    loop_request.loop(true);
  }

  @Override public void draw() {
    loop_request.loop(false);
    game.draw();
    P2 rect_size = new P2(400, 45);
    P2 rect_position = new P2(canvas_size.x / 2f - rect_size.x / 2f, canvas_size.y - rect_size.y - 5);
    if (show_info) {
      canvas.rectangle(new P2(0, 0), new P2(canvas_size.x, canvas_size.y), new Color(0, 0, 0, 128), new Color(0, 0, 0, 0), 0, 0);
      info.draw();
    }
    canvas.rectangle(rect_position, rect_size, new Color(255, 255, 255, 100), new Color(255, 255, 255, 80), 2, 10);
    canvas.text("Press spacebar to " + (show_info ? "hide" : "show") + " information.",
      Color.black, 20, Canvas.H_align.center,
      new P2(canvas_size.x / 2f, canvas_size.y - 20f));
  }

}
