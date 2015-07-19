package com.turpgames.rotategame.components;

import com.turpgames.framework.v0.component.Button;
import com.turpgames.framework.v0.effects.flash.IFlashEffectSubject;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.ShapeDrawer;
import com.turpgames.rotategame.utils.R;

public class RestartButton extends Button implements IFlashEffectSubject {
	private static float U = R.LEVELSIZE / (5 * 12);
	
	public RestartButton() {
		super();
		setWidth(R.BUTTONSIZE);
		setHeight(R.BUTTONSIZE);
	}
	
	@Override
	protected void onDraw() {
		drawRect(0, 0, 12*U, 12*U, R.Colors.BUTTONCOLOR);
		drawRect(U, 3*U, 2*U, 2*U, R.Colors.BLOCKCOLOR);
		
		drawRect(2*U, 2*U, 2*U, 2*U, R.Colors.BLOCKCOLOR);
		drawRect(3*U, U, 2*U, 2*U, R.Colors.BLOCKCOLOR);
		drawRect(5*U, U, 2*U, 2*U, R.Colors.BLOCKCOLOR);
		drawRect(6*U, U, 2*U, 2*U, R.Colors.BLOCKCOLOR);
		drawRect(7*U, 2*U, 2*U, 2*U, R.Colors.BLOCKCOLOR);
		drawRect(8*U, 3*U, 2*U, 2*U, R.Colors.BLOCKCOLOR);
		
		drawRect(7*U, 5*U, 4*U, U, R.Colors.BLOCKCOLOR);
		drawRect(10*U, 2*U, U, 4*U, R.Colors.BLOCKCOLOR);
		
		drawRect(2*U, 7*U, 2*U, 2*U, R.Colors.BLOCKCOLOR);
		drawRect(3*U, 8*U, 2*U, 2*U, R.Colors.BLOCKCOLOR);
		drawRect(4*U, 9*U, 2*U, 2*U, R.Colors.BLOCKCOLOR);
		drawRect(6*U, 9*U, 2*U, 2*U, R.Colors.BLOCKCOLOR);
		drawRect(7*U, 9*U, 2*U, 2*U, R.Colors.BLOCKCOLOR);
		drawRect(8*U, 8*U, 2*U, 2*U, R.Colors.BLOCKCOLOR);
		drawRect(9*U, 7*U, 2*U, 2*U, R.Colors.BLOCKCOLOR);

		drawRect(U, 6*U, U, 4*U, R.Colors.BLOCKCOLOR);
		drawRect(U, 6*U, 4*U, U, R.Colors.BLOCKCOLOR);
		
		if (isTouched())
			drawRect(0,0,12*U,12*U,R.Colors.BLOCKCOLOR);
	}
	
	private void drawRect(float x, float y, float w, float h, Color color) {
		ShapeDrawer.drawRect(getLocation().x + x, getLocation().y + y, w, h, color, true, true);
	}
	
	@Override
	public void setColor(float r, float g, float b, float a) {
		setDefaultColor(new Color(r,g,b,a));
	}
	
	@Override
	public void registerSelf() {
		Game.getInputManager().register(this, Game.LAYER_SCREEN);
	}
}
