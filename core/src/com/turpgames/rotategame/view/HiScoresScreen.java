package com.turpgames.rotategame.view;

import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.rotategame.components.GameLogo;

public class HiScoresScreen extends Screen {

	public void init() {
		super.init();
		registerDrawable(new GameLogo(), Game.LAYER_GAME);
	}
	
}
