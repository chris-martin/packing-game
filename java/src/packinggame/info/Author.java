package packinggame.info;

import com.google.common.base.Throwables;
import com.google.common.io.ByteStreams;
import packinggame.Main;
import processing.core.PImage;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

class Author {

  String name;
  String email;
  String image_file_name;

  private PImage image;

  PImage image() {
    if (image == null) {
      try {
        image = Main.loadImageMT_(Toolkit.getDefaultToolkit().createImage(
          ByteStreams.toByteArray(Info.class.getResourceAsStream(image_file_name))));
      } catch (IOException e) {
        throw Throwables.propagate(e);
      }
    }
    return image;
  }

  static List<Author> all = newArrayList();
  static {

    Author a;

    all.add(a = new Author());
    a.name = "Anshul Bhatnagar";
    a.email = "anshul.bhatnagar@gatech.edu";
    a.image_file_name = "anshul.jpg";

    all.add(a = new Author());
    a.name = "Gaurav Dhage";
    a.email = "gr8dhage@gmail.com";
    a.image_file_name = "gaurav.jpg";

    all.add(a = new Author());
    a.name = "Chris Martin";
    a.email = "chris.martin@gatech.edu";
    a.image_file_name = "chris.jpg";

    all.add(a = new Author());
    a.name = "Suraj Sirpilli";
    a.email = "surajsirpilli@gatech.edu";
    a.image_file_name = "suraj.jpg";

  }

}
