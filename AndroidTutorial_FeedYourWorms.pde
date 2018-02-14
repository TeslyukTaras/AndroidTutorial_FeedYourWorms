int STROKE_WIDTH = 20;
int WORM_COUNT = 50;

Worm[] worms = new Worm[WORM_COUNT];
WormController wormController = new WormController();
ArrayList<Food> foods = new ArrayList<Food>();
int lastTime = 0;

void setup() {
  //size(640, 360);//фіксований розмір
  fullScreen();//додати у випадку Android
  wormController.setWorldSize(width, height);
  for (int i=0; i<WORM_COUNT; i++) {
    worms[i] = new Worm((int) random(0, width), (int) random(0, height), random(-PI, PI));
  }
  
  strokeWeight(STROKE_WIDTH);
  setupBackground();
}

void draw() {
  setBackground();
  int currentTime = millis();
  int timeDifference = currentTime - lastTime;

  wormController.moveWorms(worms, timeDifference);
  wormController.calculateIntersections(worms, foods);

  for (int i = 0; i< foods.size(); i++) {
    if (foods.get(i).resource <=0) {
      foods.remove(i);
      break;
    }
  }

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

//розкоментувати у випадку Android
//void touchStarted() {
//  for (int i = 0; i < touches.length; i++) {
//    Food food = new Food((int) touches[i].x, (int)touches[i].y);
//    foods.add(food);
//  }
//}