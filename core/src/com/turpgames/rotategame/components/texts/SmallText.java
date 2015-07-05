package com.turpgames.rotategame.components.texts;

import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.FontManager;

public class SmallText extends Text {
	
	public SmallText() {
		super(FontManager.getTTFont("Nokia", FontManager.SMALL), false, false);
	}
	
}
