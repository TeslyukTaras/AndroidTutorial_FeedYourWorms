float VELOCITY = 140;
int CORNER_CHECK_COEF = 2;
int WALL_CHECK_COEF = 1;

class WormController {
  int worldSizeX = 0;
  int worldSizeY = 0;

  void setWorldSize(int x, int y) {
    this.worldSizeX = x;
    this.worldSizeY = y;
  }

  private void dragWorm(Worm worm, float millis) {
    float timeFrame = millis / 1000;
    float angle = getRightAngle(worm) + getAngleShift(worm, timeFrame);

    float distance = timeFrame * VELOCITY;
    float nextX = worm.sx[0] + cos(angle) * distance;
    float nextY = worm.sy[0] + sin(angle) * distance;
    worm.drag(nextX, nextY);
  }
  
  float getAngleShift(Worm worm, float timeFrame) {
     float angleShift = PI * sin(worm.initialPeriod + 
      ( (float) millis() / worm.wavePeriod)) * timeFrame;
      return angleShift;
  }

  float getRightAngle(Worm worm) {
    float dx = worm.sx[0] - worm.sx[1];
    float dy = worm.sy[0] - worm.sy[1];

    if (isInCorner(worm)) {
      if (worm.preferedCornerRotation == CORNER_ROTATION_UNDEFINED) {
        boolean isDxBigger = abs(dx) > abs(dy);
        if (isDxBigger) {
          worm.preferedCornerRotation = CORNER_ROTATION_OY;
        } else {
          worm.preferedCornerRotation = CORNER_ROTATION_OX;
        }
      }
      if (worm.preferedCornerRotation == CORNER_ROTATION_OY) {
        dx = 0.01;//move OY
        if (isTopWall(worm, CORNER_CHECK_COEF)) {
          dy = 1; //DOWN
        } else {
          dy = -1; //UP
        }
      } else {
        dy = 0.01;//move OX
        if (isLeftWall(worm, CORNER_CHECK_COEF)) {
          dx = 1; //RIGHT
        } else {
          dx = -1;//LEFT
        }
      }
    } else {
      worm.preferedCornerRotation = CORNER_ROTATION_UNDEFINED;
      if (isLeftWall(worm, WALL_CHECK_COEF)) {
        dx = 0.01;//move OY
      } else if (isRightWall(worm, WALL_CHECK_COEF)) {
        dx = -0.01; //move OY
      }
      if (isTopWall(worm, WALL_CHECK_COEF)) {
        dy = 0.01; //move OX
      } else if (isBottomWall(worm, WALL_CHECK_COEF)) {
        dy = -0.01; //move OX
      }
    }

    return atan2(dy, dx);
  }

  private boolean isInCorner(Worm worm) {
    int closeWalls = 0;

    if (isLeftWall(worm, CORNER_CHECK_COEF)) {
      closeWalls++;
    } else if (isRightWall(worm, CORNER_CHECK_COEF)) {
      closeWalls++;
    }
    if (isTopWall(worm, CORNER_CHECK_COEF)) {
      closeWalls++;
    } else if (isBottomWall(worm, CORNER_CHECK_COEF)) {
      closeWalls++;
    }

    return closeWalls == 2;
  }

  private boolean isLeftWall(Worm worm, int coef) {
    return worm.sx[0] < coef* STROKE_WIDTH;
  }

  private boolean isRightWall(Worm worm, int coef) {
    return worm.sx[0] > worldSizeX - coef * STROKE_WIDTH;
  }

  private boolean isTopWall(Worm worm, int coef) {
    return worm.sy[0] < coef*STROKE_WIDTH;
  }

  private boolean isBottomWall(Worm worm, int coef) {
    return worm.sy[0] > worldSizeY - coef*STROKE_WIDTH;
  }
}