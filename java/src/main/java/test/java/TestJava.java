package test.java;

import playn.java.LWJGLPlatform;

import test.core.Test;

public class TestJava {

  public static void main (String[] args) {
    LWJGLPlatform.Config config = new LWJGLPlatform.Config();
    // use config to customize the Java platform, if needed
    LWJGLPlatform plat = new LWJGLPlatform(config);
    new Test(plat);
    plat.start();
  }
}
