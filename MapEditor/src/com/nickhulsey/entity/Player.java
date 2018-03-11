package com.nickhulsey.entity;
import java.awt.Color;
import java.awt.Graphics;

import com.nickhulsey.main.MapHandler;
import com.nickhulsey.main.Window;

public class Player extends Entity{

	public boolean holding = false;
	
	public Player(int x, int y, MapHandler map){
		super(x,y,map);
		texture = map.spriteHandler.getSprite(0, 9);
	}
	
	public void tick(){
		
		if(pressed()){
			holding = true;
			map.selector.selectedEntity = this;
		}
		
		if(holding){
			x = Window.input.MouseX + map.camera.x - (int)(texture.getWidth() * map.SCALE * selfScale) / 2;
			y = Window.input.MouseY + map.camera.y - (int)(texture.getHeight() * map.SCALE * selfScale) / 2;
		}
		if(holding && !Window.input.pressed){
			holding = false;
			map.selector.selectedEntity = null;
			snapToGrid();
		}
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		if(map.selector.selectedEntity == this){
			g.setColor(Color.red);
			g.drawRect(x, y, (int)(texture.getWidth() * map.SCALE * selfScale), (int)(texture.getHeight() * map.SCALE * selfScale));
		}
		
		
	}
	
}
