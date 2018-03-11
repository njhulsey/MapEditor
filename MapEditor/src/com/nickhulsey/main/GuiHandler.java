package com.nickhulsey.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nickhulsey.entity.Tile;

public class GuiHandler extends JPanel implements ActionListener{
	
	public Window window;
	public MapHandler map;
	
	private JPanel topPanel;
	private JTextField wInput, hInput;
	private JButton resize;
	
	public JPanel tilePanel;
	private JLabel tileIndex;
	private JTextField mapXInput, mapYInput;
	private JButton done;
	private JCheckBox checkCollision;
	
	public JPanel entityPanel;
	
	private JPanel southPanel;
	private JButton save;
	private JButton load;
	
	private JFileChooser fileChooser;

	public GuiHandler(Window window){
		this.window = window;
		this.setMaximumSize(new Dimension( (int)(Window.WIDTH/3),Window.HEIGHT));
		this.setMinimumSize(new Dimension( (int)(Window.WIDTH/3),Window.HEIGHT));
		this.setPreferredSize(new Dimension( (int)(Window.WIDTH/3),Window.HEIGHT));
		this.setLayout(new BorderLayout());

		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		topPanel = new JPanel();
		wInput = new JTextField("width",3);
		hInput = new JTextField("height",3);
		resize = new JButton("resize");
		resize.addActionListener(this);
		
		topPanel.add(wInput);
		topPanel.add(hInput);
		topPanel.add(resize);
		
		tilePanel = new JPanel(new FlowLayout());
		tileIndex = new JLabel("Sprite index:");
		mapXInput = new JTextField("",3);
		mapYInput = new JTextField("",3);
		checkCollision = new JCheckBox("Collision");
		done = new JButton("Done");
		done.addActionListener(this);
		
		tilePanel.add(tileIndex);
		tilePanel.add(mapXInput);
		tilePanel.add(mapYInput);
		tilePanel.add(checkCollision);
		tilePanel.add(done);
		
		southPanel = new JPanel();
		save = new JButton("Save");
		save.addActionListener(this);
		load = new JButton("Load");
		load.addActionListener(this);
		
		southPanel.add(save);
		southPanel.add(load);
		
		this.add(topPanel,BorderLayout.NORTH);
		this.add(southPanel, BorderLayout.SOUTH);
		
	}
	
	public void selectTile(){
		mapXInput.setText(""+map.selector.selectedTiles.get(0).mapX);
		mapYInput.setText(""+map.selector.selectedTiles.get(0).mapY);
		checkCollision.setSelected(map.selector.selectedTiles.get(0).collision);
		
		this.add(tilePanel,BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}
	

	public void actionPerformed(ActionEvent e) {
		map.requestFocus();
		if(e.getSource() == resize){
			map.newBoard(Integer.parseInt(wInput.getText().trim()), Integer.parseInt(hInput.getText().trim()));
		}
		if(e.getSource() == done){

			for(Tile t: map.selector.selectedTiles){
				int x = Integer.parseInt(mapXInput.getText().trim());
				int y = Integer.parseInt(mapYInput.getText().trim());
				boolean collision = checkCollision.isSelected();
				t.updateValues(x,y,collision);
			}
			
			map.selector.selectedTiles.clear();
			
			this.remove(tilePanel);

			this.revalidate();
			this.repaint();
		}
		
		if(e.getSource() == save){
			fileChooser.showSaveDialog(window);
			window.filer.saveMap(map.tiles, fileChooser.getSelectedFile());
		}
	
		if(e.getSource() == load){
			fileChooser.showOpenDialog(window);
			window.filer.loadMap(fileChooser.getSelectedFile());
		}
	}

}