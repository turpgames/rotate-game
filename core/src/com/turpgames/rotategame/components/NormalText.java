package com.turpgames.rotategame.components;

import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.FontManager;

public class NormalText extends Text {

	public NormalText() {
		super(FontManager.getTTFont("Nokia", FontManager.NORMAL), false, false);
	}
	
}
