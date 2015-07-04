package com.turpgames.rotategame.controller;

import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.rotategame.components.ArcadeButton;
import com.turpgames.rotategame.components.NormalText;
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
	
	protected NormalText levelTitle;
	private NormalText totalScoreText;
	protected int totalScore;
	private Text levelScoreText;
	private int levelScore;
	private float scoreTimer;
	
	protected DrawableContainer retryHud;
	protected DrawableContainer gameWonHud;
	
	protected ArcadeButton btnNext;
	protected ArcadeButton btnRetry;
	
	public MasterPlayController() {
		super();
		retryHud = new DrawableContainer();
		gameWonHud = new DrawableContainer();
		
		this.mapData = MapData.randMap();
		level = new Level(this, mapData);
		generalDrawables.add(level);
		
		levelTimer = new LevelTimer(mapTime, this);
		levelTimer.start();
		generalDrawables.add(levelTimer);
		
		levelTitle = new NormalText();
		levelTitle.setText("LEVEL\n" + (mapIndex + 1));
		generalDrawables.add(levelTitle);
		
		levelScoreText = new Text();
		levelScoreText.setText("" + levelScore);
		generalDrawables.add(levelScoreText);
		
		Text gameWonText = new Text();
		gameWonText.setText("Level finished!!");
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
		gameWonHud.add(totalScoreText);
		
		btnRetry = new ArcadeButton("Retry");
		btnRetry.setLocation(R.BUTTONOFFSETX, 393);
		btnRetry.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				onRetry();
			}
		});
		retryHud.add(btnRetry);
		
		btnNext.deactivate();
		btnRetry.deactivate();
	}
	
	private void onNext() {
		mapIndex++;
		mapTime = R.MASTERLEVELTIME - mapIndex;
		if (mapIndex == R.MASTERLEVELCOUNT) {
			// finish master state. go to master result screen
			ScreenManager.instance.switchTo(R.Screens.menu, false);
		}
		else
		{
			resetState();
			mapData = MapData.randMap();
			level = new Level(this, mapData);
			levelTitle.setText("LEVEL" + (mapIndex + 1) + "/" + R.MASTERLEVELCOUNT);
		}
	}
	
	private void onRetry() {
		resetState();
		level.randomizeBlocks();
		level.levelIsFinished = false;
		totalScore -= 100;
		totalScoreText.setText("Score:\n" + totalScore);
	}
	
	protected void resetState() {
		scoreTimer = 0;
		levelScore = mapTime * 10;
		levelScoreText.setText("" + levelScore);
		
		levelTimer.reset();
		levelTimer.start();
	}
	
	public void timeUp() {
		level.levelIsFinished = true;
		levelTimer.stop();
	}
	
	@Override
	public void levelWon() {
		btnNext.activate();
		
		totalScore += levelScore;
		totalScoreText.setText("Score:\n " + totalScore);
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
		levelScoreText.setText("" + levelScore);
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
			levelScoreText.setText("" + levelScore);
			scoreTimer = 0;
		}
	}
}
