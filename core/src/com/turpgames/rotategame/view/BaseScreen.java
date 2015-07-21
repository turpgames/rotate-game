package com.turpgames.rotategame.view;

import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.ShapeDrawer;
import com.turpgames.rotategame.utils.R;

public class BaseScreen extends Screen {
	@Override
	public void draw() {
		ShapeDrawer.drawRect(0,0,Game.getScreenWidth(), Game.getScreenHeight(), R.Colors.COLOR3, true, true);
		super.draw();
	}
}
