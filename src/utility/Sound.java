package utility;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
  Clip clip;
  URL[] effects = new URL[10];

  public Sound() {
    effects[0] = getClass().getResource("/sound/missile.wav");
    effects[1] = getClass().getResource("/sound/bullet.wav");
    effects[2] = getClass().getResource("/sound/reloading.wav");
    effects[3] = getClass().getResource("/sound/hit.wav");
    effects[4] = getClass().getResource("/sound/missile_hit.wav");
    effects[5] = getClass().getResource("/sound/choose_option.wav");
    effects[6] = getClass().getResource("/sound/select_option.wav");
  }

  public void setFile(int i) {
    try {
      AudioInputStream ais = AudioSystem.getAudioInputStream(effects[i]);
      clip = AudioSystem.getClip();
      clip.open(ais);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void play() {
    clip.start();
  }
}
