package com.turpgames.rotategame.controller;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.component.Button;
import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.effects.flash.FlashEffect;
import com.turpgames.framework.v0.impl.InputListener;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.rotategame.components.BackButton;
import com.turpgames.rotategame.components.RestartButton;
import com.turpgames.rotategame.components.texts.LevelResultText;
import com.turpgames.rotategame.components.texts.XLargeText;
import com.turpgames.rotategame.objects.Block;
import com.turpgames.rotategame.objects.ControlListener;
import com.turpgames.rotategame.objects.DrawableContainer;
import com.turpgames.rotategame.objects.Level;
import com.turpgames.rotategame.objects.LevelTimer;
import com.turpgames.rotategame.objects.MapData;
import com.turpgames.rotategame.objects.PauseTimer;
import com.turpgames.rotategame.utils.R;

public class MasterPlayController extends InputListener implements IDrawable {
	private static int CURRENTMATRIXSIZE = 2;
	
	private ControlListener listener;
	
	private int[][] mapData;
	
	private Level level;

	private DrawableContainer generalDrawables;
	
	private int mapIndex;
	private LevelTimer levelTimer;
	private PauseTimer pauseTimer;
	
	private XLargeText levelTitle;
	
	private LevelResultText levelResultText;
	private FlashEffect gameWonFlash;
	private FlashEffect restartFlash;

	private BackButton btnBack;
	private RestartButton btnRestart;
	/***
	 * 	8		30	15	0.53
		9.3		60	20	0.46
		26		100	25	1.04
		33.2	150	30	1.10
		53.8	210	35	1.53
		78.2	280	40	1.95
		94.2	360	45	2.09
	 */
	public MasterPlayController() {
		generalDrawables = new DrawableContainer();
		
		listener = new ControlListener(this);
		generalDrawables.add(listener);
		
		btnBack = new BackButton();
		btnBack.setLocation(Button.AlignNW, R.BUTTONSPACING, R.BUTTONSPACING);
		btnBack.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
//				levelWon();
				ScreenManager.instance.switchTo(R.Screens.menu, false);
			}
		});
		generalDrawables.add(btnBack);
		generalDrawables.activate();
		
		float aboveFrame = R.LEVELFRAMEOFFSETY + R.LEVELSIZE + R.BARWIDTH * 2;
		
		levelTitle = new XLargeText();
		levelTitle.getColor().set(R.Colors.BLOCKCOLOR);
		levelTitle.setAlignment(Text.HAlignCenter, Text.VAlignBottom);
		levelTitle.setPadding(0, aboveFrame + R.UNIT * 14);
		generalDrawables.add(levelTitle);
		
		levelResultText = new LevelResultText();
		levelResultText.setAlignment(Text.HAlignCenter, Text.VAlignBottom);
		levelResultText.setPadY(aboveFrame + R.UNIT * 2);
		levelResultText.getColor().set(R.Colors.BLOCKCOLOR);
		generalDrawables.add(levelResultText);
		
		gameWonFlash = new FlashEffect(levelResultText, R.Colors.BUTTONCOLOR, 4);
		gameWonFlash.setDuration(100);

		btnRestart = new RestartButton();
		btnRestart.setLocation(Button.AlignNE, R.BUTTONSPACING, R.BUTTONSPACING);
		btnRestart.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				start();
			}
		});

		restartFlash = new FlashEffect(btnRestart, R.Colors.BLOCKCOLOR, 4);
		restartFlash.setDuration(100);
		
		generalDrawables.add(btnRestart);
		generalDrawables.add(btnBack);

		levelTimer = new LevelTimer(this);
		generalDrawables.add(levelTimer);
		
		pauseTimer = new PauseTimer(this);
		
		start();
	}
	
	protected void start() {
		mapIndex = 1;
		CURRENTMATRIXSIZE = 1;
		levelResultText.setText("");
		pauseTimer.stop();
		newLevel();
	}
	
	public void levelLost() {
		listener.stop();
		
		levelResultText.setText("Time up...");
		gameWonFlash.stop();
		restartFlash.start();
		
		level.levelIsFinished = true;
		levelTimer.stop();
	}
	
	public void levelWon() {
		listener.stop();
		
		int addedTime = getLevelTime();
		if (mapIndex < CURRENTMATRIXSIZE)
			levelResultText.setText("+" + addedTime + "!");
		else
			levelResultText.setText("SIZE UP!");
		gameWonFlash.start();
		levelTimer.addTime(addedTime);
		
		levelTimer.pause();
		pauseTimer.start();
	}

	public void pauseEnded() {
		newLevel();
		pauseTimer.stop();
	}
	
	private void newLevel() {
		mapIndex++;
		if (mapIndex > CURRENTMATRIXSIZE) {
			CURRENTMATRIXSIZE++;
			mapIndex = 1;
			levelTimer.start(getLevelTime(), CURRENTMATRIXSIZE);
		}
		
		mapData = MapData.randMap(CURRENTMATRIXSIZE);
		generalDrawables.remove(level);
		level = new Level(this, mapData, CURRENTMATRIXSIZE);
		generalDrawables.add(2, level);
		
		setLevelTitle();
		restartFlash.stop();
		listener.start(getBlockSize());
		levelTimer.unpause();
	}
	
	public void update() {
		levelTimer.update(Game.getDeltaTime());
		pauseTimer.update(Game.getDeltaTime());
		levelResultText.update(Game.getDeltaTime());
	}

	private void setLevelTitle() {
		levelTitle.setText("Lvl " + mapIndex + "/" + CURRENTMATRIXSIZE);
	}
	
	@Override
	public void draw() {
		generalDrawables.draw();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return listener.touchDown(x, y, pointer, button);
	}

	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		return listener.touchUp(x, y, pointer, button);
	}

	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		return listener.touchDragged(x, y, pointer);
	}

	public Block getTouchedBlock(float x, float y) {
		return level.getTouchedBlock(x, y);
	}

	public void activate() {
		btnRestart.activate();
		btnBack.activate();
	}

	public void deactivate() {
		btnRestart.deactivate();
		btnBack.deactivate();
	}
	
	public static float getBlockSize() {
		return R.LEVELSIZE / CURRENTMATRIXSIZE;
	}

	private int getLevelTime() {
		return R.LEVELTIMEDEC * CURRENTMATRIXSIZE / 2;
	}
}
