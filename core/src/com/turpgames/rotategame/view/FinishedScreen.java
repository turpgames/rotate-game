package com.turpgames.rotategame.view;

import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.rotategame.components.ArcadeButton;
import com.turpgames.rotategame.components.texts.NormalText;
import com.turpgames.rotategame.utils.R;
import com.turpgames.rotategame.utils.R.Screens;

public class FinishedScreen extends BaseScreen {
	private ArcadeButton btnAbout;
	private NormalText t;
	
	@Override
	public void init() {
		super.init();
		
		t = new NormalText();
		t.setText("WOW!\nDidn't expect anybody\nto make it this far!\n\nA big\nCONGRATULATIONS\nis in order.\n\n " +
				"Thank you for playing");
		t.getColor().set(R.Colors.COLOR1);
		t.setAlignment(Text.HAlignCenter, Text.VAlignTop);
		t.setPadY(R.UNIT * 4);
		registerDrawable(t, Game.LAYER_BACKGROUND);
		
		btnAbout = MenuScreen.createButton(Screens.about, "Credits", R.UNIT * 3);
		registerDrawable(btnAbout, Game.LAYER_BACKGROUND);
	}
	
	@Override
	protected void onAfterActivate() {
		btnAbout.activate();
		t.getColor().set(R.Colors.COLOR1);
		super.onAfterActivate();
	}
	
	@Override
	protected void onAfterDeactivate() {
		btnAbout.deactivate();
		super.onAfterDeactivate();
	}
	
	@Override
	protected boolean onBack() {
		ScreenManager.instance.switchTo(R.Screens.menu, false);
		return super.onBack();
	}
}
