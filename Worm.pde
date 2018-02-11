final float SEGMENT_LENGTH = 20;
final int MAX_CHAIN_COUNT = 30;
final int INITIAL_CHAIN_COUNT = 3;

class Worm {
  float[] sx = new float[MAX_CHAIN_COUNT];
  float[] sy = new float[MAX_CHAIN_COUNT];
  
  int chainCount = INITIAL_CHAIN_COUNT;

  int R = (int) random(128, 255);
  int G = (int)random(128, 255);
  int B = (int)random(128, 255);

  Worm(int x, int y, float angle) {
    sx[0] = x;
    sy[0] = y;
    sx[1] = x + cos(angle) * SEGMENT_LENGTH;
    sy[1] = y + sin(angle) * SEGMENT_LENGTH;
    sx[2] = sx[1] + cos(angle) * SEGMENT_LENGTH;
    sy[2] = sy[1] + sin(angle) * SEGMENT_LENGTH;
  }

  void drag(float x, float y) {
    sx[0] = x;
    sy[0] = y;
    for (int i=0; i < min(chainCount, sx.length-1); i++) {
      dragSegment(i+1, sx[i], sy[i]);
    }
  }

  private void dragSegment(int i, float xin, float yin) {
    float dx = xin - sx[i];
    float dy = yin - sy[i];
    float angle = atan2(dy, dx);  
    sx[i] = xin - cos(angle) * SEGMENT_LENGTH;
    sy[i] = yin - sin(angle) * SEGMENT_LENGTH;
  }

  void drawWorm() {
    for (int i=1; i < min(chainCount, sx.length-1); i++) {
      drawSegment(sx[i-1], sy[i-1], sx[i], sy[i], R, G, B);
    }
  }

  void drawSegment(float x1, float y1, float x2, float y2, int r, int g, int b) {
    stroke(r, g, b, 100);
    line(x1, y1, x2, y2);
  }

  void addSegment() {
    chainCount++;
  }
}