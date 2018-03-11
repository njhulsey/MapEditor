package com.nickhulsey.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.nickhulsey.main.MapHandler;

public class SpriteHandler {

	private BufferedImage sheet;

	public SpriteHandler() {
		try {
			sheet = ImageIO.read(getClass().getResource("/Sprites/Terrain.png"));
		} catch (IOException e) {e.printStackTrace();}
		
	}
	
	public BufferedImage getSprite(int x, int y){
		return sheet.getSubimage(x * MapHandler.TILE_SIZE, y * MapHandler.TILE_SIZE, MapHandler.TILE_SIZE, MapHandler.TILE_SIZE);
		
	}

}
