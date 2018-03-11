package com.nickhulsey.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.nickhulsey.main.MapHandler;
import com.nickhulsey.main.Window;

public class Tile {
	
	public int x,y;
	public int mapX,mapY;
	public boolean collision;
	
	private BufferedImage texture;
	private MapHandler map;
	
	public Tile(int x, int y, MapHandler map){
		Random r = new Random();
		this.x = x;
		this.y = y;
		this.mapX = r.nextInt(3);
		this.mapY = r.nextInt(6);
		this.map = map;
		collision = false;
		
		texture = map.spriteHandler.getSprite(mapX, mapY);
	}
	
	public Tile(String[] data, MapHandler map){
		this.map = map;
		x = Integer.parseInt(data[1]) * (int)(map.SCALE * map.TILE_SIZE);
		y = Integer.parseInt(data[2]) * (int)(map.SCALE * map.TILE_SIZE);
		mapX = Integer.parseInt(data[3]);
		mapY = Integer.parseInt(data[4]);
		collision = Boolean.parseBoolean(data[5]);
		texture = map.spriteHandler.getSprite(mapX, mapY);
	}
	
	
	public void tick(){
		
		boolean inside = map.selector.box.intersects(x,y,map.TILE_SIZE * map.SCALE, map.TILE_SIZE * map.SCALE);
		if(inside && !map.selector.selectedTiles.contains(this)){
			map.selector.addTile(this);
		}else if (!inside && map.selector.selectedTiles.contains(this) && map.selector.holding){
			map.selector.selectedTiles.remove(this);
		}
	}
	
	public void updateValues(int mapX, int mapY,boolean collision){
		this.mapX = mapX;
		this.mapY = mapY;
		this.collision = collision;
		texture = map.spriteHandler.getSprite(mapX, mapY);
		
	}
	
	public void paint(Graphics g){
		
		if(texture != null)
			g.drawImage(texture, x, y, (int)(MapHandler.TILE_SIZE * MapHandler.SCALE), (int)(MapHandler.TILE_SIZE * MapHandler.SCALE), null);
		
		if(map.selector.selectedTiles.contains(this)){
			g.setColor(Color.red);
			g.drawRect(x, y, (int)(MapHandler.TILE_SIZE * MapHandler.SCALE) - 1,(int)(MapHandler.TILE_SIZE * MapHandler.SCALE) - 1);
		}else if(collision){
			g.setColor(Color.blue);
			g.drawRect(x, y,(int)(MapHandler.TILE_SIZE * MapHandler.SCALE) - 1, (int)(MapHandler.TILE_SIZE * MapHandler.SCALE) - 1);
		}
	
		
	}

}
