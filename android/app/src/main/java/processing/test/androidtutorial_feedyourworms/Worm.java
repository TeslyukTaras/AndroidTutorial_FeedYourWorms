package processing.test.androidtutorial_feedyourworms;

import processing.core.PApplet;
import processing.core.PConstants;

class Worm {
    private final AndroidTutorial_FeedYourWorms androidTutorialFeedYourWorms;
    float[] sx = new float[AndroidTutorial_FeedYourWorms.MAX_CHAIN_COUNT];
    float[] sy = new float[AndroidTutorial_FeedYourWorms.MAX_CHAIN_COUNT];
    float wavePeriod;
    float initialPeriod;
    int chainCount = AndroidTutorial_FeedYourWorms.INITIAL_CHAIN_COUNT;
    int preferedCornerRotation = AndroidTutorial_FeedYourWorms.CORNER_ROTATION_UNDEFINED;

    int R;
    int G;
    int B;

    Worm(AndroidTutorial_FeedYourWorms androidTutorialFeedYourWorms, int x, int y, float angle) {
        this.androidTutorialFeedYourWorms = androidTutorialFeedYourWorms;
        initData();

        sx[0] = x;
        sy[0] = y;
        sx[1] = x + PApplet.cos(angle) * AndroidTutorial_FeedYourWorms.SEGMENT_LENGTH;
        sy[1] = y + PApplet.sin(angle) * AndroidTutorial_FeedYourWorms.SEGMENT_LENGTH;
        sx[2] = sx[1] + PApplet.cos(angle) * AndroidTutorial_FeedYourWorms.SEGMENT_LENGTH;
        sy[2] = sy[1] + PApplet.sin(angle) * AndroidTutorial_FeedYourWorms.SEGMENT_LENGTH;
    }

    void initData() {
        wavePeriod = androidTutorialFeedYourWorms.random(2000, 5000);
        initialPeriod = androidTutorialFeedYourWorms.random(-PConstants.PI, PConstants.PI);
        R = (int) androidTutorialFeedYourWorms.random(128, 255);
        G = (int) androidTutorialFeedYourWorms.random(128, 255);
        B = (int) androidTutorialFeedYourWorms.random(128, 255);
    }

    Worm(AndroidTutorial_FeedYourWorms androidTutorialFeedYourWorms) {
        this.androidTutorialFeedYourWorms = androidTutorialFeedYourWorms;
        initData();
        int x = (int) androidTutorialFeedYourWorms.random(0, androidTutorialFeedYourWorms.width);
        int y = (int) androidTutorialFeedYourWorms.random(0, androidTutorialFeedYourWorms.height);
        float angle = androidTutorialFeedYourWorms.random(-PConstants.PI, PConstants.PI);
        chainCount = (int) androidTutorialFeedYourWorms.random(0, 10);
        sx[0] = x;
        sy[0] = y;
        for (int i = 1; i < chainCount; i++) {
            sx[i] = (float) (x + Math.cos(angle) * AndroidTutorial_FeedYourWorms.SEGMENT_LENGTH);
            sy[i] = (float) (y + Math.sin(angle) * AndroidTutorial_FeedYourWorms.SEGMENT_LENGTH);
        }
    }

    public void drag(float x, float y) {
        sx[0] = x;
        sy[0] = y;
        for (int i = 0; i < PApplet.min(chainCount, sx.length - 1); i++) {
            dragSegment(i + 1, sx[i], sy[i]);
        }
    }

    private void dragSegment(int i, float xin, float yin) {
        float dx = xin - sx[i];
        float dy = yin - sy[i];
        float angle = PApplet.atan2(dy, dx);
        sx[i] = xin - PApplet.cos(angle) * AndroidTutorial_FeedYourWorms.SEGMENT_LENGTH;
        sy[i] = yin - PApplet.sin(angle) * AndroidTutorial_FeedYourWorms.SEGMENT_LENGTH;
    }

    public void drawWorm() {
        for (int i = 1; i < PApplet.min(chainCount, sx.length - 1); i++) {
            drawSegment(sx[i - 1], sy[i - 1], sx[i], sy[i], R, G, B);
        }
    }

    public void drawSegment(float x1, float y1, float x2, float y2, int r, int g, int b) {
        androidTutorialFeedYourWorms.stroke(r, g, b, 100);
        androidTutorialFeedYourWorms.line(x1, y1, x2, y2);
    }

    public void addSegment() {
        chainCount++;
    }
}
