package com.turpgames.rotategame.components.texts;

import com.turpgames.framework.v0.effects.flash.IFlashEffectSubject;
import com.turpgames.framework.v0.impl.Text;

public class HugeText extends Text implements IFlashEffectSubject {
	public HugeText() {
		super("NokiaHuge", false, false);
	}

	@Override
	public void setColor(float r, float g, float b, float a) {
		getColor().set(r, g, b, a);
	}
}
