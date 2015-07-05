package com.turpgames.rotategame.components.texts;

import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.FontManager;

public class LargeText extends Text {
	public LargeText() {
		super(FontManager.getTTFont("Nokia", FontManager.LARGE), false, false);
	}
}
