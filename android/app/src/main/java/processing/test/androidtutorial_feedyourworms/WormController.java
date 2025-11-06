package processing.test.androidtutorial_feedyourworms;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;

class WormController {
    private final AndroidTutorial_FeedYourWorms androidTutorialFeedYourWorms;
    int worldSizeX = 0;
    int worldSizeY = 0;

    public WormController(AndroidTutorial_FeedYourWorms androidTutorialFeedYourWorms) {
        this.androidTutorialFeedYourWorms = androidTutorialFeedYourWorms;
    }

    public void setWorldSize(int x, int y) {
        this.worldSizeX = x;
        this.worldSizeY = y;
    }

    public void moveWorms(Worm[] worms, float millis) {
        for (int i = 0; i < worms.length; i++) {
            dragWorm(worms[i], millis);
        }
    }

    public void calculateIntersections(Worm[] worms, ArrayList<Food> foods) {
        for (int i = 0; i < worms.length; i++) {
            Worm worm = worms[i];

            for (int j = 0; j < foods.size(); j++) {
                Food food = foods.get(j);
                float distance = PApplet.sqrt((worm.sx[0] - food.x) * (worm.sx[0] - food.x) +
                        (worm.sy[0] - food.y) * (worm.sy[0] - food.y));
                if (distance < food.radius) {
                    PApplet.println("Consume food!");
                    food.consume();
                    worm.addSegment();
                }
            }
        }
    }

    private void dragWorm(Worm worm, float millis) {
        float timeFrame = millis / 1000;
        float angle = getRightAngle(worm) + getAngleShift(worm, timeFrame);

        float distance = timeFrame * AndroidTutorial_FeedYourWorms.VELOCITY;
        float nextX = worm.sx[0] + PApplet.cos(angle) * distance;
        float nextY = worm.sy[0] + PApplet.sin(angle) * distance;
        worm.drag(nextX, nextY);
    }

    public float getAngleShift(Worm worm, float timeFrame) {
        float angleShift = PConstants.PI * PApplet.sin(worm.initialPeriod +
                ((float) androidTutorialFeedYourWorms.millis() / worm.wavePeriod)) * timeFrame;
        return angleShift;
    }

    public float getRightAngle(Worm worm) {
        float dx = worm.sx[0] - worm.sx[1];
        float dy = worm.sy[0] - worm.sy[1];

        if (isInCorner(worm)) {
            if (worm.preferedCornerRotation == AndroidTutorial_FeedYourWorms.CORNER_ROTATION_UNDEFINED) {
                boolean isDxBigger = PApplet.abs(dx) > PApplet.abs(dy);
                if (isDxBigger) {
                    worm.preferedCornerRotation = AndroidTutorial_FeedYourWorms.CORNER_ROTATION_OY;
                } else {
                    worm.preferedCornerRotation = AndroidTutorial_FeedYourWorms.CORNER_ROTATION_OX;
                }
            }
            if (worm.preferedCornerRotation == AndroidTutorial_FeedYourWorms.CORNER_ROTATION_OY) {
                dx = 0.01f;//move OY
                if (isTopWall(worm, androidTutorialFeedYourWorms.CORNER_CHECK_COEF)) {
                    dy = 1; //DOWN
                } else {
                    dy = -1; //UP
                }
            } else {
                dy = 0.01f;//move OX
                if (isLeftWall(worm, androidTutorialFeedYourWorms.CORNER_CHECK_COEF)) {
                    dx = 1; //RIGHT
                } else {
                    dx = -1;//LEFT
                }
            }
        } else {
            worm.preferedCornerRotation = AndroidTutorial_FeedYourWorms.CORNER_ROTATION_UNDEFINED;
            if (isLeftWall(worm, androidTutorialFeedYourWorms.WALL_CHECK_COEF)) {
                dx = 0.01f;//move OY
            } else if (isRightWall(worm, androidTutorialFeedYourWorms.WALL_CHECK_COEF)) {
                dx = -0.01f; //move OY
            }
            if (isTopWall(worm, androidTutorialFeedYourWorms.WALL_CHECK_COEF)) {
                dy = 0.01f; //move OX
            } else if (isBottomWall(worm, androidTutorialFeedYourWorms.WALL_CHECK_COEF)) {
                dy = -0.01f; //move OX
            }
        }

        return PApplet.atan2(dy, dx);
    }

    private boolean isInCorner(Worm worm) {
        int closeWalls = 0;

        if (isLeftWall(worm, androidTutorialFeedYourWorms.CORNER_CHECK_COEF)) {
            closeWalls++;
        } else if (isRightWall(worm, androidTutorialFeedYourWorms.CORNER_CHECK_COEF)) {
            closeWalls++;
        }
        if (isTopWall(worm, androidTutorialFeedYourWorms.CORNER_CHECK_COEF)) {
            closeWalls++;
        } else if (isBottomWall(worm, androidTutorialFeedYourWorms.CORNER_CHECK_COEF)) {
            closeWalls++;
        }

        return closeWalls == 2;
    }

    private boolean isLeftWall(Worm worm, int coef) {
        return worm.sx[0] < coef * androidTutorialFeedYourWorms.STROKE_WIDTH;
    }

    private boolean isRightWall(Worm worm, int coef) {
        return worm.sx[0] > worldSizeX - coef * androidTutorialFeedYourWorms.STROKE_WIDTH;
    }

    private boolean isTopWall(Worm worm, int coef) {
        return worm.sy[0] < coef * androidTutorialFeedYourWorms.STROKE_WIDTH;
    }

    private boolean isBottomWall(Worm worm, int coef) {
        return worm.sy[0] > worldSizeY - coef * androidTutorialFeedYourWorms.STROKE_WIDTH;
    }
}
