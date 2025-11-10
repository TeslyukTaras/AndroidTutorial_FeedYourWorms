package processing.test.androidtutorial_feedyourworms;

class Food {
    private final AndroidTutorial_FeedYourWorms androidTutorialFeedYourWorms;
    int x;
    int y;
    int radius = AndroidTutorial_FeedYourWorms.INITIAL_FOOD_RADIUS;
    int resource = AndroidTutorial_FeedYourWorms.INITIAL_FOOD_SIZE;

    Food(AndroidTutorial_FeedYourWorms androidTutorialFeedYourWorms, int x, int y) {
        this.androidTutorialFeedYourWorms = androidTutorialFeedYourWorms;
        this.x = x;
        this.y = y;
    }

    public void drawFood() {
        androidTutorialFeedYourWorms.fill(255);
        androidTutorialFeedYourWorms.ellipse(x, y, radius, radius);
    }

    public void consume() {
        radius = (int) ((float) radius * 0.75f);
        resource--;
    }
}
