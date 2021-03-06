package com.turpgames.rotategame.view;

import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.rotategame.components.ArcadeButton;
import com.turpgames.rotategame.components.texts.NormalText;
import com.turpgames.rotategame.utils.R;
import com.turpgames.rotategame.utils.R.Screens;

public class TutorialScreen extends BaseScreen {
	private ArcadeButton btnPlay;
	private NormalText t;
	@Override
	public void init() {
		super.init();
		
		t = new NormalText();
		t.setText("Click to rotate and\nconnect all parts.\n\nSolve n levels for\neach size nxn\n\n" +
				"(n * " + R.LEVELTIMEDEC / 2 + ") seconds\nfor each level.\n\nCan you reach\n8x8?");
		t.getColor().set(R.Colors.COLOR1);
		t.setAlignment(Text.HAlignCenter, Text.VAlignTop);
		t.setPadY(R.UNIT * 4);
		registerDrawable(t, Game.LAYER_BACKGROUND);
		
		btnPlay = MenuScreen.createButton(Screens.play, "Cont.", R.UNIT * 3);
		registerDrawable(btnPlay, Game.LAYER_BACKGROUND);
	}

	public void updateColor() {
		t.getColor().set(R.Colors.COLOR1);
	}
	
	@Override
	protected void onAfterActivate() {
		btnPlay.activate();
		updateColor();
		super.onAfterActivate();
	}
	
	@Override
	protected void onAfterDeactivate() {
		btnPlay.deactivate();
		super.onAfterDeactivate();
	}
	
	@Override
	protected boolean onBack() {
		ScreenManager.instance.switchTo(R.Screens.menu, false);
		return super.onBack();
	}
}
