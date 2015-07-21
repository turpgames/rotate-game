package com.turpgames.rotategame.utils;

import com.turpgames.framework.v0.ITexture;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.utils.Util;

public class R {
//	public static final int ROWNUMBER = Util.Strings.parseInt(Game.getParam("matrixsize"));
//	public static final int COLNUMBER = Util.Strings.parseInt(Game.getParam("matrixsize"));
	
	public static final int UNIT = (int) (Game.getVirtualWidth() / 55);
	public static final int BUTTONWIDTH =  UNIT * 23;
	public static final int BUTTONHEIGHT =  UNIT * 10;
	public static final int BUTTONOFFSETX =  UNIT;
	
	public static final float LEVELSIZE = UNIT * 50;
	public static final float MAPOFFSETX = ((Game.getVirtualWidth() - LEVELSIZE) / 2);
	public static final float MAPOFFSETY = UNIT * 5;

	public static final float BARWIDTH = UNIT * 1.5f;
	public static final float LEVELFRAMEOFFSETX = MAPOFFSETX - BARWIDTH;
	public static final float LEVELFRAMEOFFSETY = MAPOFFSETY - BARWIDTH;
	public static final float LEVELFRAMEWIDTH = LEVELSIZE + BARWIDTH * 2;
	public static final float LEVELFRAMEHEIGHT = LEVELSIZE + BARWIDTH * 2;
	
	public final static float BUTTONSPACING = MAPOFFSETX;
	public final static float BUTTONSIZE = R.LEVELSIZE / 5;
	
	public static final int MENUNUMBEROFBUTTONS = 4;
	public static final int MENUBUTTONSPACE = R.UNIT;
	public static final int MENUBUTTONSOFFSETX = (int)((Game.getVirtualHeight() - (MENUNUMBEROFBUTTONS * R.BUTTONHEIGHT + (MENUNUMBEROFBUTTONS - 1) * MENUBUTTONSPACE)) / 2 - Game.getVirtualHeight() / 10);
	
	public static final int LEVELTIMEDEC = Util.Strings.parseInt(Game.getParam("timedec"));
//	public static final int STARTTIME = Util.Strings.parseInt(Game.getParam("starttime"));
	
	public static class Connection {
		public static final int EAST = 0;
		public static final int NORTH = 1;
		public static final int WEST = 2;
		public static final int SOUTH = 3;
	}
	
	public static class Screens {
		public static final String menu = "menu";
		public static final String play = "play";
		public static final String tutorial = "tutorial";
		public static final String finished = "finished";
		public static final String skin = "skin";
		public static final String about = "about";
	}
	
	public static class Colors {
		public static final Color TURP_YELLOW = Color.fromHex("#f9b000ff");
		
		public static final Color WASABI1 = Color.fromHex("#FF4242FF");
		public static final Color WASABI2 = Color.fromHex("#D4EE5EFF");
		public static final Color WASABI3 = Color.fromHex("#F0F2EBFF");
		
		public static final Color GOLDFISH1 = Color.fromHex("#FA6900FF");
		public static final Color GOLDFISH2 = Color.fromHex("#69D2E7FF");
		public static final Color GOLDFISH3 = Color.fromHex("#E0E4CCFF");

		public static final Color CHEERUP1 = Color.fromHex("#FF6B6BFF");
		public static final Color CHEERUP2 = Color.fromHex("#4ECDC4FF"); 
		public static final Color CHEERUP3 = Color.fromHex("#C7F464FF");

		public static final Color OCEANFIVE1 = Color.fromHex("#00A0B0FF");
		public static final Color OCEANFIVE2 = Color.fromHex("#CC333FFF");
		public static final Color OCEANFIVE3 = Color.fromHex("#EDC951FF");

		public static final Color SUGAR1 = Color.fromHex("#E97F02FF");
		public static final Color SUGAR2 = Color.fromHex("#8A9B0FFF");
		public static final Color SUGAR3 = Color.fromHex("#F8CA00FF");
		
		public static final Color CURIOSITY1 = Color.fromHex("#DCE9BEFF");
		public static final Color CURIOSITY2 = Color.fromHex("#99173CFF");
		public static final Color CURIOSITY3 = Color.fromHex("#2E2633FF");

		public static final Color HEADACHE1 = Color.fromHex("#BF4D28FF");
		public static final Color HEADACHE2 = Color.fromHex("#E6AC27FF");
		public static final Color HEADACHE3 = Color.fromHex("#F6F7BDFF");
		public static final Color HEADACHE4 = Color.fromHex("#80BCA3FF");
		public static final Color HEADACHE5 = Color.fromHex("#655643FF");

		public static final Color WAVES1 = Color.fromHex("#024055FF");
		public static final Color WAVES2 = Color.fromHex("#9CB5B1FF");
		public static final Color WAVES3 = Color.fromHex("#F9F0F3FF");

		public static final Color HONEY1 = Color.fromHex("#DF7F41FF");
		public static final Color HONEY2 = Color.fromHex("#F7DDADFF");
		public static final Color HONEY3 = Color.fromHex("#BECBADFF");
		
		public static Color COLOR1 = CURIOSITY1;
		public static Color COLOR2 = CURIOSITY2;
		public static Color COLOR3 = CURIOSITY3;
	}
	
	public static class Textures
	{
		public static final ITexture facebookbutton = Game.getResourceManager().getTexture("ab_facebook");
		public static final ITexture twitterbutton = Game.getResourceManager().getTexture("ab_twitter");
		public static final ITexture turpbutton = Game.getResourceManager().getTexture("ab_turp");
		public static final ITexture paypalbutton = Game.getResourceManager().getTexture("ab_paypal");

		public static final ITexture btn_default = Game.getResourceManager().getTexture("btn_default");
		public static final ITexture btn_active = Game.getResourceManager().getTexture("btn_active");
	}
}
