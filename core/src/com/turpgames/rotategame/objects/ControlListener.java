package com.turpgames.rotategame.objects;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.impl.InputListener;
import com.turpgames.framework.v0.util.GameUtils;
import com.turpgames.framework.v0.util.Rectangle;
import com.turpgames.framework.v0.util.ShapeDrawer;
import com.turpgames.rotategame.controller.MasterPlayController;
import com.turpgames.rotategame.utils.R;

public class ControlListener extends InputListener implements IDrawable {
	private MasterPlayController parent;
	private Rectangle frameRect;

	private Block focusedBlock;
	public boolean isActive;
	
	public ControlListener(MasterPlayController controller) {
		this.parent = controller;
		this.frameRect = new Rectangle(R.MAPOFFSETX, R.MAPOFFSETY, R.LEVELSIZE, R.LEVELSIZE);
		this.focusedBlock = null;
		
		isActive = true;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		if (!isActive)
			return false;
		if (GameUtils.isIn(x, y, frameRect, false)) {
			Block block = parent.getTouchedBlock(x, y);
			focusedBlock = block;
		}
		return super.touchDown(x, y, pointer, button);
	}
	
	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		if (!isActive)
			return false;
		if (GameUtils.isIn(x, y, frameRect, false)) {
			Block block = parent.getTouchedBlock(x, y);
			focusedBlock = block;
		}
		else {
			focusedBlock = null;
		}
		return super.touchDragged(x, y, pointer);
	}
	
	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		if (!isActive)
			return false;
		if (GameUtils.isIn(x, y, frameRect, false)) {
			if (focusedBlock != null)
				focusedBlock.clicked();
			focusedBlock = null;
		}
		return super.touchUp(x, y, pointer, button);
	}
	
	@Override
	public void draw() {
		if (focusedBlock != null)
			ShapeDrawer.drawRect(focusedBlock.getLocation().x, focusedBlock.getLocation().y, MasterPlayController.getBlockSize(), MasterPlayController.getBlockSize(), R.Colors.BUTTONCOLOR, true, false);
	}

	public void stop() {
		isActive = false;
		focusedBlock = null;
	}
	
	public void start(float size) {
		isActive = true;
	}
}
