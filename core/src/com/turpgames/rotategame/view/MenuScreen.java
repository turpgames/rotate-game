package com.turpgames.rotategame.view;

import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.rotategame.components.ArcadeButton;
import com.turpgames.rotategame.utils.R;
import com.turpgames.rotategame.utils.R.Screens;

public class MenuScreen extends Screen {
	private static final int NUMBER_OF_BUTTONS = 4;
	private static final int BUTTON_SPACE = 10;
	private static final int BUTTONS_OFFSET = (int)(Game.getVirtualHeight() - (NUMBER_OF_BUTTONS * R.BUTTONHEIGHT + (NUMBER_OF_BUTTONS - 1) * BUTTON_SPACE)) / 2;

	private ArcadeButton btnPlay, btnMaster, btnHisc, btnAbout; 
	
	@Override
	public void init() {
		super.init();
		btnPlay = createButton(Screens.play, "Play", BUTTONS_OFFSET);
		btnMaster = createButton(Screens.master, "Master", BUTTONS_OFFSET + R.BUTTONHEIGHT + BUTTON_SPACE);
		btnHisc = createButton(Screens.master, "Hi-Sc", BUTTONS_OFFSET + 2*R.BUTTONHEIGHT + 2*BUTTON_SPACE);
		btnAbout = createButton(Screens.master, "About", BUTTONS_OFFSET + 3*R.BUTTONHEIGHT + 3*BUTTON_SPACE);
	}
	
	@Override
	protected void onAfterActivate() {
		btnPlay.activate();
		btnMaster.activate();
		btnHisc.activate();
		btnAbout.activate();
		super.onAfterActivate();
	}
	
	@Override
	protected void onAfterDeactivate() {
		btnPlay.deactivate();
		btnMaster.deactivate();
		btnHisc.deactivate();
		btnAbout.deactivate();
		super.onAfterDeactivate();
	}
	
	private static ArcadeButton createButton(final String screen, final String label, float yOffset) {
		ArcadeButton btn = new ArcadeButton(label);
		
		btn.setLocation((Game.getVirtualWidth() - btn.getWidth()) / 2, yOffset);
		btn.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				ScreenManager.instance.switchTo(screen, false);
			}
		});
		return btn;
	}
	
	@Override
	public void draw() {
		super.draw();
		btnPlay.draw();
		btnMaster.draw();
		btnHisc.draw();
		btnAbout.draw();
	}
}
