package com.turpgames.rotategame.objects;

import com.turpgames.rotategame.controller.MasterPlayController;

public class PauseTimer {
	private static final float PAUSETIME = 1;
	
	private MasterPlayController parent;
	private float currTime;
	private boolean isStopped;
	
	public PauseTimer(MasterPlayController parent) {
		this.parent = parent;
		this.isStopped = true;
		this.currTime = 0;
	}
	
	public void start() {
		currTime = PAUSETIME;
		isStopped = false;
	}
	
	public void update(float elapsed) {
		if (!isStopped) {
			currTime -= elapsed;
			if (currTime < 0) {
				currTime = 0;
				parent.pauseEnded();
			}
		}
	}

	public void stop() {
		isStopped = true;
	}
}
