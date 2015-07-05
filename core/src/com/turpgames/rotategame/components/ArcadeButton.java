package com.turpgames.rotategame.components;

import com.turpgames.framework.v0.component.Button2;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.GameUtils;
import com.turpgames.rotategame.components.texts.XLargeText;
import com.turpgames.rotategame.utils.R;

public class ArcadeButton extends Button2 {
	
	public ArcadeButton(String s) {
		super();
		
		this.text = new XLargeText();
		this.text.setAlignment(Text.HAlignCenter, Text.VAlignCenter);
		
		setTexture(R.Textures.btn_default, R.Textures.btn_active);
		setColor(R.Colors.BUTTONCOLOR);
		setTextColor(R.Colors.BUTTONLABELCOLOR);
		setText(s);
		setSize(R.BUTTONWIDTH, R.BUTTONHEIGHT);
		setScaleOnTouch(false);
	}
	
	@Override
	public void activate() {
		button.listenInput(true);
	}
	
	@Override
	public void deactivate() {
		button.listenInput(false);
	}
	
	@Override
	protected void onButtonDown() {
		super.onButtonDown();
		text.setPadding(0, -5);
	}
	
	@Override
	protected void onButtonUp() {
		super.onButtonUp();
		text.setPadding(0, 0);
	}
	
	@Override
	protected void onTouchDragged(float x, float y) {
		super.onTouchDragged(x, y);
		if (!GameUtils.isIn(x, y, button)) {
			text.setPadding(0, 0);
		}
	}
}
