package test.android;

import playn.android.GameActivity;

import test.core.Test;

public class TestActivity extends GameActivity {

  @Override public void main () {
    new Test(platform());
  }
}
