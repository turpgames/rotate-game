package com.turpgames.rotategame.components.texts;

import com.turpgames.framework.v0.effects.flash.IFlashEffectSubject;
import com.turpgames.framework.v0.impl.Text;

public class Last5Text extends Text implements IFlashEffectSubject {
	public Last5Text() {
		super("NokiaLast", false, false);
	}

	@Override
	public void setColor(float r, float g, float b, float a) {
		getColor().set(r,g,b,a);
	}
}
