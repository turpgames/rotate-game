package com.turpgames.rotategame.objects;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.rotategame.controller.MasterPlayController;
import com.turpgames.rotategame.utils.R;

public class LevelTimer implements IDrawable {
	private static int FULLSCALE = 18 + 12 + 12 + 5 * R.BLOCKSIZE;
	
	private MasterPlayController parent;
	
	private float currTime;
	private int mapTime;
	
	private Text timeText;
	private Text last5Text;
	
	private boolean timerIsStopped;
	
	public LevelTimer(int mapTime, MasterPlayController parent) {
		this.parent = parent;
		this.mapTime = mapTime;
		
		timeText = new Text();
		timeText.setText("");
		timeText.setLocation(R.MAPOFFSETX - 12, 4);
		timeText.getColor().set(R.Colors.BLOCKCOLOR);
		//timeText.setAlignment(Text.HAlignCenter, Text.VAlignTop);
		
		last5Text = new Text();
		last5Text.setText("");
		last5Text.setFontScale(5);
		last5Text.getColor().set(R.Colors.BLOCKCOLOR);
		last5Text.getColor().a = 0.65f;
		last5Text.setAlignment(Text.HAlignCenter, Text.VAlignTop);
		
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
			
			Integer time = mapTime - (int)(mapTime * ((mapTime - currTime) / mapTime));
			timeText.setText(time.toString());
			last5Text.setText(time.toString());
			
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
	}
}
