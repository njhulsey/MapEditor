package com.nickhulsey.graphics;

import java.awt.event.KeyEvent;

import com.nickhulsey.main.MapHandler;
import com.nickhulsey.main.Window;

public class Camera {

	private MapHandler map; 
	
	public int x, y = 0;
	
	private int xMax;
	private int xMin = 0;
	
	private int yMax; 
	private int yMin = 0;
	
	private int speed = 5;
	
	public Camera(MapHandler map){
		this.map = map;
	}	
	
	public void tick(){
		xMax = map.boardWidth * (int)(MapHandler.TILE_SIZE * MapHandler.SCALE) - map.getWidth() / 2;
		yMax = map.boardHeight * (int)(MapHandler.TILE_SIZE * MapHandler.SCALE) - map.getHeight() / 2;
		
		xMin = -map.getWidth() / 2;
		yMin = -map.getHeight() / 2;
		
		if(Window.input.key[KeyEvent.VK_W] || Window.input.key[KeyEvent.VK_UP] )
			y -= speed;
		if(Window.input.key[KeyEvent.VK_A] || Window.input.key[KeyEvent.VK_LEFT] )
			x -= speed;
		if(Window.input.key[KeyEvent.VK_S] || Window.input.key[KeyEvent.VK_DOWN] )
			y += speed;
		if(Window.input.key[KeyEvent.VK_D] || Window.input.key[KeyEvent.VK_RIGHT] )
			x += speed;
		
		if(x > xMax)x = xMax;
		if(x < xMin)x = xMin;
		if(y > yMax)y = yMax;
		if(y < yMin)y = yMin;
		
	}
	
	
}