package com.turpgames.rotategame.components;

import com.turpgames.framework.v0.component.ImageButton;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;

public class HelpButton extends ImageButton {
	public HelpButton() {
		setWidth(Game.scale(64f));
		setHeight(Game.scale(64f));
		getLocation().set(Game.viewportToScreenX(10), Game.viewportToScreenY(10));
		setTexture("help");
		setTouchedColor(Color.white());
		deactivate();
	}
}
