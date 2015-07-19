package com.turpgames.rotategame.components;

import com.turpgames.framework.v0.component.Button;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.ShapeDrawer;
import com.turpgames.rotategame.utils.R;

public class BackButton extends Button {
	private static float U = R.BUTTONSIZE / 12;
	
	public BackButton() {
		super();
		setWidth(R.BUTTONSIZE);
		setHeight(R.BUTTONSIZE);
	}
	
	@Override
	protected void onDraw() {
		drawRect(0, 0, 12*U, 12*U, R.Colors.BUTTONCOLOR);
		drawRect(7*U, 4*U, 4*U, 4*U, R.Colors.BLOCKCOLOR);
		drawRect(5*U, U, 2*U, 10*U, R.Colors.BLOCKCOLOR);
		drawRect(4*U, 2*U, U, 8*U, R.Colors.BLOCKCOLOR);
		drawRect(3*U, 3*U, U, 6*U, R.Colors.BLOCKCOLOR);
		drawRect(2*U, 4*U, U, 4*U, R.Colors.BLOCKCOLOR);
		drawRect(U, 5*U, U, 2*U, R.Colors.BLOCKCOLOR);
		
		if (isTouched())
			drawRect(0,0,12*U,12*U,R.Colors.BLOCKCOLOR);
	}
	
	private void drawRect(float x, float y, float w, float h, Color color) {
		ShapeDrawer.drawRect(getLocation().x + x, getLocation().y + y, w, h, color, true, true);
	}
	
	@Override
	public void registerSelf() {
		Game.getInputManager().register(this, Game.LAYER_SCREEN);
	}
}
