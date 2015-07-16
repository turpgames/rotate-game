package com.turpgames.rotategame.controller;

import com.turpgames.framework.v0.component.Button;
import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.effects.flash.FlashEffect;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.rotategame.components.ControlButton;
import com.turpgames.rotategame.components.texts.LevelResultText;
import com.turpgames.rotategame.components.texts.NormalText;
import com.turpgames.rotategame.components.texts.XLargeText;
import com.turpgames.rotategame.objects.Block;
import com.turpgames.rotategame.objects.Level;
import com.turpgames.rotategame.objects.LevelTimer;
import com.turpgames.rotategame.objects.MapData;
import com.turpgames.rotategame.utils.R;

public class MasterPlayController extends LevelController {

	protected int mapIndex;
	protected LevelTimer levelTimer;
	
	protected XLargeText levelTitle;
	private NormalText timeText;
	protected int time;
	protected int lostTime;
	protected int addedTime;
	
	protected LevelResultText levelResultText;
	protected FlashEffect gameWonFlash;
	protected FlashEffect restartFlash;
	
	protected ControlButton btnRestart;
	
	public MasterPlayController() {
		super();
		
		levelTimer = new LevelTimer(this);
		generalDrawables.add(0, levelTimer);
		
		float timeTextYPad = R.LEVELFRAMEOFFSETY + R.BLOCKSIZE * R.COLNUMBER + R.BARWIDTH * 2 + R.UNIT * 7;
		timeText = new NormalText();
		timeText.getColor().set(R.Colors.BLOCKCOLOR);
		timeText.setAlignment(Text.HAlignCenter, Text.VAlignBottom);
		timeText.setPadding(0, timeTextYPad);
		generalDrawables.add(timeText);
		
		levelTitle = new XLargeText();
		levelTitle.getColor().set(R.Colors.BLOCKCOLOR);
		levelTitle.setAlignment(Text.HAlignCenter, Text.VAlignBottom);
		levelTitle.setPadding(0, timeTextYPad + R.UNIT * 5);
		generalDrawables.add(levelTitle);
		
		levelResultText = new LevelResultText();
		levelResultText.setAlignment(Text.HAlignCenter, Text.VAlignBottom);
		levelResultText.setPadY(timeTextYPad - R.UNIT * 4);
		levelResultText.getColor().set(R.Colors.BLOCKCOLOR);
		generalDrawables.add(levelResultText);
		
		gameWonFlash = new FlashEffect(levelResultText, R.Colors.BUTTONCOLOR, 4);
		gameWonFlash.setDuration(100);

		btnRestart = new ControlButton(R.Textures.btnRestart);
//		btnRestart.setLocation((Game.getVirtualWidth() - btnRestart.getWidth()) / 2, R.UNIT * 55);
		btnRestart.setLocation(Button.AlignNE, R.BUTTONSPACING, R.BUTTONSPACING);
		btnRestart.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				start();
			}
		});

		restartFlash = new FlashEffect(btnRestart, R.Colors.BLOCKCOLOR, 4);
		restartFlash.setDuration(100);
		
//		btnMenu.setLocation((Game.getVirtualWidth() - btnMenu.getWidth()) / 2,  R.UNIT * 65);
		btnMenu.setLocation(Button.AlignNW, R.BUTTONSPACING, R.BUTTONSPACING);
		btnRestart.activate();
		btnMenu.activate();
		generalDrawables.add(btnRestart);
		generalDrawables.add(btnMenu);
		
		start();
	}
	
	protected void start() {
		time = R.STARTTIME;
		mapIndex = 0;
		
		newLevel();

		listener.start();
	}
	
	@Override
	public void levelLost() {
		super.levelLost();
		levelResultText.setText("Time up...");
		gameWonFlash.stop();
		restartFlash.start();
		
		level.levelIsFinished = true;
		levelTimer.stop();

		time = 0;
		setTimeText();
	}
	
	@Override
	public void levelWon() {
		super.levelWon();
		
		addedTime = R.STARTTIME - R.LEVELTIMEDEC * mapIndex;
//		int netTime = addedTime - lostTime;
//		levelResultText.setText("+" + addedTime + "-" + lostTime + "= net " + 
//				(netTime < 0 ? "" : "+") + netTime);
		levelResultText.setText("+" + addedTime + "!");
		gameWonFlash.start();
		lostTime = 0;
		time += addedTime;
		listener.start();

		newLevel();
	}

	private void newLevel() {
		mapData = MapData.randMap();
		generalDrawables.remove(level);
		level = new Level(this, mapData);
		generalDrawables.add(2, level);
		mapIndex++;
		levelTimer.start(time);
		setTimeText();
		setLevelTitle();
		restartFlash.stop();
		levelResultText.setText("+" + addedTime + "!");
	}
	
	@Override
	public void blockClicked(Block block) {
//		if (block.type == Block.CONN0 || block.type == Block.CONN4)
//			return;
//		time--;
//		if (time <= 0) {
//			time = 0;
//			// game lost
//		}
//		setTimeText();
	}
	
	@Override
	public void update() {
		super.update();
		levelTimer.update(Game.getDeltaTime());
		levelResultText.update(Game.getDeltaTime());
	}

	private void setTimeText() {
		timeText.setText("Time: " + time);
	}
	
	private void setLevelTitle() {
		levelTitle.setText("LEVEL " + mapIndex);
	}

	public void secondPassed() {
		lostTime += 1;
		time -= 1;
		if (time <= 0) {
			time = 0;
		}
		setTimeText();
	}
}
