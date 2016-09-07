package test.html;

import com.google.gwt.core.client.EntryPoint;
import playn.html.HtmlPlatform;
import test.core.Test;

public class TestHtml implements EntryPoint {

  @Override public void onModuleLoad () {
    HtmlPlatform.Config config = new HtmlPlatform.Config();
    // use config to customize the HTML platform, if needed
    HtmlPlatform plat = new HtmlPlatform(config);
    plat.assets().setPathPrefix("test/");
    new Test(plat);
    plat.start();
  }
}
