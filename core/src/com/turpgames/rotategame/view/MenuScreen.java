package com.turpgames.rotategame.view;

import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.rotategame.components.ArcadeButton;
import com.turpgames.rotategame.components.GameLogo;
import com.turpgames.rotategame.utils.R;
import com.turpgames.rotategame.utils.R.Screens;

public class MenuScreen extends Screen {
	private ArcadeButton btnPlay, btnAbout; 
	
	@Override
	public void init() {
		super.init();
		btnPlay = createButton(Screens.tutorial, "Play", R.MENUBUTTONSOFFSETX + 2 * R.BUTTONHEIGHT + 2 * R.MENUBUTTONSPACE);		
		btnAbout = createButton(Screens.about, "About", R.MENUBUTTONSOFFSETX);
		GameLogo logo = new GameLogo();
		registerDrawable(logo, Game.LAYER_BACKGROUND);
		registerDrawable(btnPlay, Game.LAYER_BACKGROUND);
		registerDrawable(btnAbout, Game.LAYER_BACKGROUND);
	}
	
	@Override
	protected void onAfterActivate() {
		btnPlay.activate();
		btnAbout.activate();
		super.onAfterActivate();
	}
	
	@Override
	protected void onAfterDeactivate() {
		btnPlay.deactivate();
		btnAbout.deactivate();
		super.onAfterDeactivate();
	}
	
	protected static ArcadeButton createButton(final String screen, final String label, float yOffset) {
		ArcadeButton btn = new ArcadeButton(label);
		
		btn.setLocation((Game.getVirtualWidth() - btn.getWidth()) / 2, yOffset);
		btn.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				ScreenManager.instance.switchTo(screen, false);
			}
		});
		return btn;
	}
	
	@Override
	protected boolean onBack() {
		Game.exit();
		return super.onBack();
	}
}
