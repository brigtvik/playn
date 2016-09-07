package test.core;

import playn.core.Canvas;
import playn.core.Image;
import playn.core.Platform;
import playn.scene.CanvasLayer;
import playn.scene.ImageLayer;
import playn.scene.SceneGame;
import react.Slot;

public class Test extends SceneGame
{
	public Test(Platform plat)
	{
		super(plat, 33); // update our "simulation" 33ms (30 times per second)

		Image testImage = plat.assets().getImage("images/user_add.png");
		testImage.state.onSuccess(new LoadThemUp(this));
	}

	private class LoadThemUp extends Slot<Image>
	{
		private Test game;

		public LoadThemUp(Test game)
		{
			this.game = game;
		}

		@Override
		public void onEmit(Image image)
		{
			//Add Canvas layers at different scales, looking bad
			float x = 0;
			float y = 0;
			for(float scale = 0.7f; scale < 1.3f; scale += 0.1f)
			{
				float width = image.width() * scale;
				float height = image.height() * scale;
				CanvasLayer layer = new CanvasLayer(game.plat.graphics(), width, height);
				Canvas canvas = layer.begin();
				canvas.draw(image, 0, 0, width, height);
				layer.end();
				layer.setTranslation(x, y);
				game.rootLayer.add(layer);
				x += width;
			}

			//Add Image layers at the same scales, looking good
			x = 0;
			y += 200;
			for(float scale = 0.7f; scale < 1.3f; scale += 0.1f)
			{
				float width = image.width() * scale;
				ImageLayer layer = new ImageLayer(image);
				layer.setScale(scale, scale);
				layer.setTranslation(x, y);
				game.rootLayer.add(layer);
				x += width;
			}
		}
	}
}
