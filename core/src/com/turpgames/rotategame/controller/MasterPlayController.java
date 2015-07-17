package com.turpgames.rotategame.controller;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.component.Button;
import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.effects.flash.FlashEffect;
import com.turpgames.framework.v0.impl.InputListener;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.rotategame.components.ControlButton;
import com.turpgames.rotategame.components.texts.LevelResultText;
import com.turpgames.rotategame.components.texts.NormalText;
import com.turpgames.rotategame.components.texts.XLargeText;
import com.turpgames.rotategame.objects.Block;
import com.turpgames.rotategame.objects.ControlListener;
import com.turpgames.rotategame.objects.DrawableContainer;
import com.turpgames.rotategame.objects.Level;
import com.turpgames.rotategame.objects.LevelTimer;
import com.turpgames.rotategame.objects.MapData;
import com.turpgames.rotategame.utils.R;

public class MasterPlayController extends InputListener implements IDrawable {
	private static int CURRENTMATRIXSIZE = 2;
	
	private ControlListener listener;
	
	private int[][] mapData;
	
	private Level level;
	private ControlButton btnMenu;

	private DrawableContainer generalDrawables;
	
	private int mapIndex;
	private LevelTimer levelTimer;
	
	private XLargeText levelTitle;
	private NormalText timeText;
	private int time;
//	private int lostTime;
	private int addedTime;
	
	private LevelResultText levelResultText;
	private FlashEffect gameWonFlash;
	private FlashEffect restartFlash;
	
	private ControlButton btnRestart;
	/***
	 * 	8	30	15	0.53
		9.3	60	20	0.46
		26	100	25	1.04
		33.2	150	30	1.10
		53.8	210	35	1.53
		78.2	280	40	1.95
		94.2	360	45	2.09
	 */
	public MasterPlayController() {
		generalDrawables = new DrawableContainer();
		
		listener = new ControlListener(this);
		generalDrawables.add(listener);
		
		btnMenu = new ControlButton(R.Textures.btnMenu);
		btnMenu.setLocation(Button.AlignNW, R.BUTTONSPACING, R.BUTTONSPACING);
		btnMenu.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
//				levelWon();
				ScreenManager.instance.switchTo(R.Screens.menu, false);
			}
		});
		generalDrawables.add(btnMenu);
		generalDrawables.activate();
		
		
		levelTimer = new LevelTimer(this);
		generalDrawables.add(0, levelTimer);
		
		float timeTextYPad = R.LEVELFRAMEOFFSETY + R.LEVELSIZE + R.BARWIDTH * 2 + R.UNIT * 7;
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
		
		btnRestart.activate();
		btnMenu.activate();
		generalDrawables.add(btnRestart);
		generalDrawables.add(btnMenu);
		
		start();
	}
	
	protected void start() {
		mapIndex = 0;
		CURRENTMATRIXSIZE = 2;
		time = CURRENTMATRIXSIZE * R.LEVELTIMEDEC;
		newLevel();
	}
	
	public void levelLost() {
		listener.stop();
		
		levelResultText.setText("Time up...");
		gameWonFlash.stop();
		restartFlash.start();
		
		level.levelIsFinished = true;
		levelTimer.stop();

		time = 0;
		setTimeText();
	}
	
	public void levelWon() {
		listener.stop();
		
		addedTime = R.LEVELTIMEDEC * (CURRENTMATRIXSIZE - mapIndex);
//		int netTime = addedTime - lostTime;
//		levelResultText.setText("+" + addedTime + "-" + lostTime + "= net " + 
//				(netTime < 0 ? "" : "+") + netTime);
		if (addedTime != 0)
			levelResultText.setText("+" + addedTime + "!");
		else
			levelResultText.setText("SIZE UP!");
		gameWonFlash.start();
//		lostTime = 0;
		time += addedTime;

		newLevel();
	}

	private void newLevel() {
		mapIndex++;
		if (mapIndex > CURRENTMATRIXSIZE) {
			CURRENTMATRIXSIZE++;
			mapIndex = 1;
			time = CURRENTMATRIXSIZE * R.LEVELTIMEDEC;
		}
		
		mapData = MapData.randMap(CURRENTMATRIXSIZE);
		generalDrawables.remove(level);
		level = new Level(this, mapData, CURRENTMATRIXSIZE);
		generalDrawables.add(2, level);
		levelTimer.start(time);
		setTimeText();
		setLevelTitle();
		restartFlash.stop();
		listener.start(getBlockSize());
	}
	
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
	
	public void update() {
		levelTimer.update(Game.getDeltaTime());
		levelResultText.update(Game.getDeltaTime());
	}

	private void setTimeText() {
		timeText.setText("Time: " + time);
	}
	
	private void setLevelTitle() {
		levelTitle.setText("Lvl " + mapIndex + "/" + CURRENTMATRIXSIZE);
	}

	public void secondPassed() {
//		lostTime += 1;
		time -= 1;
		if (time <= 0) {
			time = 0;
		}
		setTimeText();
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
		
	}

	public void deactivate() {
		
	}
	
	public float getBlockSize() {
		return R.LEVELSIZE / CURRENTMATRIXSIZE;
	}
}
