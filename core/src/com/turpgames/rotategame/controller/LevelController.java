package com.turpgames.rotategame.controller;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.component.Button2;
import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.impl.InputListener;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.rotategame.components.ArcadeButton;
import com.turpgames.rotategame.objects.Block;
import com.turpgames.rotategame.objects.ControlListener;
import com.turpgames.rotategame.objects.DrawableContainer;
import com.turpgames.rotategame.objects.Level;
import com.turpgames.rotategame.utils.R;

abstract public class LevelController extends InputListener implements IDrawable {
	protected ControlListener listener;
	
	protected int[][] mapData;
	
	protected Level level;
	protected ArcadeButton btnMenu;

	protected DrawableContainer generalDrawables;
	public LevelController() {
		this(null);
	}
	
	public LevelController(int[][] mapData) {
		generalDrawables = new DrawableContainer();
		
		listener = new ControlListener(this);
		generalDrawables.add(listener);
		
		this.mapData = mapData;
		if (mapData != null) {
			level = new Level(this, mapData);
			generalDrawables.add(level);
		}
		
		btnMenu = new ArcadeButton("Menu");
		btnMenu.setLocation(Button2.nw, R.HUDPAD, R.HUDPAD);
		btnMenu.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
//				levelWon();
				ScreenManager.instance.switchTo(R.Screens.menu, false);
			}
		});
		generalDrawables.add(btnMenu);
		generalDrawables.activate();
	}

	public void activate() {
		
	}

	public void deactivate() {
		
	}

	public void update() {

	}

	public void levelLost() {
		listener.stop();
	}
	
	public void levelWon() {
		listener.stop();
	}

	abstract public void blockClicked(Block block);

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
}
