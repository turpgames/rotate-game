package com.turpgames.rotategame.components;

import com.turpgames.framework.v0.component.Button2;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.rotategame.utils.R;

public class ArcadeButton extends Button2 {
	
	public ArcadeButton(String s) {
		super();
		
		this.text = new NormalText();
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
}
