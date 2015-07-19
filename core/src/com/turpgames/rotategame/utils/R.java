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
		public static final String master = "master";
		public static final String about = "about";
		public static final String hiscores = "hiscores";

		public static final String finishednormal = "finishednormal";
		public static final String finishedmaster = "finishedmaster";
		public static final String tutorial = "tutorial";
	}
	
	public static class Colors {
		public static final Color TURP_YELLOW = Color.fromHex("#f9b000ff");
		
		public static final Color WASABI1 = Color.fromHex("#FF4242FF");
		public static final Color WASABI2 = Color.fromHex("#F4FAD2FF");
		public static final Color WASABI3 = Color.fromHex("#D4EE5EFF");
		public static final Color WASABI4 = Color.fromHex("#E1EDB9FF");
		public static final Color WASABI5 = Color.fromHex("#F0F2EBFF");
		
		public static final Color TITLECOLOR = WASABI1;
		public static final Color BACKGROUND = WASABI5;
		public static final Color FADECOLOR = WASABI5;
		public static final Color BUTTONCOLOR = WASABI3;
		public static final Color BUTTONLABELCOLOR = WASABI1;
		public static final Color BLOCKCOLOR = WASABI1;
		public static final Color HIGHLIGHTCOLOR = WASABI3;
		public static final Color UNCONNCOLOR = WASABI5;
		public static final Color LEVELFRAMECOLOR = BLOCKCOLOR;
		public static final Color TIMERBARCOLOR = BUTTONCOLOR;
		public static final Color LAST5SECCOLOR = WASABI2;
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
