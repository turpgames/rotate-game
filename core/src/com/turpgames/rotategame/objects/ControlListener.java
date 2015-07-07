package com.turpgames.rotategame.objects;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.impl.InputListener;
import com.turpgames.framework.v0.impl.TexturedGameObject;
import com.turpgames.framework.v0.util.GameUtils;
import com.turpgames.framework.v0.util.Rectangle;
import com.turpgames.rotategame.controller.LevelController;
import com.turpgames.rotategame.utils.R;

public class ControlListener extends InputListener implements IDrawable {
	private LevelController parent;
	private Rectangle frameRect;

	private Block focusedBlock;
	private TexturedGameObject highlight;
	public boolean levelIsFinished;
	
	public ControlListener(LevelController parent) {
		this.parent = parent;
		this.frameRect = new Rectangle(R.MAPOFFSETX, R.MAPOFFSETY, R.ROWNUMBER * R.BLOCKSIZE, R.COLNUMBER * R.BLOCKSIZE);
		this.focusedBlock = null;
		this.highlight = new TexturedGameObject(R.Textures.DOT) { };
		highlight.setWidth(R.BLOCKSIZE);
		highlight.setHeight(R.BLOCKSIZE);
		highlight.getColor().set(R.Colors.BUTTONCOLOR);
//		highlight.getColor().a = 0.6f;
		dealignHighlight();
		
		levelIsFinished = false;
	}
	
	private void alignHighlight(Block block) {
		highlight.getLocation().x = block.getLocation().x;
		highlight.getLocation().y = block.getLocation().y;
	}
	
	private void dealignHighlight() {
		highlight.getLocation().x = -100;
		highlight.getLocation().y = -100;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		if (GameUtils.isIn(x, y, frameRect, false)) {
			Block block = parent.getTouchedBlock(x, y);
			focusedBlock = block;
			alignHighlight(focusedBlock);
		}
		return super.touchDown(x, y, pointer, button);
	}
	
	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		if (GameUtils.isIn(x, y, frameRect, false)) {
			Block block = parent.getTouchedBlock(x, y);
			focusedBlock = block;
			alignHighlight(focusedBlock);
		}
		else {
			focusedBlock = null;
			dealignHighlight();
		}
		return super.touchDragged(x, y, pointer);
	}
	
	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		if (GameUtils.isIn(x, y, frameRect, false)) {
			focusedBlock.clicked();
			dealignHighlight();
		}
		return super.touchUp(x, y, pointer, button);
	}
	
	@Override
	public void draw() {
		if (!levelIsFinished)
			this.highlight.draw();
	}

	public void levelIsFinished() {
		levelIsFinished = true;
	}
}
