package com.turpgames.rotategame.objects;

import java.util.Arrays;

import com.turpgames.framework.v0.impl.GameObject;

public class Block extends GameObject {
	public static final int CONN0 = 0;
	public static final int CONN1 = 1;
	public static final int CONN2CRV = 2;
	public static final int CONN2STR = 3;
	public static final int CONN3 = 4;
	public static final int CONN4 = 5;
	
	private Level parent;
	private int row;
	private int col;
	
	private int direction;
	
	public boolean[] connections;
	public boolean[] unconnecteds;
	public int type;
	
	public Block(Level parent, float X, float Y, int row, int col, int type, int rotation) {
		this.parent = parent;
		this.row = row;
		this.col = col;
		this.type = type;
		
		if(type == CONN0) {
			connections = new boolean[] {false, false, false, false};
		}
		else if(type == CONN1) {
			connections = new boolean[] {true, false, false, false};
		}
		else if(type == CONN2CRV) {
			connections = new boolean[] {true, true, false, false};
		}
		else if(type == CONN2STR) {
			connections = new boolean[] {true, false, true, false};
		}
		else if(type == CONN3) {
			connections = new boolean[] {true, true, true, false};
		}
		else if(type == CONN4) {
			connections = new boolean[] {true, true, true, true};
		}
		
		this.direction = 0;
		for (int i = 0; i < direction; i++) {
			rotate();
		}

		this.getLocation().set(X, Y);
		this.setWidth(parent.getBlockSize());
		this.setHeight(parent.getBlockSize());
//		this.getColor().set(R.Colors.BLOCKCOLOR);
		
		unconnecteds = new boolean[4];
		Arrays.fill(unconnecteds, false);
	}
	
	public void rotate() {
		boolean temp = connections[3];
		connections[3] = connections[2];
		connections[2] = connections[1];
		connections[1] = connections[0];
		connections[0] = temp;
		
		this.direction += 1;
		this.direction %= 4;
	}
	
	public void clicked() {
		rotate();
		parent.blockIsClicked(row, col);		
	}
	
	@Override
	public void draw() {
		if (type == CONN0)
			return;
		
//		TextureDrawer.draw(R.Textures.blockImgs[type-1][direction], this);
		BlockDrawer.drawBlock(type, direction, this);
		//ShapeDrawer.drawRect(this, true);

//		this.getColor().set(R.Colors.UNCONNCOLOR);
//		for (int i = 0; i < 4; i++) {
//			if (unconnecteds[i])
//				TextureDrawer.draw(R.Textures.unconnectedImgs[i], this);
//		}
//		this.getColor().set(R.Colors.BLOCKCOLOR);
	}
}
