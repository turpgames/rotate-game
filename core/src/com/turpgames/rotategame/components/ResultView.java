package com.turpgames.rotategame.components;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.component.TextButton;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.rotategame.utils.R.Colors;
import com.turpgames.rotategame.utils.R.Screens;

public class ResultView implements IDrawable {
	public static interface IListener {
		void onRestartGame();

		void onShareScore();
	}

	private final TextButton restartButton;
	private final TextButton shareScoreButton;
	private final TextButton aboutButton;
	private final TextButton hiScoresButton;
	private final GameLogo logo;

	private boolean hideShareScoreButton;

	public ResultView(final IListener listener) {
		float y = Game.getVirtualHeight() / 2.0f;
		
		shareScoreButton = createButton("Share Score on Facebook", y + 90f, new IButtonListener() {
			public void onButtonTapped() {
				listener.onShareScore();
			}
		});

		restartButton = createButton("Play Again", y - 180f, new IButtonListener() {
			public void onButtonTapped() {
				listener.onRestartGame();
			}
		});

		aboutButton = createButton("About", y, new IButtonListener() {
			public void onButtonTapped() {
				ScreenManager.instance.switchTo(Screens.about, false);
			}
		});

		hiScoresButton = createButton("Hi Scores", y - 90f, new IButtonListener() {
			public void onButtonTapped() {
				ScreenManager.instance.switchTo(Screens.hiscores, false);
			}
		});

		logo = new GameLogo();
	}

	private static TextButton createButton(String s, float y, IButtonListener ibuttonlistener) {
		TextButton btn = new TextButton(Color.white(), Colors.TURP_YELLOW);
		btn.setListener(ibuttonlistener);
		btn.setFontScale(0.9f);
		btn.setText(s);
		btn.deactivate();

		btn.getLocation().set((Game.getVirtualWidth() - btn.getWidth()) / 2.0f, y);

		return btn;
	}

	public void activate() {
		hideShareScoreButton = false;
		restartButton.activate();
		shareScoreButton.activate();
		aboutButton.activate();
		hiScoresButton.activate();
	}

	public void deactivate() {
		restartButton.deactivate();
		shareScoreButton.deactivate();
		aboutButton.deactivate();
		hiScoresButton.deactivate();
	}

	public void draw() {
		restartButton.draw();
		if (!hideShareScoreButton)
			shareScoreButton.draw();
		aboutButton.draw();
		hiScoresButton.draw();
		logo.draw();
	}

	public void hideShareScoreButton() {
		shareScoreButton.deactivate();
		hideShareScoreButton = true;
	}
}
