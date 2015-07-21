package com.turpgames.rotategame.objects;

import com.turpgames.framework.v0.IDrawingInfo;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.ShapeDrawer;
import com.turpgames.rotategame.controller.MasterPlayController;
import com.turpgames.rotategame.utils.R;
import com.turpgames.rotategame.utils.R.Connection;

public class BlockDrawer {
	private static Color OUTCOLOR = R.Colors.COLOR2;
	private static Color INCOLOR = R.Colors.COLOR1;
	private static float U;
	
	public static void drawBlock(int type, int direction, IDrawingInfo obj) {
		U = MasterPlayController.getBlockSize() / 12;
		if (type == Block.CONN0)
			return;
		else if (type == Block.CONN1)
			drawConn1(direction, obj);
		else if (type == Block.CONN2CRV)
			drawConn2C(direction, obj);
		else if (type == Block.CONN2STR)
			drawConn2S(direction, obj);
		else if (type == Block.CONN3)
			drawConn3(direction, obj);
		else if (type == Block.CONN4)
			drawConn4(direction, obj);
	}
	
	private static void drawRect(IDrawingInfo obj, float x, float y, float w, float h, Color color) {
		ShapeDrawer.drawRect(obj.getLocation().x + x, obj.getLocation().y + y, w, h, color, true, false);
	}
	
	private static void drawConn1(int direction, IDrawingInfo obj) {
		if (direction == Connection.EAST) {
			drawRect(obj, 4*U, 3*U, 8*U, 6*U, OUTCOLOR);
			drawRect(obj, 3*U, 4*U, U, 4*U, OUTCOLOR);
			drawRect(obj, 5*U, 5*U, 7*U, 2*U, INCOLOR);
		}
		else if (direction == Connection.NORTH) {
			drawRect(obj, 3*U, 4*U, 6*U, 8*U, OUTCOLOR);
			drawRect(obj, 4*U, 3*U, 4*U, U, OUTCOLOR);
			drawRect(obj, 5*U, 5*U, 2*U, 7*U, INCOLOR);
		}
		else if (direction == Connection.WEST) {
			drawRect(obj, 0, 3*U, 8*U, 6*U, OUTCOLOR);
			drawRect(obj, 8*U, 4*U, U, 4*U, OUTCOLOR);
			drawRect(obj, 0, 5*U, 7*U, 2*U, INCOLOR);
		}
		else {
			drawRect(obj, 3*U, 0, 6*U, 8*U, OUTCOLOR);
			drawRect(obj, 4*U, 8*U, 4*U, U, OUTCOLOR);
			drawRect(obj, 5*U, 0, 2*U, 7*U, INCOLOR);
		}
	}

	private static void drawConn2C(int direction, IDrawingInfo obj) {
		if (direction == Connection.EAST) {
			drawRect(obj, 5*U, 3*U, 7*U, 6*U, OUTCOLOR);
			drawRect(obj, 3*U, 5*U, 6*U, 7*U, OUTCOLOR);
			
			drawRect(obj, 9*U, 9*U, U, U, OUTCOLOR);
			drawRect(obj, 4*U, 4*U, U, U, OUTCOLOR);
			
			drawRect(obj, 5*U, 5*U, 7*U, 2*U, INCOLOR);
			drawRect(obj, 5*U, 5*U, 2*U, 7*U, INCOLOR);
			
			drawRect(obj, 5*U, 5*U, U, U, OUTCOLOR);
			drawRect(obj, 7*U, 7*U, U, U, INCOLOR);
			drawRect(obj, 7*U, 8*U, U, U, INCOLOR);
			drawRect(obj, 8*U, 7*U, U, U, INCOLOR);
		}
		else if (direction == Connection.NORTH) {
			drawRect(obj, 0, 3*U, 7*U, 6*U, OUTCOLOR);
			drawRect(obj, 3*U, 5*U, 6*U, 7*U, OUTCOLOR);

			drawRect(obj, 2*U, 9*U, U, U, OUTCOLOR);
			drawRect(obj, 7*U, 4*U, U, U, OUTCOLOR);
			
			drawRect(obj, 0, 5*U, 7*U, 2*U, INCOLOR);
			drawRect(obj, 5*U, 5*U, 2*U, 7*U, INCOLOR);
			
			drawRect(obj, 6*U, 5*U, U, U, OUTCOLOR);
			drawRect(obj, 4*U, 7*U, U, U, INCOLOR);
			drawRect(obj, 4*U, 8*U, U, U, INCOLOR);
			drawRect(obj, 3*U, 7*U, U, U, INCOLOR);
		}
		else if (direction == Connection.WEST) {
			drawRect(obj, 0, 3*U, 7*U, 6*U, OUTCOLOR);
			drawRect(obj, 3*U, 0, 6*U, 7*U, OUTCOLOR);
			
			drawRect(obj, 2*U, 2*U, U, U, OUTCOLOR);
			drawRect(obj, 7*U, 7*U, U, U, OUTCOLOR);

			drawRect(obj, 0, 5*U, 7*U, 2*U, INCOLOR);
			drawRect(obj, 5*U, 0, 2*U, 7*U, INCOLOR);
			
			drawRect(obj, 6*U, 6*U, U, U, OUTCOLOR);
			drawRect(obj, 4*U, 4*U, U, U, INCOLOR);
			drawRect(obj, 3*U, 4*U, U, U, INCOLOR);
			drawRect(obj, 4*U, 3*U, U, U, INCOLOR);
		}
		else {
			drawRect(obj, 5*U, 3*U, 7*U, 6*U, OUTCOLOR);
			drawRect(obj, 3*U, 0, 6*U, 7*U, OUTCOLOR);
			
			drawRect(obj, 9*U, 2*U, U, U, OUTCOLOR);
			drawRect(obj, 4*U, 7*U, U, U, OUTCOLOR);

			drawRect(obj, 5*U, 5*U, 7*U, 2*U, INCOLOR);
			drawRect(obj, 5*U, 0, 2*U, 7*U, INCOLOR);
			
			drawRect(obj, 5*U, 6*U, U, U, OUTCOLOR);
			drawRect(obj, 7*U, 4*U, U, U, INCOLOR);
			drawRect(obj, 8*U, 4*U, U, U, INCOLOR);
			drawRect(obj, 7*U, 3*U, U, U, INCOLOR);
		}
	}

	private static void drawConn2S(int direction, IDrawingInfo obj) {
		if (direction == Connection.EAST) {
			drawRect(obj, 0, 3*U, 12*U, 6*U, OUTCOLOR);
			drawRect(obj, 0, 5*U, 12*U, 2*U, INCOLOR);
		}
		else if (direction == Connection.NORTH) {
			drawRect(obj, 3*U, 0, 6*U, 12*U, OUTCOLOR);
			drawRect(obj, 5*U, 0, 2*U, 12*U, INCOLOR);
		}
		else if (direction == Connection.WEST) {
			drawRect(obj, 0, 3*U, 12*U, 6*U, OUTCOLOR);
			drawRect(obj, 0, 5*U, 12*U, 2*U, INCOLOR);
		}
		else {
			drawRect(obj, 3*U, 0, 6*U, 12*U, OUTCOLOR);
			drawRect(obj, 5*U, 0, 2*U, 12*U, INCOLOR);
		}
	}

	private static void drawConn3(int direction, IDrawingInfo obj) {
		if (direction == Connection.EAST) {
			drawRect(obj, 0, 3*U, 12*U, 6*U, OUTCOLOR);
			drawRect(obj, 3*U, 3*U, 6*U, 9*U, OUTCOLOR);
			
			drawRect(obj, 0, 5*U, 12*U, 2*U, INCOLOR);
			drawRect(obj, 5*U, 5*U, 2*U, 7*U, INCOLOR);

			drawRect(obj, 9*U, 9*U, U, U, OUTCOLOR);
			drawRect(obj, 2*U, 9*U, U, U, OUTCOLOR);
			
			drawRect(obj, 4*U, 7*U, U, U, INCOLOR);
			drawRect(obj, 3*U, 7*U, U, U, INCOLOR);
			drawRect(obj, 4*U, 8*U, U, U, INCOLOR);

			drawRect(obj, 7*U, 7*U, U, U, INCOLOR);
			drawRect(obj, 7*U, 8*U, U, U, INCOLOR);
			drawRect(obj, 8*U, 7*U, U, U, INCOLOR);
		}
		else if (direction == Connection.NORTH) {
			drawRect(obj, 0, 3*U, 9*U, 6*U, OUTCOLOR);
			drawRect(obj, 3*U, 0, 6*U, 12*U, OUTCOLOR);
			
			drawRect(obj, 0, 5*U, 7*U, 2*U, INCOLOR);
			drawRect(obj, 5*U, 0, 2*U, 12*U, INCOLOR);
	
			drawRect(obj, 2*U, 9*U, U, U, OUTCOLOR);
			drawRect(obj, 2*U, 2*U, U, U, OUTCOLOR);
			
			drawRect(obj, 4*U, 7*U, U, U, INCOLOR);
			drawRect(obj, 3*U, 7*U, U, U, INCOLOR);
			drawRect(obj, 4*U, 8*U, U, U, INCOLOR);
	
			drawRect(obj, 4*U, 4*U, U, U, INCOLOR);
			drawRect(obj, 4*U, 3*U, U, U, INCOLOR);
			drawRect(obj, 3*U, 4*U, U, U, INCOLOR);
		}
		else if (direction == Connection.WEST) {
			drawRect(obj, 0, 3*U, 12*U, 6*U, OUTCOLOR);
			drawRect(obj, 3*U, 0, 6*U, 9*U, OUTCOLOR);
			
			drawRect(obj, 0, 5*U, 12*U, 2*U, INCOLOR);
			drawRect(obj, 5*U, 0, 2*U, 7*U, INCOLOR);

			drawRect(obj, 9*U, 2*U, U, U, OUTCOLOR);
			drawRect(obj, 2*U, 2*U, U, U, OUTCOLOR);

			drawRect(obj, 7*U, 4*U, U, U, INCOLOR);
			drawRect(obj, 7*U, 3*U, U, U, INCOLOR);
			drawRect(obj, 8*U, 4*U, U, U, INCOLOR);

			drawRect(obj, 4*U, 4*U, U, U, INCOLOR);
			drawRect(obj, 4*U, 3*U, U, U, INCOLOR);
			drawRect(obj, 3*U, 4*U, U, U, INCOLOR);
		}
		else {
			drawRect(obj, 3*U, 3*U, 9*U, 6*U, OUTCOLOR);
			drawRect(obj, 3*U, 0, 6*U, 12*U, OUTCOLOR);
			
			drawRect(obj, 5*U, 5*U, 7*U, 2*U, INCOLOR);
			drawRect(obj, 5*U, 0, 2*U, 12*U, INCOLOR);

			drawRect(obj, 9*U, 9*U, U, U, OUTCOLOR);
			drawRect(obj, 9*U, 2*U, U, U, OUTCOLOR);
			
			drawRect(obj, 7*U, 4*U, U, U, INCOLOR);
			drawRect(obj, 7*U, 3*U, U, U, INCOLOR);
			drawRect(obj, 8*U, 4*U, U, U, INCOLOR);

			drawRect(obj, 7*U, 7*U, U, U, INCOLOR);
			drawRect(obj, 7*U, 8*U, U, U, INCOLOR);
			drawRect(obj, 8*U, 7*U, U, U, INCOLOR);
		}
	}

	private static void drawConn4(int direction, IDrawingInfo obj) {
		drawRect(obj, 0, 3*U, 12*U, 6*U, OUTCOLOR);
		drawRect(obj, 3*U, 0, 6*U, 12*U, OUTCOLOR);
		drawRect(obj, 0, 5*U, 12*U, 2*U, INCOLOR);
		drawRect(obj, 5*U, 0, 2*U, 12*U, INCOLOR);

		drawRect(obj, 9*U, 9*U, U, U, OUTCOLOR);
		drawRect(obj, 2*U, 9*U, U, U, OUTCOLOR);
		drawRect(obj, 9*U, 2*U, U, U, OUTCOLOR);
		drawRect(obj, 2*U, 2*U, U, U, OUTCOLOR);
		
		drawRect(obj, 4*U, 7*U, U, U, INCOLOR);
		drawRect(obj, 3*U, 7*U, U, U, INCOLOR);
		drawRect(obj, 4*U, 8*U, U, U, INCOLOR);

		drawRect(obj, 7*U, 4*U, U, U, INCOLOR);
		drawRect(obj, 7*U, 3*U, U, U, INCOLOR);
		drawRect(obj, 8*U, 4*U, U, U, INCOLOR);

		drawRect(obj, 7*U, 7*U, U, U, INCOLOR);
		drawRect(obj, 7*U, 8*U, U, U, INCOLOR);
		drawRect(obj, 8*U, 7*U, U, U, INCOLOR);

		drawRect(obj, 4*U, 4*U, U, U, INCOLOR);
		drawRect(obj, 4*U, 3*U, U, U, INCOLOR);
		drawRect(obj, 3*U, 4*U, U, U, INCOLOR);
	}

	public static void updateColor() {
		OUTCOLOR = R.Colors.COLOR2;
		INCOLOR = R.Colors.COLOR1;
	}
}
