package com.turpgames.rotategame.controller;

import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.effects.flash.FlashEffect;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.rotategame.components.ArcadeButton;
import com.turpgames.rotategame.components.texts.NormalText;
import com.turpgames.rotategame.components.texts.XLargeText;
import com.turpgames.rotategame.objects.Block;
import com.turpgames.rotategame.objects.DrawableContainer;
import com.turpgames.rotategame.objects.Level;
import com.turpgames.rotategame.objects.LevelTimer;
import com.turpgames.rotategame.objects.MapData;
import com.turpgames.rotategame.utils.R;

public class MasterPlayController extends LevelController {

	protected int mapIndex;
	protected int mapTime;
	protected LevelTimer levelTimer;
	
	protected XLargeText levelTitle;
	private NormalText totalScoreText;
	protected int totalScore;
	private NormalText levelScoreText;
	private int levelScore;
	private float scoreTimer;
	
	protected DrawableContainer retryHud;
	protected DrawableContainer gameWonHud;
	private FlashEffect gameWonFlash;
	
	protected ArcadeButton btnNext;
	protected ArcadeButton btnRetry;
	
	public MasterPlayController() {
		super();
		retryHud = new DrawableContainer();
		gameWonHud = new DrawableContainer();
		
		this.mapData = MapData.randMap();
		level = new Level(this, mapData);
		generalDrawables.add(level);

		mapTime = R.MASTERLEVELTIME - mapIndex;
		levelScore = mapTime * 10;
		
		levelTimer = new LevelTimer(mapTime, this);
		levelTimer.start();
		generalDrawables.add(levelTimer);
		
		levelTitle = new XLargeText();
		levelTitle.getColor().set(R.Colors.BLOCKCOLOR);
		levelTitle.setAlignment(Text.HAlignLeft, Text.VAlignTop);
		levelTitle.setPadding(0, R.HUDPAD);
		generalDrawables.add(levelTitle);
		setLevelTitle();
		
		levelScoreText = new NormalText();
		levelScoreText.getColor().set(R.Colors.BLOCKCOLOR);
		levelScoreText.setAlignment(Text.HAlignLeft, Text.VAlignTop);
		levelScoreText.setPadding(0, R.HUDPAD * 4 + 10);
		generalDrawables.add(levelScoreText);
		setLevelScoreText();
		
		NormalText gameWonText = new NormalText();
		gameWonText.setText("Level finished!!");
		gameWonText.getColor().set(R.Colors.BLOCKCOLOR);
		gameWonHud.add(gameWonText);

		btnNext = new ArcadeButton("Next");
		btnNext.setLocation(R.BUTTONOFFSETX, 393);
		btnNext.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				onNext();
			}
		});
		gameWonHud.add(btnNext);		
		
		totalScoreText = new NormalText();
		totalScoreText.getColor().set(R.Colors.BLOCKCOLOR);
		totalScoreText.setAlignment(Text.HAlignLeft, Text.VAlignTop);
		totalScoreText.setPadding(0, R.HUDPAD * 3);
		generalDrawables.add(totalScoreText);
		setTotalScoreText();
		
		btnRetry = new ArcadeButton("Retry");
		btnRetry.setLocation((Game.getVirtualWidth() - btnRetry.getWidth()) / 2, Game.getVirtualHeight() / 4);
		btnRetry.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				onRetry();
			}
		});
		retryHud.add(btnRetry);
		
		btnNext.deactivate();
		btnRetry.deactivate();
		
		generalDrawables.add(retryHud);
		generalDrawables.add(gameWonHud);
		
//		gameWonFlash = new FlashEffect(totalScoreText, Color.white(), 4);
//		gameWonFlash.setDuration(100);
//		gameWonFlash.start();
	}
	
	private void onRetry() {
		resetState();
		level.randomizeBlocks();
		level.levelIsFinished = false;
		totalScore -= 100;
		setTotalScoreText();
	}
	
	protected void onNext() {
		mapIndex++;
		mapTime = R.MASTERLEVELTIME - mapIndex;
		if (mapIndex == R.MASTERLEVELCOUNT) {
			// save score to progress
			ScreenManager.instance.switchTo(R.Screens.finishedmaster, false);
		}
		else
		{
			resetState();
			mapData = MapData.randMap();
			generalDrawables.remove(level);
			level = new Level(this, mapData);
			generalDrawables.add(level);
			setLevelTitle();
			mapTime = R.MASTERLEVELTIME - mapIndex;
		}
	}

	protected void resetState() {
		retryHud.deactivate();
		btnRetry.deactivate();
		gameWonHud.deactivate();
		btnNext.deactivate();
		
		scoreTimer = 0;
		levelScore = mapTime * 10;
		setLevelScoreText();
		
		levelTimer.reset();
		levelTimer.start();
	}
	
	public void timeUp() {
		retryHud.activate();
		btnRetry.activate();
		
		level.levelIsFinished = true;
		levelTimer.stop();
	}
	
	@Override
	public void levelWon() {
		super.levelWon();
		gameWonHud.activate();
		btnNext.activate();
		
		totalScore += levelScore;
		setTotalScoreText();
		scoreTimer = 0;
		
		levelTimer.stop();
		super.levelWon();
	}
	
	@Override
	public void blockClicked(Block block) {
		if (block.type == Block.CONN0 || block.type == Block.CONN4)
			return;
		levelScore--;
		if (levelScore < 0)
			levelScore = 0;
		setLevelScoreText();
		super.blockClicked(block);
	}
	
	@Override
	public void update() {
		super.update();
		levelTimer.update(Game.getDeltaTime());
		scoreTimer += Game.getDeltaTime();
		if (scoreTimer >= 1) 
		{
			levelScore -= 10;							
			if (levelScore < 0)
				levelScore = 0;
			setLevelScoreText();
			scoreTimer = 0;
		}
	}

	private void setTotalScoreText() {
		totalScoreText.setText("Total Score: " + totalScore);
	}
	
	private void setLevelScoreText() {
		levelScoreText.setText("Level Score: " + levelScore);
	}
	
	private void setLevelTitle() {
		levelTitle.setText("LEVEL " + (mapIndex + 1) + "/" + R.MASTERLEVELCOUNT);
	}
}
