int STROKE_WIDTH = 20;
int WORM_COUNT = 50;

Worm[] worms = new Worm[WORM_COUNT];
WormController wormController = new WormController();
ArrayList<Food> foods = new ArrayList<Food>();
int lastTime = 0;

void setup() {
  size(640, 360);
  wormController.setWorldSize(width, height);
  for (int i=0; i<WORM_COUNT; i++) {
    worms[i] = new Worm((int) random(0, width), (int) random(0, height), random(-PI, PI));
  }
  
  strokeWeight(STROKE_WIDTH);
}

void draw() {
  background(0);
  int currentTime = millis();
  int timeDifference = currentTime - lastTime;

  wormController.moveWorms(worms, timeDifference);

  for (int i = 0; i< worms.length; i++) {
    worms[i].drawWorm();
  }

  for (int i=0; i<foods.size(); i++) {
    foods.get(i).drawFood();
  }
  lastTime = currentTime;
}

void mouseClicked() {
  Food food = new Food(mouseX, mouseY);
  foods.add(food);
}