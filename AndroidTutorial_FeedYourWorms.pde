int STROKE_WIDTH = 20;

Worm worm1;
WormController wormController = new WormController();
int lastTime = 0;

void setup() {
  size(640, 360);
  wormController.setWorldSize(width, height);
  worm1 = new Worm((int) random(0, width), (int) random(0, height), random(-PI, PI));
  
  strokeWeight(STROKE_WIDTH);
}

void draw() {
  background(0);
  int currentTime = millis();
  int timeDifference = currentTime - lastTime;

  wormController.dragWorm(worm1, timeDifference);
  worm1.drawWorm();

  lastTime = currentTime;
}