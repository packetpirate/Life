package com.life.cell;

import java.awt.geom.Rectangle2D;

public class Cell {
	private boolean alive;
	public boolean isAlive() { return this.alive; }
	public void setAlive(boolean alive) { this.alive = alive; }
	
	private int neighbors;
	public int getNeighbors() { return this.neighbors; }
	public void setNeighbors(int neighbors) { this.neighbors = neighbors; } 
	
	private Rectangle2D bounds;
	public Rectangle2D getBounds() { return this.bounds; }
	public void setBounds(Rectangle2D bounds) { this.bounds = bounds; }
	
	public Cell() {
		alive = false;
		neighbors = 0;
		bounds = new Rectangle2D.Double(0,0,10,10);
	}
	
	public Cell(Rectangle2D bounds) {
		alive = false;
		neighbors = 0;
		this.bounds = bounds;
	}
}
