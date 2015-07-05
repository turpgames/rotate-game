package com.turpgames.rotategame.controller;

import java.util.List;

import com.turpgames.rotategame.objects.Level;
import com.turpgames.rotategame.objects.LevelTimer;
import com.turpgames.rotategame.objects.MapData;

public class NormalPlayController extends MasterPlayController {
	private List<MapData> allMapData;
	
	public NormalPlayController(List<MapData> allMaps, int startIndex) {
		super();
		this.allMapData = allMaps;
		this.mapIndex = startIndex;
		this.mapData = allMapData.get(mapIndex).mapData;
		this.mapTime = allMapData.get(mapIndex).mapTime;
		this.levelTimer = new LevelTimer(mapTime, this);
		updateLevelTitle();
	}
	
	private void updateLevelTitle() {
		this.levelTitle.setText("LEVEL\n" + (mapIndex+1) + "/" + allMapData.size());
	}

	@Override
	protected void onNext() {
		mapIndex++;
		if (mapIndex >= allMapData.size()) {
			// switch to finished game screen
		}
		else {
			resetState();
			mapData = allMapData.get(mapIndex).mapData;
			mapTime = allMapData.get(mapIndex).mapTime;
			levelTimer.reset();
			levelTimer.start(mapTime);
			level = new Level(this, mapData);
		}
	}
	
}
