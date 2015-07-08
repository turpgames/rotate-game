package com.turpgames.rotategame.objects;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.effects.flash.FlashEffect;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.ShapeDrawer;
import com.turpgames.rotategame.components.texts.Last5Text;
import com.turpgames.rotategame.components.texts.NormalText;
import com.turpgames.rotategame.controller.MasterPlayController;
import com.turpgames.rotategame.utils.R;

public class LevelTimer implements IDrawable {
	private static float SCALE = 1;
	
	private MasterPlayController parent;
	
	private float secondCounter;
	private float currTime;
	private float mapTime;
	
	private NormalText timeText;
	private Last5Text last5Text;
	private FlashEffect last5Flash;
	
	private boolean timerIsStopped;
	
	public LevelTimer(MasterPlayController parent) {
		this.parent = parent;
		
		timeText = new NormalText();
		timeText.setText("");
		timeText.setAlignment(Text.HAlignRight, Text.VAlignBottom);
		timeText.setPadY(R.MAPOFFSETY + R.BLOCKSIZE * R.ROWNUMBER + 25);
		timeText.getColor().set(R.Colors.BLOCKCOLOR);
		//timeText.setAlignment(Text.HAlignCenter, Text.VAlignTop);
		
		last5Text = new Last5Text();
		last5Text.setText("");
		last5Text.setFontScale(2);
		last5Text.getColor().set(R.Colors.BLOCKCOLOR);
		last5Text.getColor().a = 0.45f;
		last5Text.setAlignment(Text.HAlignCenter, Text.VAlignBottom);
		last5Text.setPadY(R.MAPOFFSETY);
		
		last5Flash = new FlashEffect(last5Text, R.Colors.LAST5SECCOLOR, 7);
		last5Flash.setDuration(10000);
		last5Flash.start();
		
		this.timerIsStopped = true;
	}
	
	public void stop() {
		timerIsStopped = true;
	}
	
	public void start(float mapTime) {
		this.mapTime = mapTime;
		this.currTime = mapTime;
		this.secondCounter = 1;
		this.timerIsStopped = false;
	}
	
	public void update(float elapsed) {
		if (!timerIsStopped) {
			currTime -= elapsed;
			SCALE = currTime / mapTime;
			
			if (currTime < 0) {
				currTime = 0;
				parent.levelLost();
			}
			
			secondCounter -= elapsed;
			if (secondCounter < 0) {
				secondCounter = 1;
				parent.secondPassed();
			}

			Integer time = (int) Math.ceil(currTime);
			timeText.setText(time.toString());
			last5Text.setText(time.toString());
		}
	}
	
	@Override
	public void draw() {
		if (currTime < 9) {
			last5Text.draw();
		}
		//timeText.draw();
		
		// Frame
		ShapeDrawer.drawRect(R.LEVELFRAMEOFFSETX, R.LEVELFRAMEOFFSETY, R.LEVELFRAMEWIDTH, R.BARWIDTH, R.Colors.LEVELFRAMECOLOR, true, false);
		ShapeDrawer.drawRect(R.LEVELFRAMEOFFSETX, R.LEVELFRAMEOFFSETY + R.LEVELFRAMEHEIGHT - R.BARWIDTH, R.LEVELFRAMEWIDTH, R.BARWIDTH, R.Colors.LEVELFRAMECOLOR, true, false);
		ShapeDrawer.drawRect(R.LEVELFRAMEOFFSETX, R.LEVELFRAMEOFFSETY, R.BARWIDTH, R.LEVELFRAMEHEIGHT, R.Colors.LEVELFRAMECOLOR, true, false);
		ShapeDrawer.drawRect(R.LEVELFRAMEOFFSETX + R.LEVELFRAMEWIDTH - R.BARWIDTH, R.LEVELFRAMEOFFSETY, R.BARWIDTH, R.LEVELFRAMEHEIGHT, R.Colors.LEVELFRAMECOLOR, true, false);

		// Timer bars
		ShapeDrawer.drawRect(R.LEVELFRAMEOFFSETX + (1 - SCALE) * R.LEVELFRAMEWIDTH / 2, R.LEVELFRAMEOFFSETY, R.LEVELFRAMEWIDTH * SCALE, R.BARWIDTH, R.Colors.TIMERBARCOLOR, true, false);
		ShapeDrawer.drawRect(R.LEVELFRAMEOFFSETX + (1 - SCALE) * R.LEVELFRAMEWIDTH / 2, R.LEVELFRAMEOFFSETY + R.LEVELFRAMEHEIGHT - R.BARWIDTH, R.LEVELFRAMEWIDTH * SCALE, R.BARWIDTH, R.Colors.TIMERBARCOLOR, true, false);
		ShapeDrawer.drawRect(R.LEVELFRAMEOFFSETX, R.LEVELFRAMEOFFSETY + (1 - SCALE) * R.LEVELFRAMEHEIGHT / 2, R.BARWIDTH, R.LEVELFRAMEHEIGHT * SCALE, R.Colors.TIMERBARCOLOR, true, false);
		ShapeDrawer.drawRect(R.LEVELFRAMEOFFSETX + R.LEVELFRAMEWIDTH - R.BARWIDTH, R.LEVELFRAMEOFFSETY + (1 - SCALE) * R.LEVELFRAMEHEIGHT / 2, R.BARWIDTH, R.LEVELFRAMEHEIGHT * SCALE, R.Colors.TIMERBARCOLOR, true, false);
	}
}
