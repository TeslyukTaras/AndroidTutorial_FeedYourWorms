// backgroud colors
float currR, currG, currB;
float nextR, nextG, nextB;
float easing = 0.001;
int lastChange = 0;

void setupBackground() {
  currR = currG = currB = 25;
  pickNextColor();
}

void setBackground() {
  background(currR, currG, currB, 100);
  updateCurrColor();
  if (5000 < millis() - lastChange) { 
    pickNextColor();
    lastChange = millis();
  }
}

void pickNextColor() {
  nextG = random(128);
  nextG = random(128);
  nextB = random(128);
}

void updateCurrColor() {
  // Easing between current and next colors
  currR += easing * (nextR - currR);
  currG += easing * (nextG - currG);
  currB += easing * (nextB - currB);
}