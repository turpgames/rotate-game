package com.turpgames.rotategame.view;

import com.turpgames.framework.v0.IResourceManager;
import com.turpgames.framework.v0.client.ConnectionManager;
import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Debug;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.ShapeDrawer;
import com.turpgames.rotategame.components.TurpLogo;
import com.turpgames.rotategame.updates.GameUpdateManager;
import com.turpgames.rotategame.utils.R.Colors;
import com.turpgames.rotategame.utils.R.Screens;

public class SplashScreen extends Screen {
	private Color progressColor;
	private IResourceManager resourceManager;

	@Override
	public void draw() {
		super.draw();
		float f = (Game.getVirtualWidth() - 50F) * resourceManager.getProgress();
		ShapeDrawer.drawRect((Game.getVirtualWidth() - f) / 2.0F, 100F, f, 20F, progressColor, true, false);
	}

	@Override
	public void init() {
		ConnectionManager.init();
		super.init();
		registerDrawable(new TurpLogo(), 0);
		progressColor = Colors.TURP_YELLOW;
		resourceManager = Game.getResourceManager();

		Debug.println(Game.getPhysicalScreenSize());
	}

	@Override
	public void update() {
		if (!resourceManager.isLoading()) {
			GameUpdateManager.runUpdates();
			switchToGame();
		}
	}

	private void switchToGame() {
		ScreenManager.instance.switchTo(Screens.menu, false);
	}
}
