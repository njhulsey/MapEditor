package com.nickhulsey.main;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.nickhulsey.input.FileHandler;
import com.nickhulsey.input.InputHandler;

public class Window extends JFrame implements Runnable{
	
	public static final int WIDTH = 685;
	public static final int HEIGHT = 660;
	
	public static InputHandler input;
	
	private Thread thread;
	private JPanel container;
	
	public MapHandler map;
	public GuiHandler gui;
	public FileHandler filer;
	
	public void init(){
		thread = new Thread(this);
		input = new InputHandler();

		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		
		gui = new GuiHandler(this);
		map = new MapHandler(gui,this);
		gui.map = map;
		
		filer = new FileHandler(map);
		
		container.add(map);
		container.add(gui);
		
		this.add(container);
		
		map.init();
		thread.start();
	}
	
	public void tick(){
		map.tick();
		
	}
	
	public void draw(){
		map.repaint();
	}
	
	
	public void run(){
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(true){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
				draw();
			}
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				this.setTitle("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	
	public static void main(String[] args){
		Window window = new Window();
		window.setSize(WIDTH,HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.init();
		window.setVisible(true);
	}
	
}
