package com.turpgames.rotategame.components.texts;

public class LevelResultText extends NormalText {
	private static float TIME = 6; 
	private float timer;
	
	@Override
	public void setText(String text) {
		super.setText(text);
		timer = TIME;
	}
	
	public void update(float elapsed) {
		if (timer == 0)
			return;
		timer -= elapsed;
		if (timer < 0) {
			setText("");
			timer = 0;
		}
	}
}
