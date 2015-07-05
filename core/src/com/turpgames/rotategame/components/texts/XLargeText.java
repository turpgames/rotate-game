package com.turpgames.rotategame.components.texts;

import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.FontManager;

public class XLargeText extends Text {
	public XLargeText() {
		super(FontManager.getTTFont("Nokia", FontManager.XLARGE), false, false);
	}
}
