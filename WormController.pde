float VELOCITY = 140;

class WormController {
  int worldSizeX = 0;
  int worldSizeY = 0;

  void setWorldSize(int x, int y) {
    this.worldSizeX = x;
    this.worldSizeY = y;
  }

  private void dragWorm(Worm worm, float millis) {
    float timeFrame = millis / 1000;
    float angle = getRightAngle(worm);

    float distance = timeFrame * VELOCITY;
    float nextX = worm.sx[0] + cos(angle) * distance;
    float nextY = worm.sy[0] + sin(angle) * distance;
    worm.drag(nextX, nextY);
  }

  float getRightAngle(Worm worm) {
    float dx = worm.sx[0] - worm.sx[1];
    float dy = worm.sy[0] - worm.sy[1];

    return atan2(dy, dx);
  }
}