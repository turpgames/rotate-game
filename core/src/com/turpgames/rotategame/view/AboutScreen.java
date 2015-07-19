package com.turpgames.rotategame.view;

import com.turpgames.framework.v0.ITexture;
import com.turpgames.framework.v0.component.Button;
import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.component.ImageButton;
import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.rotategame.components.ArcadeButton;
import com.turpgames.rotategame.components.GameLogo;
import com.turpgames.rotategame.components.texts.SmallText;
import com.turpgames.rotategame.utils.R;
import com.turpgames.rotategame.utils.R.Screens;
import com.turpgames.rotategame.utils.R.Textures;

public class AboutScreen extends Screen {
	private final static float buttonSize = 128f;
	private final static int buttonPerRow = 3;
	
	private AboutButton facebookButton;
	private AboutButton twitterButton;
	private AboutButton webSiteButton;
	private ImageButton paypalButton;
	private ArcadeButton btnMenu;

	@Override
	public void init() {
		super.init();

		initVersionText();
		initFacebookButton();
		initTwitterButton();
		initWebSiteButton();
		
		btnMenu = new ArcadeButton("Menu");
		btnMenu.setLocation((Game.getVirtualWidth() - btnMenu.getWidth()) / 2, R.UNIT * 3);
		btnMenu.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
//				levelWon();
				ScreenManager.instance.switchTo(R.Screens.menu, false);
			}
		});
		registerDrawable(btnMenu, Game.LAYER_BACKGROUND);

		registerDrawable(new GameLogo(), Game.LAYER_SCREEN);
		
		paypalButton = new ImageButton();
		paypalButton.setTexture(R.Textures.paypalbutton);
		paypalButton.setWidth(129);
		paypalButton.setHeight(47);
		paypalButton.setLocation(Button.AlignS, 0, R.UNIT * 10);
		paypalButton.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				Game.openUrl("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=P3GLEFM46FZFG");
			}
		});
		
		registerDrawable(paypalButton, Game.LAYER_BACKGROUND);
		
		SmallText text = new SmallText();
		text.getColor().set(R.Colors.BLOCKCOLOR);
		text.setText("Would you consider donating\nso we can continue making\ngames like this?\n<3 <3 <3");
		text.setAlignment(Text.HAlignCenter, Text.VAlignBottom);
		text.setPadding(0, R.UNIT * 24);
		registerDrawable(text, Game.LAYER_BACKGROUND);
	}

	private void initVersionText() {
		Text text = new SmallText();
		text.getColor().set(R.Colors.BLOCKCOLOR);
		text.setText("v" + Game.getVersion());
		text.setAlignment(Text.HAlignCenter, Text.VAlignTop);
		text.setPadY(R.UNIT * 18);
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
		paypalButton.activate();
		btnMenu.activate();
	}

	@Override
	protected boolean onBeforeDeactivate() {
		facebookButton.deactivate();
		twitterButton.deactivate();
		webSiteButton.deactivate();
		paypalButton.deactivate();
		btnMenu.deactivate();

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
