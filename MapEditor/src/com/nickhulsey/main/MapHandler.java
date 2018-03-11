package com.nickhulsey.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import com.nickhulsey.entity.Player;
import com.nickhulsey.entity.Tile;
import com.nickhulsey.graphics.Camera;
import com.nickhulsey.graphics.SpriteHandler;
import com.nickhulsey.input.Selector;

public class MapHandler extends JPanel{
	
	public static final int TILE_SIZE = 256;
	public static final double SCALE = .40;
	public int boardWidth,boardHeight;

	public GuiHandler gui;
	public Window window;
	public SpriteHandler spriteHandler;
	
	public ArrayList<Tile> tiles;
	public Camera camera;
	private BufferedImage background;
	public Selector selector;
	public Player player;
	
	public MapHandler(GuiHandler gui, Window window){
		this.gui = gui;
		this.window = window;
		spriteHandler = new SpriteHandler();
	}
	public void init(){
		this.addKeyListener(window.input);
		this.addMouseListener(window.input);
		this.addMouseMotionListener(window.input);
		this.addFocusListener(window.input);
		
		tiles = new ArrayList<Tile>();
		camera = new Camera(this);
		selector = new Selector(this);
		
		player = new Player(0,0,this);
	
	}
	
	public void newBoard(int w, int h){
		boardWidth = w;
		boardHeight = h;
		camera.x = 0;
		camera.y = 0;
		tiles.clear();
		
		for(int y = 0; y < h; y ++){
			for(int x = 0; x < w; x ++){
				tiles.add(new Tile(x * (int)(TILE_SIZE * SCALE), y * (int)(TILE_SIZE * SCALE), this));
			}
		}
		
		//create the background stars
		background = new BufferedImage(getWidth() * 2,getHeight() * 2,BufferedImage.TYPE_INT_RGB);
		Graphics g = background.getGraphics();
		g.setColor(Color.white);
		int size = 2;
		Random r = new Random();
		for(int i = 0; i < 200; i++){
			g.fillRect(r.nextInt( background.getWidth()), r.nextInt(background.getHeight()), size, size);
		}
	}
	
	public void loadBoard(int w, int h){
		boardWidth = w;
		boardHeight = h;
		camera.x = 0;
		camera.y = 0;
		
		//create the background stars
		background = new BufferedImage(getWidth() * 2,getHeight() * 2,BufferedImage.TYPE_INT_RGB);
		Graphics g = background.getGraphics();
		g.setColor(Color.white);
		int size = 2;
		Random r = new Random();
		for(int i = 0; i < 200; i++){
			g.fillRect(r.nextInt( background.getWidth()), r.nextInt(background.getHeight()), size, size);
		}
	}
	
	
	public void tick(){
		camera.tick();
		player.tick();
		for(int i = 0; i < tiles.size(); i++)
			tiles.get(i).tick();
		
		selector.tick();
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(background, 0, 0,null);
		g.translate(-camera.x, -camera.y);
		
		for(int i = 0; i < tiles.size(); i++)
			tiles.get(i).paint(g);
		
		selector.paint(g);
		player.paint(g);
		
	}
	
	
}
