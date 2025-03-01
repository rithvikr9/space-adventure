package utility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {
  public static BufferedImage loadImage(String imageName) {
    BufferedImage spritesheet;
    try {
      spritesheet = ImageIO.read(ImageLoader.class.getResource("/images/" + imageName + ".png"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return spritesheet;
  }
}
