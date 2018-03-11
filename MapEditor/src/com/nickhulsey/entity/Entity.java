package com.nickhulsey.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.nickhulsey.main.MapHandler;
import com.nickhulsey.main.Window;

public abstract class Entity{

	public int x, y;
	public int mapX, mapY;
	public MapHandler map;
	public BufferedImage texture;
 
	public double selfScale = 1.0;
	
	public Entity(int x, int y, MapHandler map) {
		this.x = x;
		this.y = y;
		this.map = map;
		
	}
	
	public Entity(String[] data, MapHandler map){
		this.map = map;
		x = Integer.parseInt(data[1]) * (int)(map.SCALE * map.TILE_SIZE);
		y = Integer.parseInt(data[2]) * (int)(map.SCALE * map.TILE_SIZE);
		mapX = Integer.parseInt(data[3]);
		mapY = Integer.parseInt(data[4]);
		
		texture = map.spriteHandler.getSprite(mapX, mapY);
	}
	
	public void snapToGrid(){
		x =  (int)((Math.round((x / (map.SCALE * map.TILE_SIZE)))) * map.SCALE * map.TILE_SIZE);
		y = (int)((Math.round((y / (map.SCALE * map.TILE_SIZE)))) * map.SCALE * map.TILE_SIZE);
	}

	public boolean pressed(){
		int mouseX = Window.input.MouseX + map.camera.x;
		int mouseY = Window.input.MouseY + map.camera.y;
		
		return 
				Window.input.pressed && map.selector.selectedTiles.size() == 0
				&& mouseX > x && mouseX < x + 20 + (int)(texture.getWidth() * map.SCALE * selfScale) / 2
				&& mouseY > y && mouseY < y + 20 + (int)(texture.getHeight() * map.SCALE * selfScale) / 2;
	}
	
	
	public abstract void tick();
	public void paint(Graphics g){
		g.drawImage(texture, x, y, (int)(map.TILE_SIZE * map.SCALE * selfScale), (int)(map.TILE_SIZE * map.SCALE * selfScale), null);
	}
	
	
	
}
