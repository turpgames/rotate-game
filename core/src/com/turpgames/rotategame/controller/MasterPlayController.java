package com.turpgames.rotategame.controller;

import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.effects.flash.FlashEffect;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Color;
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
		
		levelTitle = new XLargeText();
		levelTitle.getColor().set(R.Colors.BLOCKCOLOR);
		levelTitle.setAlignment(Text.HAlignCenter, Text.VAlignTop);
		levelTitle.setPadding(0, R.HUDPAD);
		generalDrawables.add(levelTitle);
		
		scoreText = new NormalText();
		scoreText.getColor().set(R.Colors.BLOCKCOLOR);
		scoreText.setAlignment(Text.HAlignCenter, Text.VAlignTop);
		scoreText.setPadding(0, R.HUDPAD * 6);
		generalDrawables.add(scoreText);
		
		levelResultText = new LevelResultText();
		levelResultText.setAlignment(Text.HAlignCenter, Text.VAlignBottom);
		levelResultText.setPadY(2 * R.HUDPAD / 3);
		levelResultText.getColor().set(R.Colors.BLOCKCOLOR);
		generalDrawables.add(levelResultText);
		
		gameWonFlash = new FlashEffect(levelResultText, R.Colors.BUTTONCOLOR, 4);
		gameWonFlash.setDuration(100);

		btnRestart = new ArcadeButton("Restart");
		btnRestart.setLocation((Game.getVirtualWidth() - btnRestart.getWidth()) / 2, R.HUDPAD * 5);
		btnRestart.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				start();
			}
		});
		btnMenu.setLocation((Game.getVirtualWidth() - btnRestart.getWidth()) / 2, R.HUDPAD * 9);
		
		start();
	}
	
	protected void start() {
		score = R.STARTSCORE;
		mapIndex = 0;
		generalDrawables.remove(btnRestart);
		generalDrawables.remove(btnMenu);
		btnRestart.deactivate();
		btnMenu.deactivate();
		
		newLevel();

		listener.start();
	}
	
	@Override
	public void levelLost() {
		super.levelLost();
		levelResultText.setText("Time up...");
		gameWonFlash.stop();
		btnRestart.activate();
		btnMenu.activate();
		generalDrawables.add(btnRestart);
		generalDrawables.add(btnMenu);
		
		level.levelIsFinished = true;
		levelTimer.stop();

		score = 0;
		setScoreText();
	}
	
	@Override
	public void levelWon() {
		super.levelWon();
		
		newLevel();
		
		int netScore = addedScore - lostScore;
		levelResultText.setText("+ " + addedScore + " - " + lostScore + " = " + 
				(netScore < 0 ? " - " : " + ") + netScore + " points.");
		gameWonFlash.start();
		
		listener.start();
	}

	private void newLevel() {
		mapData = MapData.randMap();
		generalDrawables.remove(level);
		level = new Level(this, mapData);
		generalDrawables.add(2, level);
		addedScore = R.LEVELSCOREMULT * mapIndex;
		lostScore = 0;
		score += addedScore;
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
		levelTitle.setText("LEVEL\n" + mapIndex);
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
