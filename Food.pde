final int INITIAL_FOOD_SIZE = 5;
final int INITIAL_FOOD_RADIUS = 30;
class Food {
  int x;
  int y;
  int radius = INITIAL_FOOD_RADIUS;
  int resource = INITIAL_FOOD_SIZE;

  Food(int x, int y) {
    this.x = x;
    this.y = y;
  }

  void drawFood() {
    fill(255);
    ellipse(x, y, radius, radius);
  }

  void consume() {
    radius = (int) ((float)radius * 0.75);
    resource--;
  }
}