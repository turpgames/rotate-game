package com.turpgames.rotategame.objects;

import com.turpgames.rotategame.utils.R.Connection;
import com.turpgames.utils.Util.Random;

public class MapData {
	public int[][] mapData;
	public int mapIndex;
	public int mapTime;

	public MapData(int[][] mapData, int mapIndex, int mapTime) {
		this.mapData = mapData;
		this.mapIndex = mapIndex;
		this.mapTime = mapTime;
	}

	private static Boolean randBool() {
		int r = Random.randInt(40);
		r %= 2;
		if (r == 0)
			return false;
		return true;
	}

	public static int[][] randMap(int mapSize) {
		boolean[][][] connectionsMap = new boolean[mapSize][mapSize][4];

		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				connectionsMap[i][j][0] = false;
				connectionsMap[i][j][1] = false;
				connectionsMap[i][j][2] = false;
				connectionsMap[i][j][3] = false;
			}
		}

		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize - 1; j++) {
				if (randBool()) {
					connectionsMap[i][j][Connection.EAST] = true;
					connectionsMap[i][j + 1][Connection.WEST] = true;
				}
			}
		}

		for (int i = 0; i < mapSize - 1; i++) {
			for (int j = 0; j < mapSize; j++) {
				if (randBool()) {
					connectionsMap[i][j][Connection.SOUTH] = true;
					connectionsMap[i + 1][j][Connection.NORTH] = true;
				}
			}
		}

		boolean empty = true; // to prevent generating empty levels by cbance.
		int[][] map = new int[mapSize][mapSize];
		int trues;
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				trues = 0;
				for (int k = 0; k < 4; k++) {
					if (connectionsMap[i][j][k])
						trues++;
				}
				if (trues == 0)
					map[i][j] = Block.CONN0;
				else if (trues == 1)
					map[i][j] = Block.CONN1;
				else if (trues == 2) {
					if (connectionsMap[i][j][0] && connectionsMap[i][j][1]
							|| connectionsMap[i][j][1]
							&& connectionsMap[i][j][2]
							|| connectionsMap[i][j][2]
							&& connectionsMap[i][j][3]
							|| connectionsMap[i][j][3]
							&& connectionsMap[i][j][0]) // curved
					{
						map[i][j] = Block.CONN2CRV;
					} else { // straight
						map[i][j] = Block.CONN2STR;
					}
				} else if (trues == 3)
					map[i][j] = Block.CONN3;
				else if (trues == 4)
					map[i][j] = Block.CONN4;
				
				if (trues != 0)
					empty = false;
			}
		}
				
		if (mapSize >= 3) { // swastika filter
			for (int i = 1; i < mapSize - 1; i++) {
				for (int j = 0; j < mapSize - 1; j++) {
					if (map[i][j] == Block.CONN4) {
						if (map[i-1][j] == Block.CONN2CRV && map[i+1][j] == Block.CONN2CRV && 
								map[i][j-1] == Block.CONN2CRV && map[i][j+1] == Block.CONN2CRV){
							if (map[i-1][j-1] == Block.CONN1 && map[i+1][j+1] == Block.CONN1 && 
									map[i+1][j-1] == Block.CONN1 && map[i-1][j+1] == Block.CONN1){
								return randMap(mapSize);
							}
						}
					}
				}
			}
		}
		
		if (empty)
			return randMap(mapSize);
		else if (mapSize == 3 && map[1][1] == Block.CONN4) // swastika-like filter
			return randMap(mapSize);
		else
			return map;
//		return new int[][]{
//	     		{2, 2, 0, 0, 0},
//	    		{2, 2, 0, 0, 0},
//	    		{0, 0, 0, 0, 0},
//	    		{0, 0, 0, 0, 0},
//	    		{0, 0, 0, 0, 0}};
//		return new int[][]{
//	     		{0, 1, 0, 1, 0},
//	    		{0, 3, 0, 3, 0},
//	    		{0, 2, 4, 2, 0},
//	    		{0, 0, 3, 0, 0},
//	    		{0, 1, 4, 1, 0}};
	}

	public static MapData[] getAllMapData() 
	{
		MapData[] allMapData = new MapData[18];
		
		int index = 0;
		MapData mapData;
		
		// glass
		mapData = new MapData(new int[][]{
     		{0, 1, 0, 1, 0},
    		{0, 3, 0, 3, 0},
    		{0, 2, 4, 2, 0},
    		{0, 0, 3, 0, 0},
    		{0, 1, 4, 1, 0}}, index, 10);
		allMapData[index] = mapData;
		index++;
		
		// trident
		mapData = new MapData(new int[][] {
		{0, 1, 1, 1, 0},
		{0, 2, 5, 2, 0},
		{0, 0, 3, 0, 0},
		{0, 0, 3, 0, 0},
		{0, 0, 1, 0, 0}}, index, 30);
		allMapData[index] = mapData;
		index++;
		
		// T
		mapData = new MapData(new int[][] {
		{2, 3, 3, 3, 2}, 
		{3, 0, 0, 0, 3}, 
		{2, 2, 0, 2, 2}, 
		{0, 3, 0, 3, 0}, 
		{0, 2, 3, 2, 0}}, index, 30);
		allMapData[index] = mapData;
		index++;
		
		// crosshair
		mapData = new MapData(new int[][] {
		{2, 1, 0, 1, 2},
		{1, 0, 1, 0, 1},
		{0, 1, 5, 1, 0},
		{1, 0, 1, 0, 1},
		{2, 1, 0, 1, 2}}, index, 30);
		allMapData[index] = mapData;
		index++;
		
		// umut sarikaya
		mapData = new MapData(new int[][] {
		{1, 3, 3, 3, 1},
		{2, 2, 0, 2, 2},
		{2, 2, 0, 2, 2},
		{2, 3, 3, 3, 1},
		{2, 3, 3, 3, 1}}, index, 30);
		allMapData[index] = mapData;
		index++;
		
		// celtic
		mapData = new MapData(new int[][] {
		{0, 1, 4, 4, 2}, 
		{1, 0, 2, 5, 4}, 
		{4, 2, 0, 2, 4}, 
		{4, 5, 2, 0, 1}, 
		{2, 4, 4, 1, 0}}, index, 30);
		allMapData[index] = mapData;
		index++;
		
		// ibo
		mapData = new MapData(new int[][] {
		{1, 3, 3, 3, 1},
		{1, 2, 2, 2, 2},
		{3, 4, 4, 3, 3},
		{1, 2, 2, 2, 2},
		{1, 3, 3, 3, 1}}, index, 30);
		allMapData[index] = mapData;
		index++;
		
		// ship
		mapData = new MapData(new int[][] {
		{0, 2, 3, 4, 1},
		{0, 3, 0, 3, 0},
		{0, 2, 3, 5, 1},
		{1, 0, 0, 3, 1},
		{2, 3, 3, 4, 2}}, index, 30);
		allMapData[index] = mapData;
		index++;
		
		// rabbit
		mapData = new MapData(new int[][] {
		{0, 0, 0, 1, 1},
		{2, 3, 3, 4, 4},
		{4, 2, 0, 2, 2},
		{2, 4, 1, 2, 1},
		{1, 3, 3, 3, 1}}, index, 30);
		allMapData[index] = mapData;
		index++;
		
		// mouse
		mapData = new MapData(new int[][] {
		{2, 2, 0, 2, 2},
		{2, 5, 4, 5, 2},
		{1, 5, 4, 5, 1},
		{0, 2, 4, 2, 0},
		{0, 0, 1, 0, 0}}, index, 30);
		allMapData[index] = mapData;
		index++;
		
		//snail
		mapData = new MapData(new int[][] {
		{0, 0, 0, 0, 0},
		{1, 1, 0, 0, 0},
		{2, 4, 2, 3, 2},
		{0, 3, 4, 2, 3},
		{1, 4, 4, 4, 2}}, index, 30);
		allMapData[index] = mapData;
		index++;
		
		// deer
		mapData = new MapData(new int[][] {
		{0, 0, 1, 2, 0}, 
		{0, 0, 1, 5, 2}, 
		{2, 3, 3, 5, 2}, 
		{4, 3, 3, 4, 0}, 
		{1, 0, 0, 1, 0}}, index, 30);
		allMapData[index] = mapData;
		index++;
		
		// classroom
		mapData = new MapData(new int[][] {
		{0, 0, 0, 0, 1},
		{1, 0, 1, 0, 3},
		{4, 1, 4, 1, 3},
		{4, 2, 4, 2, 1},
		{1, 1, 1, 1, 0}}, index, 30);
		allMapData[index] = mapData;
		index++;
		
		// dog and ball
		mapData = new MapData(new int[][] {
		{0, 0, 1, 1, 0}, 
		{0, 0, 4, 4, 2}, 
		{2, 1, 4, 3, 2}, 
		{4, 3, 4, 2, 2}, 
		{1, 0, 1, 2, 2}}, index, 30);
		allMapData[index] = mapData;
		index++;
		
		// random
		mapData = new MapData(new int[][] {
		{2, 2, 2, 2, 0},
		{3, 3, 2, 2, 1},
		{3, 4, 3, 3, 4},
		{3, 3, 2, 2, 1},
		{1, 1, 1, 1, 0}}, index, 30);
		allMapData[index] = mapData;
		index++;
		
		// random
		mapData = new MapData(new int[][] {
		{0, 1, 4, 1, 1},
		{1, 3, 2, 1, 3},
		{2, 4, 2, 1, 3},
		{4, 2, 3, 2, 4},
		{2, 3, 2, 2, 2}}, index, 30);
		allMapData[index] = mapData;
		index++;
		
		// random
		mapData = new MapData(new int[][] {
		{0, 0, 1, 2, 1},
		{0, 2, 5, 4, 1},
		{2, 4, 2, 4, 2},
		{2, 3, 4, 5, 2},
		{0, 0, 1, 1, 1}}, index, 30);
		allMapData[index] = mapData;
		index++;
		
		// random
		mapData = new MapData(new int[][] {
		{0, 2, 2, 1, 1},
		{0, 2, 4, 3, 2},
		{1, 1, 1, 1, 2},
		{1, 1, 1, 0, 0},
		{1, 1, 1, 3, 1}}, index, 30);
		allMapData[index] = mapData;
		index++;
		
		return allMapData;
	}
}
