package com.turpgames.rotategame.controller;

import java.util.ArrayList;
import java.util.List;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.ISound;
import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.effects.flash.FlashEffect;
import com.turpgames.framework.v0.impl.InputListener;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.rotategame.components.PauseButton;
import com.turpgames.rotategame.components.RestartButton;
import com.turpgames.rotategame.components.texts.LargeText;
import com.turpgames.rotategame.components.texts.LevelResultText;
import com.turpgames.rotategame.objects.Block;
import com.turpgames.rotategame.objects.BlockDrawer;
import com.turpgames.rotategame.objects.ControlListener;
import com.turpgames.rotategame.objects.Level;
import com.turpgames.rotategame.objects.LevelTimer;
import com.turpgames.rotategame.objects.MapData;
import com.turpgames.rotategame.objects.PauseTimer;
import com.turpgames.rotategame.utils.R;

public class MasterPlayController extends InputListener implements IDrawable {
	private static int CURRENTMATRIXSIZE = 2;
	
	private ControlListener listener;
		
	private Level level;

	private List<IDrawable> drawables;
	
	private int mapIndex;
	private LevelTimer levelTimer;
	private PauseTimer pauseTimer;
	
	private LargeText levelTitle;
	private LevelResultText levelResultText;
	private FlashEffect gameWonFlash;

	private PauseButton btnPause;
	private RestartButton btnRestart;
	
	private ISound newSound;
	private ISound sizeSound;
	private ISound turnSound;
	private ISound winSound;
	private ISound lostSound;

	public MasterPlayController() {
		newSound = Game.getResourceManager().getSound("new");
		sizeSound = Game.getResourceManager().getSound("size");
		turnSound = Game.getResourceManager().getSound("turn");
		winSound = Game.getResourceManager().getSound("win");
		lostSound = Game.getResourceManager().getSound("lost");
		
		drawables = new ArrayList<IDrawable>();
		
		listener = new ControlListener(this);
		drawables.add(listener);
		
		btnPause = new PauseButton();
		drawables.add(btnPause);
		
		float aboveFrame = R.LEVELFRAMEOFFSETY + R.LEVELSIZE + R.BARWIDTH * 2;
		
		levelTitle = new LargeText();
		levelTitle.getColor().set(R.Colors.COLOR1);
		levelTitle.setAlignment(Text.HAlignCenter, Text.VAlignBottom);
		levelTitle.setPadding(0, aboveFrame + R.UNIT * 14);
		drawables.add(levelTitle);
		
		levelResultText = new LevelResultText();
		levelResultText.setAlignment(Text.HAlignCenter, Text.VAlignBottom);
		levelResultText.setPadY(aboveFrame + R.UNIT * 2);
		levelResultText.getColor().set(R.Colors.COLOR1);
		drawables.add(levelResultText);
		
		gameWonFlash = new FlashEffect(levelResultText, R.Colors.COLOR2, 4);
		gameWonFlash.setDuration(100);

		btnRestart = new RestartButton();
		btnRestart.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				start();
//				levelWon();
			}
		});
		
		drawables.add(btnRestart);
		drawables.add(btnPause);

		levelTimer = new LevelTimer(this);
		drawables.add(levelTimer);
		
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
		btnRestart.startFlash();
		
		level.levelIsFinished = true;
		levelTimer.stop();
		
		lostSound.play();
	}
	
	public void levelWon() {
		winSound.play();
		listener.stop();
		
		int addedTime = getLevelTime();
		if (mapIndex < CURRENTMATRIXSIZE) {
			levelResultText.setText("+" + addedTime + "!");
		}
		else if (CURRENTMATRIXSIZE < 8 ) {
			levelResultText.setText("SIZE UP!");
		}
		else {
			start();
			ScreenManager.instance.switchTo(R.Screens.finished, false);
		}
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
			if (CURRENTMATRIXSIZE != 2)
				sizeSound.play();
		}
		else {
			newSound.play();
		}
		
		drawables.remove(level);
		level = new Level(this, MapData.randMap(CURRENTMATRIXSIZE), CURRENTMATRIXSIZE);
		drawables.add(2, level);
		
		setLevelTitle();
		btnRestart.stopFlash();
		listener.start(getBlockSize());
		levelTimer.unpause();
		
	}
	
	public void update() {
		levelTimer.update(Game.getDeltaTime());
		pauseTimer.update(Game.getDeltaTime());
		levelResultText.update(Game.getDeltaTime());
	}

	private void setLevelTitle() {
		levelTitle.setText(mapIndex + "/" + CURRENTMATRIXSIZE + "x" + CURRENTMATRIXSIZE);
	}
	
	@Override
	public void draw() {
		for (IDrawable obj : drawables) {
			obj.draw();
		}
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
		btnPause.activate();
		
		btnRestart.updateColor();
		btnPause.updateColor();
		levelTimer.updateColor();
		BlockDrawer.updateColor();
		levelTitle.getColor().set(R.Colors.COLOR1);
		levelResultText.getColor().set(R.Colors.COLOR1);
		gameWonFlash.setFlashColor(R.Colors.COLOR2);
	}

	public void deactivate() {
		btnRestart.deactivate();
		btnPause.deactivate();
	}
	
	public static float getBlockSize() {
		return R.LEVELSIZE / CURRENTMATRIXSIZE;
	}

	private int getLevelTime() {
		return R.LEVELTIMEDEC * CURRENTMATRIXSIZE / 2;
	}

	public void blockIsClicked(Block b) {
		turnSound.play();
	}
}
