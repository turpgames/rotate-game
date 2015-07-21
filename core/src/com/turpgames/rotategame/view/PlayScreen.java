package com.turpgames.rotategame.view;

import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.rotategame.controller.MasterPlayController;
import com.turpgames.rotategame.utils.R;

public class PlayScreen extends BaseScreen implements IScreenView {

	private MasterPlayController controller;
	
	public void init() {
		super.init();
		controller = new MasterPlayController();
	}

	@Override
	protected void onAfterActivate() {
		controller.activate();
		registerDrawable(controller, Game.LAYER_GAME);
		registerInputListener(controller);
	}

	@Override
	protected boolean onBeforeDeactivate() {
		controller.deactivate();
		unregisterDrawable(controller);
		unregisterInputListener(controller);
		return super.onBeforeDeactivate();
	}

	@Override
	public void update() {
		super.update();
		controller.update();
	}
	
	@Override
	protected boolean onBack() {
		ScreenManager.instance.switchTo(R.Screens.menu, false);
		return super.onBack();
	}
}
