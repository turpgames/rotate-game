package com.turpgames.rotategame.objects;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.effects.flash.FlashEffect;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.ShapeDrawer;
import com.turpgames.rotategame.components.texts.LargeText;
import com.turpgames.rotategame.components.texts.Last5Text;
import com.turpgames.rotategame.controller.MasterPlayController;
import com.turpgames.rotategame.utils.R;

public class LevelTimer implements IDrawable {
	private static float SCALE = 1;
	
	private MasterPlayController parent;
	
	private float currTime;
	private float totalTime;
	
	private LargeText timeText;
	private Last5Text last5Text;
	private FlashEffect last5Flash;
	
	private boolean isStopped;
	private boolean isFlashShown;
	
	public LevelTimer(MasterPlayController parent) {
		this.parent = parent;
		
		timeText = new LargeText();
		timeText.getColor().set(R.Colors.BLOCKCOLOR);
		timeText.setAlignment(Text.HAlignCenter, Text.VAlignBottom);
		timeText.setPadding(0, R.LEVELFRAMEOFFSETY + R.LEVELSIZE + R.BARWIDTH * 2 + R.UNIT * 7);
		
		last5Text = new Last5Text();
		last5Text.setText("1");
		last5Text.setFontScale(2);
		last5Text.getColor().set(R.Colors.BLOCKCOLOR);
		last5Text.getColor().a = 0.40f;
		last5Text.setAlignment(Text.HAlignCenter, Text.VAlignBottom);
		last5Text.setPadY(R.LEVELFRAMEOFFSETY + R.BARWIDTH + (R.LEVELSIZE - last5Text.getTextAreaHeight()) / 2);
		
		last5Flash = new FlashEffect(last5Text, R.Colors.LAST5SECCOLOR, 5);
		last5Flash.setDuration(10000);
		last5Flash.start();
		
		this.isStopped = true;
	}
	
	public void stop() {
		isStopped = true;
	}

	public void pause() {
		stop();
	}
	
	public void unpause() {
		isStopped = false;
	}
	
	public void start(int totalTime, int size) {
		if (size < 4)
			isFlashShown = false;
		else
			isFlashShown = true;
		this.totalTime = totalTime;
		this.currTime = totalTime;
		this.isStopped = false;
	}
	
	public void update(float elapsed) {
		if (!isStopped) {
			currTime -= elapsed;
			SCALE = currTime / totalTime;
			
			if (currTime < 0) {
				currTime = 0;
				parent.levelLost();
			}

			Integer time = (int) Math.ceil(currTime);
			timeText.setText(time.toString());
			last5Text.setText(time.toString());
		}
	}

	public void addTime(float addedTime) {
		totalTime += addedTime;
		currTime += addedTime;
	}
	
	@Override
	public void draw() {
		if (!isStopped && isFlashShown && currTime < 9)
			last5Text.draw();
		
		timeText.draw();
		
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
