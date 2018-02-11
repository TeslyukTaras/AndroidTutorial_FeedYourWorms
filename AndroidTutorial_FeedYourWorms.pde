int STROKE_WIDTH = 20;

Worm worm1;

void setup() {
  size(640, 360);
  worm1 = new Worm((int) random(0, width), (int) random(0, height), random(-PI, PI));
  
  strokeWeight(STROKE_WIDTH);
}

void draw() {
  background(0);
  worm1.drawWorm();
}