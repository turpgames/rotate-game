package com.turpgames.rotategame.components;

import com.turpgames.framework.v0.ITexture;
import com.turpgames.framework.v0.component.ImageButton;
import com.turpgames.framework.v0.effects.flash.IFlashEffectSubject;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.rotategame.utils.R;

public class ControlButton extends ImageButton implements IFlashEffectSubject {
	public ControlButton(ITexture texture) {
		super();
		setTexture(texture);
		setDefaultColor(R.Colors.BUTTONCOLOR);
		setTouchedColor(R.Colors.BUTTONCOLOR);
		setWidth(R.BUTTONSIZETOSCREEN);
		setHeight(R.BUTTONSIZETOSCREEN);
	}

	@Override
	public void setColor(float r, float g, float b, float a) {
		setDefaultColor(new Color(r,g,b,a));
	}
}
