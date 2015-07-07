package com.turpgames.rotategame.objects;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.effects.flash.FlashEffect;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.ShapeDrawer;
import com.turpgames.rotategame.components.texts.NormalText;
import com.turpgames.rotategame.components.texts.HugeText;
import com.turpgames.rotategame.controller.MasterPlayController;
import com.turpgames.rotategame.utils.R;

public class LevelTimer implements IDrawable {
	private static float FULLSCALE = R.ROWNUMBER * R.BLOCKSIZE + R.LEVELFRAMEOFFSETX * 2;
	private static float SCALEDLENGTH = 0; 
	private static float BARWIDTH = 12;
	private static Color BARCOLOR = R.Colors.BUTTONCOLOR;
	
	private MasterPlayController parent;
	
	private float currTime;
	private int mapTime;
	
	private NormalText timeText;
	private HugeText last5Text;
//	private FlashEffect last5Flash;
	
	private boolean timerIsStopped;
	
	public LevelTimer(int mapTime, MasterPlayController parent) {
		this.parent = parent;
		this.mapTime = mapTime;
		
		timeText = new NormalText();
		timeText.setText("");
		timeText.setAlignment(Text.HAlignCenter, Text.VAlignBottom);
		timeText.setPadY(R.MAPOFFSETY + R.BLOCKSIZE * R.ROWNUMBER + 20);
		timeText.getColor().set(R.Colors.BLOCKCOLOR);
		//timeText.setAlignment(Text.HAlignCenter, Text.VAlignTop);
		
		last5Text = new HugeText();
		last5Text.setText("");
		last5Text.setFontScale(5);
		last5Text.getColor().set(R.Colors.BLOCKCOLOR);
		last5Text.getColor().a = 0.65f;
		last5Text.setAlignment(Text.HAlignCenter, Text.VAlignBottom);
		last5Text.setPadY(R.MAPOFFSETY + R.BLOCKSIZE);
		
//		last5Flash = new FlashEffect(last5Text, R.Colors.BUTTONCOLOR, 7);
//		last5Flash.setDuration(10000);
		
		this.timerIsStopped = true;
	}

	public void reset() {
		currTime = mapTime;
	}
	
	public void stop() {
		timerIsStopped = true;
	}
	
	public void start() {
		start(mapTime);
	}
	
	public void start(int mapTime) {
		this.mapTime = mapTime;
		this.currTime = mapTime;
		timerIsStopped = false;
	}
	
	public void update(float elapsed) {
		if (!timerIsStopped) {
			currTime -= elapsed;
			SCALEDLENGTH = FULLSCALE * currTime / mapTime;
			
			Integer time = (int) currTime;
			timeText.setText(time.toString());
			last5Text.setText(time.toString());
			
			if (currTime <= 5) {
//				last5Flash.start();
			}
			
			if (currTime < 0) {
				currTime = 0;
				parent.timeUp();
			}
		}
	}
	
	@Override
	public void draw() {
		if (currTime < 5) {
			last5Text.draw();
		}
		timeText.draw();
		
		ShapeDrawer.drawRect(R.LEVELFRAMEOFFSETX + (FULLSCALE - SCALEDLENGTH) / 2, R.LEVELFRAMEOFFSETY, SCALEDLENGTH, BARWIDTH, BARCOLOR, true, false);
		ShapeDrawer.drawRect(R.LEVELFRAMEOFFSETX + (FULLSCALE - SCALEDLENGTH) / 2, R.LEVELFRAMEOFFSETY + R.BLOCKSIZE * R.ROWNUMBER + BARWIDTH, SCALEDLENGTH, BARWIDTH, BARCOLOR, true, false);

		ShapeDrawer.drawRect(R.LEVELFRAMEOFFSETX, R.LEVELFRAMEOFFSETY + (FULLSCALE - SCALEDLENGTH) / 2, BARWIDTH, SCALEDLENGTH, BARCOLOR, true, false);
		ShapeDrawer.drawRect(R.LEVELFRAMEOFFSETX + R.BLOCKSIZE * R.ROWNUMBER + BARWIDTH, R.LEVELFRAMEOFFSETY + (FULLSCALE - SCALEDLENGTH) / 2, BARWIDTH, SCALEDLENGTH, BARCOLOR, true, false);
	}
}
