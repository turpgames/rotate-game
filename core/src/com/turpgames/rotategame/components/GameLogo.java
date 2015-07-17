package com.turpgames.rotategame.components;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.rotategame.components.texts.XXLargeText;
import com.turpgames.rotategame.utils.R;

public class GameLogo implements IDrawable {
	private final Text title;
	
	public GameLogo() {
		title = new XXLargeText();
		title.setAlignment(Text.HAlignCenter, Text.VAlignTop);
		title.setPadY(Game.getVirtualHeight() / 10);
		title.setText("Rot8");
		title.getColor().set(R.Colors.BLOCKCOLOR);
	}
	
	@Override
	public void draw() {
		title.draw();
	}
}
