package com.turpgames.rotategame.objects;

import java.util.ArrayList;
import java.util.List;

import com.turpgames.framework.v0.IDrawable;

public class DrawableContainer implements IDrawable {

	private List<IDrawable> objList;
	
	public DrawableContainer() {
		objList = new ArrayList<IDrawable>();
	}
	
	public void add(IDrawable obj) {
		objList.add(obj);
	}
	
	public void remove(IDrawable obj) {
		objList.remove(obj);
	}
	
	@Override
	public void draw() {
		for (IDrawable obj : objList) {
			obj.draw();
		}
	}
}
