package com.turpgames.rotategame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.turpgames.framework.v0.impl.desktop.DesktopEnvironmentProvider;
import com.turpgames.framework.v0.impl.libgdx.GdxGame;
import com.turpgames.framework.v0.util.Game;

public class DesktopLauncher {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Rotate";

		float w = 480;
		float h = 640;
		float x = 1;

		cfg.width = (int) (x * w);
		cfg.height = (int) (x * h);

		Game.setEnvironmentProvider(new DesktopEnvironmentProvider());

		new LwjglApplication(new GdxGame(), cfg);
	}
}
