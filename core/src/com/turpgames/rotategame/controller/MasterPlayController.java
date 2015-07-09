package com.turpgames.rotategame.controller;

import com.turpgames.framework.v0.component.Button2;
import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.effects.flash.FlashEffect;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.rotategame.components.ArcadeButton;
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
	private NormalText scoreText;
	protected int score;
	protected int lostScore;
	protected int addedScore;
	
	protected LevelResultText levelResultText;
	protected FlashEffect gameWonFlash;
	
	protected ArcadeButton btnRestart;
	
	public MasterPlayController() {
		super();
		
		levelTimer = new LevelTimer(this);
		generalDrawables.add(0, levelTimer);
		
		float scoreTextYPad = R.LEVELFRAMEOFFSETY + R.BLOCKSIZE * R.COLNUMBER + R.BARWIDTH * 2 + R.UNIT * 4;
		scoreText = new NormalText();
		scoreText.getColor().set(R.Colors.BLOCKCOLOR);
		scoreText.setAlignment(Text.HAlignCenter, Text.VAlignBottom);
		scoreText.setPadding(0, scoreTextYPad);
		generalDrawables.add(scoreText);
		
		levelTitle = new XLargeText();
		levelTitle.getColor().set(R.Colors.BLOCKCOLOR);
		levelTitle.setAlignment(Text.HAlignCenter, Text.VAlignBottom);
		levelTitle.setPadding(0, scoreTextYPad + R.UNIT * 5);
		generalDrawables.add(levelTitle);
		
		levelResultText = new LevelResultText();
		levelResultText.setAlignment(Text.HAlignCenter, Text.VAlignBottom);
		levelResultText.setPadY(scoreTextYPad - R.UNIT * 2.5f);
		levelResultText.getColor().set(R.Colors.BLOCKCOLOR);
		generalDrawables.add(levelResultText);
		
		gameWonFlash = new FlashEffect(levelResultText, R.Colors.BUTTONCOLOR, 4);
		gameWonFlash.setDuration(100);

		btnRestart = new ArcadeButton("Restart");
//		btnRestart.setLocation((Game.getVirtualWidth() - btnRestart.getWidth()) / 2, R.UNIT * 55);
		btnRestart.setLocation(Button2.ne, R.LEVELFRAMEOFFSETY - R.UNIT * 2, R.LEVELFRAMEOFFSETY - R.UNIT * 2);
		btnRestart.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				start();
			}
		});
//		btnMenu.setLocation((Game.getVirtualWidth() - btnMenu.getWidth()) / 2,  R.UNIT * 65);
		btnMenu.setLocation(Button2.nw, R.LEVELFRAMEOFFSETY - R.UNIT * 2, R.LEVELFRAMEOFFSETY - R.UNIT * 2);
		btnRestart.activate();
		btnMenu.activate();
		generalDrawables.add(btnRestart);
		generalDrawables.add(btnMenu);
		
		start();
	}
	
	protected void start() {
		score = R.STARTSCORE;
		mapIndex = 0;
		
		newLevel();

		listener.start();
	}
	
	@Override
	public void levelLost() {
		super.levelLost();
		levelResultText.setText("Time up...");
		gameWonFlash.stop();
		
		level.levelIsFinished = true;
		levelTimer.stop();

		score = 0;
		setScoreText();
	}
	
	@Override
	public void levelWon() {
		super.levelWon();
		
		addedScore = R.LEVELSCOREMULT * mapIndex;
		int netScore = addedScore - lostScore;
		levelResultText.setText("+" + addedScore + "-" + lostScore + "= net " + 
				(netScore < 0 ? "" : "+") + netScore);
		gameWonFlash.start();
		lostScore = 0;
		score += addedScore;
		listener.start();

		newLevel();
	}

	private void newLevel() {
		mapData = MapData.randMap();
		generalDrawables.remove(level);
		level = new Level(this, mapData);
		generalDrawables.add(2, level);
		mapIndex++;
		levelTimer.start(score / mapIndex);
		setScoreText();
		setLevelTitle();
	}
	
	@Override
	public void blockClicked(Block block) {
//		if (block.type == Block.CONN0 || block.type == Block.CONN4)
//			return;
//		score--;
//		if (score <= 0) {
//			score = 0;
//			// game lost
//		}
//		setScoreText();
	}
	
	@Override
	public void update() {
		super.update();
		levelTimer.update(Game.getDeltaTime());
		levelResultText.update(Game.getDeltaTime());
	}

	private void setScoreText() {
		scoreText.setText("Score: " + score);
	}
	
	private void setLevelTitle() {
		levelTitle.setText("LEVEL " + mapIndex);
	}

	public void secondPassed() {
		lostScore += mapIndex;
		score -= mapIndex;
		if (score <= 0) {
			score = 0;
		}
		setScoreText();
	}
}
