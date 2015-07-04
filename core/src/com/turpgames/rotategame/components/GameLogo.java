package com.turpgames.rotategame.components;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.impl.Text;

public class GameLogo implements IDrawable {
	private final Text title;
	
	public GameLogo() {
		title = new Text();
		title.setFontScale(1.25f);
		title.setAlignment(Text.HAlignCenter, Text.VAlignTop);
		title.setPadY(75f);
		title.setText("Rotate");
	}
	
	@Override
	public void draw() {
		title.draw();
	}
}
