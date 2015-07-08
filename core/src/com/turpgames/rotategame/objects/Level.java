package com.turpgames.rotategame.objects;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.util.GameUtils;
import com.turpgames.rotategame.controller.LevelController;
import com.turpgames.rotategame.utils.R;
import com.turpgames.rotategame.utils.R.Connection;
import com.turpgames.utils.Util.Random;

public class Level implements IDrawable {
	protected LevelController parent;
	protected Block[][] blocks;
	
	public boolean levelIsFinished;
	
	public Level(LevelController parent, int[][] mapData) {
		this.parent = parent;
		this.levelIsFinished = false;
		
		generateLevel(mapData);
		randomizeBlocks();
	}
	
	
	public void generateLevel(int[][] mapData) {
		blocks = new Block[mapData.length][mapData[0].length];
		Block[] row;
		for (int i = 0; i < mapData.length; i++) {
			row = new Block[mapData[i].length];
			for (int j = 0; j < mapData[i].length; j++) {
				row[j] = new Block(this, R.MAPOFFSETX + j * R.BLOCKSIZE, R.MAPOFFSETY + (mapData.length - i - 1) * R.BLOCKSIZE, i, j, mapData[i][j], Random.randInt(4));
			}
			blocks[i] = row;
		}
	}
	
	public void randomizeBlocks() {
		int r;
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				r = Random.randInt() % 4;
				while (r >= 0)
				{
					blocks[i][j].rotate();
					r--;
				}
			}
		}
		
		initializeUnconnecteds();
	}
	
	public void initializeUnconnecteds() {
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				blocks[i][j].unconnecteds[0] = false;
				blocks[i][j].unconnecteds[1] = false;
				blocks[i][j].unconnecteds[2] = false;
				blocks[i][j].unconnecteds[3] = false;
			}
		}
		
		// if there are connections towards NORTH and SOUTH on UPPERMOST and LOWERMOST rows, they are definitely unconnected.
		for (int j = 0; j < blocks[0].length; j++) {
			blocks[0][j].unconnecteds[Connection.NORTH] = blocks[0][j].connections[Connection.NORTH];
			blocks[blocks.length-1][j].unconnecteds[Connection.SOUTH] = blocks[blocks.length-1][j].connections[Connection.SOUTH];
		}
		
		// if there are connections towards WEST and EAST on LEFTMOST and RIGHTMOST cols, they are definitely unconnected.
		for (int i = 0; i < blocks.length; i++) {
			blocks[i][0].unconnecteds[Connection.WEST] = blocks[i][0].connections[Connection.WEST];
			blocks[i][blocks[0].length-1].unconnecteds[Connection.EAST] = blocks[i][blocks[0].length-1].connections[Connection.EAST];
		}
		
		// in betweens, east & west and south & north. rightmost and lowermost ommitted
		for (int i = 0; i < blocks.length - 1; i++) {
			for (int j = 0; j < blocks[0].length-1; j++) {
				if (blocks[i][j].connections[Connection.EAST] != blocks[i][j + 1].connections[Connection.WEST]) {
					blocks[i][j].unconnecteds[Connection.EAST] = blocks[i][j].connections[Connection.EAST];
					blocks[i][j + 1].unconnecteds[Connection.WEST] = blocks[i][j + 1].connections[Connection.WEST];
				}
				if (blocks[i][j].connections[Connection.SOUTH] != blocks[i + 1][j].connections[Connection.NORTH]) {
					blocks[i][j].unconnecteds[Connection.SOUTH] = blocks[i][j].connections[Connection.SOUTH];
					blocks[i + 1][j].unconnecteds[Connection.NORTH] = blocks[i + 1][j].connections[Connection.NORTH];
				}
			}
		}

		// SOUTH of rightmost column of blocks
		for (int i = 0; i < blocks.length - 1; i++) {
			if (blocks[i][blocks[0].length - 1].connections[Connection.SOUTH] != blocks[i + 1][blocks[0].length - 1].connections[Connection.NORTH]) {
				blocks[i][blocks[0].length - 1].unconnecteds[Connection.SOUTH] = blocks[i][blocks[0].length - 1].connections[Connection.SOUTH];
				blocks[i + 1][blocks[0].length - 1].unconnecteds[Connection.NORTH] = blocks[i + 1][blocks[0].length - 1].connections[Connection.NORTH];
			}
		}
		
		// EAST of lowermost row of blocks
		for (int i = 0; i < blocks[0].length - 1; i++) {
			if (blocks[blocks.length - 1][i].connections[Connection.EAST] != blocks[blocks.length - 1][i + 1].connections[Connection.WEST]) {
				blocks[blocks.length - 1][i].unconnecteds[Connection.EAST] = blocks[blocks.length - 1][i].connections[Connection.EAST];
				blocks[blocks.length - 1][i + 1].unconnecteds[Connection.WEST] = blocks[blocks.length - 1][i + 1].connections[Connection.WEST];
			}
		}
	}
	
	// updating unconnecteds of the clicked block then checking if level is finished
	public void blockIsClicked(int row, int col)
	{
		Block block = blocks[row][col];
		block.unconnecteds[0] = false;
		block.unconnecteds[1] = false;
		block.unconnecteds[2] = false;
		block.unconnecteds[3] = false;
		
		if (row - 1 >= 0) {
			blocks[row - 1][col].unconnecteds[Connection.SOUTH] = false;
			if (blocks[row - 1][col].connections[Connection.SOUTH] != block.connections[Connection.NORTH]) {
				blocks[row - 1][col].unconnecteds[Connection.SOUTH] = true && blocks[row - 1][col].connections[Connection.SOUTH];
				block.unconnecteds[Connection.NORTH] = true && block.connections[Connection.NORTH];
			}
		}
		else if (row - 1 < 0 && block.connections[Connection.NORTH] == true) {
			block.unconnecteds[Connection.NORTH] = true;
		}	
		
		if (row + 1 < R.ROWNUMBER) {
			blocks[row + 1][col].unconnecteds[Connection.NORTH] = false;
			if (blocks[row + 1][col].connections[Connection.NORTH] != block.connections[Connection.SOUTH]) {
				blocks[row + 1][col].unconnecteds[Connection.NORTH] = true && blocks[row + 1][col].connections[Connection.NORTH];
				block.unconnecteds[Connection.SOUTH] = true && block.connections[Connection.SOUTH];
			}
		}
		else if (row + 1 >= R.ROWNUMBER && block.connections[Connection.SOUTH] == true) {
			block.unconnecteds[Connection.SOUTH] = true;
		}
		
		if (col - 1 >= 0) {
			blocks[row][col - 1].unconnecteds[Connection.EAST] = false;
			if (blocks[row][col - 1].connections[Connection.EAST] != block.connections[Connection.WEST]) {
				blocks[row][col - 1].unconnecteds[Connection.EAST] = true && blocks[row][col - 1].connections[Connection.EAST];
				block.unconnecteds[Connection.WEST] = true && block.connections[Connection.WEST];
			}
		}
		else if (col - 1 < 0 && block.connections[Connection.WEST] == true) {
			block.unconnecteds[Connection.WEST] = true;
		}	
		
		if (col + 1 < R.COLNUMBER) {
			blocks[row][col + 1].unconnecteds[Connection.WEST] = false;
			if (blocks[row][col + 1].connections[Connection.WEST] != block.connections[Connection.EAST]) {
				blocks[row][col + 1].unconnecteds[Connection.WEST] = true && blocks[row][col + 1].connections[Connection.WEST];
				block.unconnecteds[Connection.EAST] = true && block.connections[Connection.EAST];
			}
		}
		else if (col + 1 >= R.COLNUMBER  && block.connections[Connection.EAST] == true) {
			block.unconnecteds[Connection.EAST] = true;
		}
		
		parent.blockClicked(block);
		
		checkLevelFinished();
	}
	
	public void checkLevelFinished()
	{
		boolean unconnected = false;
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				unconnected = unconnected || blocks[i][j].unconnecteds[0];
				unconnected = unconnected || blocks[i][j].unconnecteds[1];
				unconnected = unconnected || blocks[i][j].unconnecteds[2];
				unconnected = unconnected || blocks[i][j].unconnecteds[3];
			}
		}
		this.levelIsFinished = !unconnected;
		if (this.levelIsFinished) {
			parent.levelWon();
		}
	}

	public Block getTouchedBlock(float x, float y) {
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				if (GameUtils.isIn(x, y, blocks[i][j]))
					return blocks[i][j];
			}
		}
		return null;
	}
	
	@Override
	public void draw() {
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				blocks[i][j].draw();
			}
		}
	}
}
