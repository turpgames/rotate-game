package com.turpgames.rotategame.view;

import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.ShapeDrawer;
import com.turpgames.rotategame.components.ArcadeButton;
import com.turpgames.rotategame.components.texts.LargeText;
import com.turpgames.rotategame.components.texts.XXLargeText;
import com.turpgames.rotategame.utils.R;

public class SkinScreen extends BaseScreen {
	private ArcadeButton btn1, btn2, btn3; 
	private ArcadeButton active;
	private Text title, subTitle;

	private ArcadeButton btnMenu;
	
	@Override
	public void init() {
		super.init();
		title = new XXLargeText();
		title.setAlignment(Text.HAlignCenter, Text.VAlignBottom);
		title.setPadY(Game.getVirtualHeight() * 8 / 10);
		title.setText("Skins:");
		title.getColor().set(R.Colors.COLOR1);
		registerDrawable(title, Game.LAYER_BACKGROUND);
		
		subTitle = new LargeText();
		subTitle.setAlignment(Text.HAlignCenter, Text.VAlignBottom);
		subTitle.setPadY(Game.getVirtualHeight() * 8 / 10 - R.UNIT * 15);
		subTitle.setText("A matter of\ntaste...");
		subTitle.getColor().set(R.Colors.COLOR1);
		registerDrawable(subTitle, Game.LAYER_BACKGROUND);
		
		btnMenu = new ArcadeButton("Menu");
		btnMenu.setLocation((Game.getVirtualWidth() - btnMenu.getWidth()) / 2, R.UNIT * 3);
		btnMenu.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
//				levelWon();
				ScreenManager.instance.switchTo(R.Screens.menu, false);
			}
		});
		registerDrawable(btnMenu, Game.LAYER_BACKGROUND);
		
		btn1 = createButton("BUBBLEGUM", "Bubblegum", R.UNIT * 3 + 3 * R.BUTTONHEIGHT + 3 * R.MENUBUTTONSPACE);			
		btn2 = createButton("WASABI", "Wasabi", R.UNIT * 3 + 2 * R.BUTTONHEIGHT + 2 * R.MENUBUTTONSPACE);	
		btn3 = createButton("HONEY", "Honey", R.UNIT * 3 + R.BUTTONHEIGHT + R.MENUBUTTONSPACE);
		btn1.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				R.Colors.COLOR1 = R.Colors.CURIOSITY1;
				R.Colors.COLOR2 = R.Colors.CURIOSITY2;
				R.Colors.COLOR3 = R.Colors.CURIOSITY3;
				updateColors();
				active = btn1;
			}
		});
		btn2.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {	
				R.Colors.COLOR1 = R.Colors.WASABI1;
				R.Colors.COLOR2 = R.Colors.WASABI2;
				R.Colors.COLOR3 = R.Colors.WASABI3;
				updateColors();
				active = btn2;
			}
		});
		btn3.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				R.Colors.COLOR1 = R.Colors.HONEY1;
				R.Colors.COLOR2 = R.Colors.HONEY2;
				R.Colors.COLOR3 = R.Colors.HONEY3;
				updateColors();
				active = btn3;
			}
		});
		
		registerDrawable(btn1, Game.LAYER_BACKGROUND);
		registerDrawable(btn2, Game.LAYER_BACKGROUND);
		registerDrawable(btn3, Game.LAYER_BACKGROUND);
		
		active = btn1;
	}
	
	@Override
	protected void onAfterActivate() {
		btn1.activate();
		btn2.activate();
		btn3.activate();
		btnMenu.activate();
		super.onAfterActivate();
	}
	
	@Override
	protected void onAfterDeactivate() {
		btn1.deactivate();
		btn2.deactivate();
		btn3.deactivate();
		btnMenu.deactivate();
		super.onAfterDeactivate();
	}
	
	protected ArcadeButton createButton(final String skin, final String label, float yOffset) {
		ArcadeButton btn = new ArcadeButton(label);
		btn.setSize(R.BUTTONWIDTH*1.5f, R.BUTTONHEIGHT);
		btn.setLocation((Game.getVirtualWidth() - btn.getWidth()) / 2, yOffset);
		return btn;
	}
	
	protected void updateColors() {
		btn1.updateColor();
		btn2.updateColor();
		btn3.updateColor();
		btnMenu.updateColor();
		title.getColor().set(R.Colors.COLOR1);
		subTitle.getColor().set(R.Colors.COLOR1);
	}
	
	@Override
	public void draw() {
		super.draw();

		ShapeDrawer.drawRect(active.getX() - R.MENUBUTTONSPACE / 2, active.getY() - R.MENUBUTTONSPACE / 2, R.MENUBUTTONSPACE + R.BUTTONWIDTH * 1.5f, R.MENUBUTTONSPACE + R.BUTTONHEIGHT, R.Colors.COLOR1, R.UNIT, false);
	}
	
	@Override
	protected boolean onBack() {
		ScreenManager.instance.switchTo(R.Screens.menu, false);
		return super.onBack();
	}
}

