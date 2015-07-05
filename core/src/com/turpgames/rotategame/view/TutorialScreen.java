package com.turpgames.rotategame.view;

import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.rotategame.components.texts.LargeText;
import com.turpgames.rotategame.utils.R;

public class TutorialScreen extends Screen {

	public void init() {
		super.init();
		
		LargeText t = new LargeText();
		t.setText("Click to rotate parts.\nConnect all parts\nso no jagged edges remain.");
		t.getColor().set(R.Colors.BLOCKCOLOR);
		t.setAlignment(Text.HAlignCenter, Text.VAlignTop);
		t.setPadY(Game.getVirtualHeight()/9);
		registerDrawable(t, Game.LAYER_BACKGROUND);
		
		t = new LargeText();
		t.setText("Solve all levels to unlock the \n master mode...");
		t.getColor().set(R.Colors.BLOCKCOLOR);
		t.setAlignment(Text.HAlignCenter, Text.VAlignBottom);
		t.setPadY(Game.getVirtualHeight()/9);
		registerDrawable(t, Game.LAYER_BACKGROUND);
	}
}
