package com.turpgames.rotategame.utils;

import com.turpgames.framework.v0.ITexture;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.utils.Util;

public class R {
	public static final int ROWNUMBER = Util.Strings.parseInt(Game.getParam("matrixsize"));
	public static final int COLNUMBER = Util.Strings.parseInt(Game.getParam("matrixsize"));
	
	public static final int UNIT = (int) (Game.getVirtualHeight() / 80);
	public static final int BUTTONWIDTH =  UNIT * 23;
	public static final int BUTTONHEIGHT =  UNIT * 10;
	public static final int BUTTONOFFSETX =  UNIT;
	
	public static final int BLOCKSIZE = UNIT * 50 / ROWNUMBER;
	
	public static final float MAPOFFSETX = ((Game.getVirtualWidth() - ROWNUMBER * BLOCKSIZE) / 2);
	public static final float MAPOFFSETY = UNIT * 5;

	public static final float BARWIDTH = UNIT * 1.5f;
	public static final float LEVELFRAMEOFFSETX = MAPOFFSETX - BARWIDTH;
	public static final float LEVELFRAMEOFFSETY = MAPOFFSETY - BARWIDTH;
	public static final float LEVELFRAMEWIDTH = R.ROWNUMBER * R.BLOCKSIZE + BARWIDTH * 2;
	public static final float LEVELFRAMEHEIGHT = R.ROWNUMBER * R.BLOCKSIZE + BARWIDTH * 2;
	
	public final static float BUTTONSPACING = Game.scale(UNIT * 2);
	public final static float BUTTONSIZE = 64;
	public final static float BUTTONSIZETOSCREEN = Game.scale(BUTTONSIZE);
	
	public static final int MENUNUMBEROFBUTTONS = 4;
	public static final int MENUBUTTONSPACE = R.UNIT;
	public static final int MENUBUTTONSOFFSETX = (int)((Game.getVirtualHeight() - (MENUNUMBEROFBUTTONS * R.BUTTONHEIGHT + (MENUNUMBEROFBUTTONS - 1) * MENUBUTTONSPACE)) / 2 - Game.getVirtualHeight() / 10);

	
//	public static final float HUDPAD = UNIT * 3;
	
	//public static final int MAPOFFSETX = 50;
	//public static final int MAPOFFSETY = 50;
	
	public static final int STARTTIME = Util.Strings.parseInt(Game.getParam("starttime"));
//	public static final int STARTSCORE = 5;
	public static final int LEVELTIMEDEC = Util.Strings.parseInt(Game.getParam("timedec"));
	
	
	
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
		public static final ITexture btnMenu = Game.getResourceManager().getTexture("btn_menu");
		public static final ITexture btnRestart = Game.getResourceManager().getTexture("btn_restart");
		
		public static final ITexture facebookbutton = Game.getResourceManager().getTexture("ab_facebook");
		public static final ITexture twitterbutton = Game.getResourceManager().getTexture("ab_twitter");
		public static final ITexture turpbutton = Game.getResourceManager().getTexture("ab_turp");

		public static final ITexture btn_default = Game.getResourceManager().getTexture("btn_default");
		public static final ITexture btn_active = Game.getResourceManager().getTexture("btn_active");
		
		public static final ITexture levelFrame = Game.getResourceManager().getTexture("levelFrame");
		public static final ITexture efxSprite = Game.getResourceManager().getTexture("efxSprite");

		public static final ITexture grid = Game.getResourceManager().getTexture("grid");
		public static final ITexture highlight = Game.getResourceManager().getTexture("highlight");
		
		public static final ITexture block1_1 = Game.getResourceManager().getTexture("block1-1");
		public static final ITexture block1_2 = Game.getResourceManager().getTexture("block1-2");
		public static final ITexture block1_3 = Game.getResourceManager().getTexture("block1-3");
		public static final ITexture block1_4 = Game.getResourceManager().getTexture("block1-4");
		
		public static final ITexture block2C_1 = Game.getResourceManager().getTexture("block2C-1");
		public static final ITexture block2C_2 = Game.getResourceManager().getTexture("block2C-2");
		public static final ITexture block2C_3 = Game.getResourceManager().getTexture("block2C-3");
		public static final ITexture block2C_4 = Game.getResourceManager().getTexture("block2C-4");
		
		public static final ITexture block2S_1 = Game.getResourceManager().getTexture("block2S-1");
		public static final ITexture block2S_2 = Game.getResourceManager().getTexture("block2S-2");
		public static final ITexture block2S_3 = Game.getResourceManager().getTexture("block2S-3");
		public static final ITexture block2S_4 = Game.getResourceManager().getTexture("block2S-4");
		
		public static final ITexture block3_1 = Game.getResourceManager().getTexture("block3-1");
		public static final ITexture block3_2 = Game.getResourceManager().getTexture("block3-2");
		public static final ITexture block3_3 = Game.getResourceManager().getTexture("block3-3");
		public static final ITexture block3_4 = Game.getResourceManager().getTexture("block3-4");
		
		public static final ITexture block4_1 = Game.getResourceManager().getTexture("block4-1");
		public static final ITexture block4_2 = Game.getResourceManager().getTexture("block4-2");
		public static final ITexture block4_3 = Game.getResourceManager().getTexture("block4-3");
		public static final ITexture block4_4 = Game.getResourceManager().getTexture("block4-4");
		
		public static final ITexture unconneast = Game.getResourceManager().getTexture("unconneast");
		public static final ITexture unconnnorth = Game.getResourceManager().getTexture("unconnnorth");
		public static final ITexture unconnwest = Game.getResourceManager().getTexture("unconnwest");
		public static final ITexture unconnsouth = Game.getResourceManager().getTexture("unconnsouth");
		
		public static final ITexture tutarrow = Game.getResourceManager().getTexture("tutarrow");
		public static final ITexture tutclick = Game.getResourceManager().getTexture("tutclick");
		public static final ITexture tutoksign = Game.getResourceManager().getTexture("tutoksign");
		public static final ITexture handcursor = Game.getResourceManager().getTexture("handcursor");
		public static final ITexture DOT = Game.getResourceManager().getTexture("bgdot");
		
		public static final ITexture[][] blockImgs;
		public static final ITexture[] unconnectedImgs;
		
		static {
			ITexture[] block1Imgs;
			ITexture[] block2CImgs;
			ITexture[] block2SImgs;
			ITexture[] block3Imgs;
			ITexture[] block4Imgs;
			
			block1Imgs = new ITexture[4];
			block1Imgs[0] = block1_1;
			block1Imgs[1] = block1_2;
			block1Imgs[2] = block1_3;
			block1Imgs[3] = block1_4;
			
			block2CImgs = new ITexture[4];
			block2CImgs[0] = block2C_1;
			block2CImgs[1] = block2C_2;
			block2CImgs[2] = block2C_3;
			block2CImgs[3] = block2C_4;

			block2SImgs = new ITexture[4];
			block2SImgs[0] = block2S_1;
			block2SImgs[1] = block2S_2;
			block2SImgs[2] = block2S_3;
			block2SImgs[3] = block2S_4;

			block3Imgs = new ITexture[4];
			block3Imgs[0] = block3_1;
			block3Imgs[1] = block3_2;
			block3Imgs[2] = block3_3;
			block3Imgs[3] = block3_4;

			block4Imgs = new ITexture[4];
			block4Imgs[0] = block4_1;
			block4Imgs[1] = block4_2;
			block4Imgs[2] = block4_3;
			block4Imgs[3] = block4_4;
			
			blockImgs = new ITexture[5][4];
			blockImgs[0] = block1Imgs;
			blockImgs[1] = block2CImgs;
			blockImgs[2] = block2SImgs;
			blockImgs[3] = block3Imgs;
			blockImgs[4] = block4Imgs;
			
			unconnectedImgs = new ITexture[4];
			unconnectedImgs[Connection.EAST] = unconneast;
			unconnectedImgs[Connection.WEST] = unconnwest;
			unconnectedImgs[Connection.NORTH] = unconnnorth;
			unconnectedImgs[Connection.SOUTH] = unconnsouth;
		};
	}
}
