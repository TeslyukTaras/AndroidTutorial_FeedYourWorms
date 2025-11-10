package processing.test.androidtutorial_feedyourworms;
        
import processing.android.PWallpaper;
import processing.core.PApplet;
        
public class MainService extends PWallpaper {  
  @Override
  public PApplet createSketch() {
    PApplet sketch = new AndroidTutorial_FeedYourWorms();
    
    return sketch;
  }
}
