package com.turpgames.rotategame.components;

import com.turpgames.framework.v0.component.Button;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.ShapeDrawer;
import com.turpgames.framework.v0.util.Timer;
import com.turpgames.framework.v0.util.Timer.ITimerTickListener;
import com.turpgames.rotategame.utils.R;

public class RestartButton extends Button {
	private static float U = R.LEVELSIZE / (5 * 12);
	private static Color color1 = R.Colors.COLOR1;
	private static Color color2 = R.Colors.COLOR2;

	private Timer timer;
	
	public RestartButton() {
		super();
		setWidth(R.BUTTONSIZE);
		setHeight(R.BUTTONSIZE);
		getLocation().x = Game.getVirtualWidth() - R.BUTTONSPACING - R.BUTTONSIZE;
		getLocation().y = Game.getVirtualHeight() - R.BUTTONSPACING - R.BUTTONSIZE;

		color1 = new Color(R.Colors.COLOR1);
		color2 = new Color(R.Colors.COLOR2);
		
		timer = new Timer();
		timer.setInterval(0.2f);
		timer.setTickListener(new ITimerTickListener() {
			@Override
			public void timerTick(Timer timer) {
				switchColors();
			}
		});
	}

	private void switchColors() {
		Color c = new Color(color1);
		color1.set(color2);
		color2.set(c);
	}
	
	public void updateColor() {
		color1.set(R.Colors.COLOR1);
		color2.set(R.Colors.COLOR2);
	}
	
	@Override
	public boolean ignoreViewport() {
		return false;
	}
	
	@Override
	protected void onDraw() {
		drawRect(0, 0, 12*U, 12*U, color2);
		drawRect(U, 3*U, 2*U, 2*U, color1);
		
		drawRect(2*U, 2*U, 2*U, 2*U, color1);
		drawRect(3*U, U, 2*U, 2*U, color1);
		drawRect(5*U, U, 2*U, 2*U, color1);
		drawRect(6*U, U, 2*U, 2*U, color1);
		drawRect(7*U, 2*U, 2*U, 2*U, color1);
		drawRect(8*U, 3*U, 2*U, 2*U, color1);
		
		drawRect(7*U, 5*U, 4*U, U, color1);
		drawRect(10*U, 2*U, U, 4*U, color1);
		
		drawRect(2*U, 7*U, 2*U, 2*U, color1);
		drawRect(3*U, 8*U, 2*U, 2*U, color1);
		drawRect(4*U, 9*U, 2*U, 2*U, color1);
		drawRect(6*U, 9*U, 2*U, 2*U, color1);
		drawRect(7*U, 9*U, 2*U, 2*U, color1);
		drawRect(8*U, 8*U, 2*U, 2*U, color1);
		drawRect(9*U, 7*U, 2*U, 2*U, color1);

		drawRect(U, 6*U, U, 4*U, color1);
		drawRect(U, 6*U, 4*U, U, color1);
		
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

	public void startFlash() {
		timer.start();
	}

	public void stopFlash() {
		timer.stop();
		color1.set(R.Colors.COLOR1);
		color2.set(R.Colors.COLOR2);
	}
}
