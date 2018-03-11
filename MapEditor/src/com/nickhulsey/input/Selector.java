package com.nickhulsey.input;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.nickhulsey.entity.Entity;
import com.nickhulsey.entity.Tile;
import com.nickhulsey.main.MapHandler;
import com.nickhulsey.main.Window;

public class Selector {

	public Rectangle box;
	private MapHandler map;
	private InputHandler input = Window.input;
	
	public Entity selectedEntity;
	public ArrayList<Tile> selectedTiles;
	
	public boolean holding = false;
	
	public Selector(MapHandler map){
		this.map = map;
		box = new Rectangle();
		selectedTiles = new ArrayList<Tile>();
	}
	
	public void addTile(Tile t){
		selectedTiles.add(t);

	}
	
	public void tick(){
		if(input.pressed && !holding && selectedEntity == null){
			selectedTiles.clear();
			box.x = map.camera.x + input.MouseX;
			box.y = map.camera.y + input.MouseY;
			holding = true;
		}else if(input.pressed && holding){
			box.width = (map.camera.x + input.MouseX) - box.x;
			box.height = (map.camera.y + input.MouseY) - box.y;
		}else{
			box.x = map.camera.x - 5;
			box.y = map.camera.y - 5;
			box.width = 0;
			box.height = 0;
			holding = false;
		}
		
		if(selectedTiles.size() != 0 && map.gui.tilePanel.getParent() != map.gui)
			map.gui.selectTile();
	
	}
	
	public void paint(Graphics g){
		g.setColor(Color.yellow);
		g.drawRect(box.x,box.y,box.width,box.height);
	}
	
	
}