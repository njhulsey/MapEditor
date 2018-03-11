package com.nickhulsey.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.nickhulsey.entity.Player;
import com.nickhulsey.entity.Tile;
import com.nickhulsey.main.MapHandler;

public class FileHandler {

	private MapHandler map;
	
	public FileHandler(MapHandler map){
		this.map = map;
	}
	
	
 /**
  * how this works?
  * we save the data first by the object type: W = world T = tile
  * then after that is ,datavalue,datavalue,datavalue for as many as we need
  * then \n
  * then we save it
  * 
  * loading is the same way
  * take in the line and split it up by ,'s
  * then we read the first value. and create an object for that specific object
  * then load it the rest of the data
  * 
  */
	
	public void saveMap(ArrayList<Tile> data, File file) {
		
		try {
			FileWriter writer = new FileWriter(file);

			writer.write("W,");
			writer.write(String.valueOf(map.boardWidth));
			writer.write("," + String.valueOf(map.boardHeight));
			writer.write("\n");
			
			writer.write("P,");
			writer.write(String.valueOf((int)((Math.round(map.player.x / (map.SCALE * map.TILE_SIZE))))));
			writer.write("," + String.valueOf((int)(Math.round((map.player.y / (map.SCALE * map.TILE_SIZE))))));
			writer.write("\n");
			
			//divide x and y by tilesize and scale to find its x and y index
			for(Tile t: data){
					writer.write("T,");
					writer.write(String.valueOf( t.x / (int)(map.TILE_SIZE * map.SCALE) ));
					writer.write("," + String.valueOf(t.y / (int)(map.TILE_SIZE * map.SCALE) ));
					writer.write("," + String.valueOf(t.mapX));
					writer.write("," + String.valueOf(t.mapY));
					writer.write("," + String.valueOf(t.collision));
					writer.write("\n");
			}
			writer.close();
		} catch (IOException e) {e.printStackTrace();}

	}
	
	
	public void loadMap(File file){
		try {
			Scanner scan = new Scanner(file);
			ArrayList<Tile> tiles = new ArrayList<Tile>();
			
			while (scan.hasNext()) {
				String line = scan.next();
				String[] data = line.split(",");
				
				if(data[0].equals("W")){
					map.loadBoard(Integer.parseInt(data[2]),Integer.parseInt(data[1]));
				}
				else if(data[0].equals("T")){
					tiles.add(new Tile(data,map));
				}else if(data[0].equals("P")){
					map.player = new Player((int)(Integer.parseInt(data[1]) * map.SCALE * map.TILE_SIZE), (int)(Integer.parseInt(data[2]) * map.SCALE * map.TILE_SIZE), map);
					
				}
				
			}
			map.tiles.clear();
			map.tiles.addAll(tiles);
		} catch (FileNotFoundException e) {e.printStackTrace();}
	}
	
}
