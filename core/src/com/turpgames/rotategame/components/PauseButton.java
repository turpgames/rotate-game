package com.turpgames.rotategame.components;

import com.turpgames.framework.v0.component.Button;
import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.ShapeDrawer;
import com.turpgames.rotategame.utils.R;

public class PauseButton extends Button {
	private static float U = R.BUTTONSIZE / 12;
	private static Color color1 = R.Colors.COLOR1;
	private static Color color2 = R.Colors.COLOR2;
	
	public PauseButton() {
		super();
		setWidth(R.BUTTONSIZE);
		setHeight(R.BUTTONSIZE);
		getLocation().x = R.BUTTONSPACING;
		getLocation().y = Game.getVirtualHeight() - R.BUTTONSPACING - R.BUTTONSIZE;
		
		setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				ScreenManager.instance.switchTo(R.Screens.menu, false);
			}
		});
	}

	public void updateColor() {
		color1 = R.Colors.COLOR1;
		color2 = R.Colors.COLOR2;
	}
	
	@Override
	public boolean ignoreViewport() {
		return false;
	}
	
	@Override
	protected void onDraw() {
		drawRect(0, 0, 12*U, 12*U, color2);
		drawRect(1.5f*U, 2.5f*U, 4*U, 7*U, color1);
		drawRect(2.5f*U, 1.5f*U, 2*U, 9*U, color1);
		drawRect(6.5f*U, 2.5f*U, 4*U, 7*U, color1);
		drawRect(7.5f*U, 1.5f*U, 2*U, 9*U, color1);
		
		if (isTouched())
			drawRect(0,0,12*U,12*U,color1);
	}
	
	private void drawRect(float x, float y, float w, float h, Color color) {
		ShapeDrawer.drawRect(getLocation().x + x, getLocation().y + y, w, h, color, true, false);
	}
	
	@Override
	public void registerSelf() {
		Game.getInputManager().register(this, Game.LAYER_SCREEN);
	}
}
