package com.turpgames.rotategame.view;

import com.turpgames.framework.v0.ITexture;
import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.component.ImageButton;
import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.rotategame.components.GameLogo;
import com.turpgames.rotategame.utils.R.Screens;
import com.turpgames.rotategame.utils.R.Textures;

public class AboutScreen extends Screen {
	private final static float buttonSize = 128f;
	private final static int buttonPerRow = 3;
	
	private AboutButton facebookButton;
	private AboutButton twitterButton;
	private AboutButton webSiteButton;

	@Override
	public void init() {
		super.init();

		initVersionText();
		initFacebookButton();
		initTwitterButton();
		initWebSiteButton();

		registerDrawable(new GameLogo(), Game.LAYER_SCREEN);
	}

	private void initVersionText() {
		Text text = new Text();
		text.setText("v" + Game.getVersion());
		text.setFontScale(0.66f);
		text.setAlignment(Text.HAlignCenter, Text.VAlignTop);
		text.setPadY(125f);
		registerDrawable(text, Game.LAYER_SCREEN);
	}
	
	private float getX(int i) {
		float dx = (Game.getVirtualWidth() - buttonPerRow * buttonSize) / (buttonPerRow + 1);
		return (i + 1) * dx + buttonSize * i;
	}

	private void initFacebookButton() {
		facebookButton = createButton(Textures.facebookbutton, Game.getParam("facebook-address"), buttonSize, getX(0), 400f);
		registerDrawable(facebookButton, Game.LAYER_SCREEN);
	}

	private void initTwitterButton() {
		twitterButton = createButton(Textures.twitterbutton, Game.getParam("twitter-address"), buttonSize, getX(1), 400f);
		registerDrawable(twitterButton, Game.LAYER_SCREEN);
	}

	private void initWebSiteButton() {
		webSiteButton = createButton(Textures.turpbutton, Game.getParam("turp-address"), buttonSize, getX(2), 400f);
		registerDrawable(webSiteButton, Game.LAYER_SCREEN);

	}

	private static AboutButton createButton(ITexture texture, final String url, float size, float x, float y) {
		AboutButton btn = new AboutButton();
		btn.setTexture(texture);
		btn.setWidth(size);
		btn.setHeight(size);

		btn.getLocation().set(x, y);
		btn.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				Game.openUrl(url);
			}
		});

		return btn;
	}

	@Override
	protected void onAfterActivate() {
		facebookButton.activate();
		twitterButton.activate();
		webSiteButton.activate();
	}

	@Override
	protected boolean onBeforeDeactivate() {
		facebookButton.deactivate();
		twitterButton.deactivate();
		webSiteButton.deactivate();

		return super.onBeforeDeactivate();
	}

	protected boolean onBack() {
		ScreenManager.instance.switchTo(Screens.menu, true);
		return true;
	}

	private static class AboutButton extends ImageButton {
		@Override
		public boolean ignoreViewport() {
			return false;
		}
	}
}
