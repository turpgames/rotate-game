package com.turpgames.rotategame.view;

import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.rotategame.components.ArcadeButton;
import com.turpgames.rotategame.components.texts.NormalText;
import com.turpgames.rotategame.utils.R;
import com.turpgames.rotategame.utils.R.Screens;

public class TutorialScreen extends Screen {
	private ArcadeButton btnPlay;
	
	@Override
	public void init() {
		super.init();
		
		NormalText t = new NormalText();
		t.setText("Click to rotate and\nconnect all parts.\n\nSolve levels for\neach size\nfrom 2x2 to 8x8.\n\n" +
				"You get extra \n(size * " + R.LEVELTIMEDEC / 2 + ") seconds\nfor each level.");
		t.getColor().set(R.Colors.BLOCKCOLOR);
		t.setAlignment(Text.HAlignCenter, Text.VAlignTop);
		t.setPadY(R.UNIT * 4);
		registerDrawable(t, Game.LAYER_BACKGROUND);
		
		btnPlay = MenuScreen.createButton(Screens.play, "Cont.", R.UNIT * 3);
		registerDrawable(btnPlay, Game.LAYER_BACKGROUND);
	}
	
	@Override
	protected void onAfterActivate() {
		btnPlay.activate();
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
