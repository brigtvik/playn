package test.core;

import playn.core.*;
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
			y += 150;
			for(float scale = 0.7f; scale < 1.3f; scale += 0.1f)
			{
				float width = image.width() * scale;
				ImageLayer layer = new ImageLayer(image);
				layer.setScale(scale, scale);
				layer.setTranslation(x, y);
				game.rootLayer.add(layer);
				x += width;
			}

			//Add TextureSurface layers at the same scales, not visible
			x = 0;
			y += 150;
			for(float scale = 0.7f; scale < 1.3f; scale += 0.1f)
			{
				float width = image.width() * scale;
				float height = image.height() * scale;
				TextureSurface surface = new TextureSurface(game.plat.graphics(), game.defaultBatch, width, height);
//				surface.drawLine(0, 0, width, 0, 1);
//				surface.drawLine(0, 0, 0, height, 1);
//				surface.drawLine(0, height, width, height, 1);
//				surface.drawLine(width, 0, width, height, 1);
				surface.draw(image.tile(), 0, 0, width, height);
				ImageLayer layer = new ImageLayer(surface.texture);
				layer.setTranslation(x, y);
				game.rootLayer.add(layer);
				x += width;
			}
		}
	}
}
