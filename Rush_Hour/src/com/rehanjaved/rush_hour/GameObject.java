package game;

import java.awt.Color;

/** 
 * @version 7/3/2017
 */
public class GameObject {

	// Attributes
	private Cell[] cellLocations;
	private Color color;
	private Orientation orientation;
	
	/**
	 * @param cellLocations
	 * @param color
	 * @param orientation
	 */
	public GameObject(Cell[] cellLocations, Color color, Orientation orientation) {
	
		this.cellLocations = cellLocations;
		this.color = color;
		this.orientation = orientation;
	
	}

	/**
	 * @return cellLocations
	 */
	public Cell[] getCellLocations() {
	
		return cellLocations;
	
	}

	/**
	 * Set new value for cellLocations
	 *
	 * @param cellLocations
	 */
	public void setCellLocations(Cell[] cellLocations) {
	
		this.cellLocations = cellLocations;
	
	}
	
	/**
	 * Set cell at specific location.
	 * 
	 * @param cell
	 * @param index
	 */
	public void setCellAtIndex(Cell cell, int index){
		
		this.cellLocations[index] = cell;
		
	}

	/**
	 * @return color
	 */
	public Color getColor() {
		
		return color;
	
	}

	/**
	 * Set new value for color
	 *
	 * @param color
	 */
	public void setColor(Color color) {
		
		this.color = color;
	
	}
	
	/**
	 * @return size of game object.
	 */
	public int size(){
		
		return this.cellLocations.length;
		
	}

	/**
	 * @return orientation
	 */
	public Orientation getOrientation() {
	
		return orientation;
	
	}

	/**
	 * Set new value for orientation
	 *
	 * @param orientation
	 */
	public void setOrientation(Orientation orientation) {
	
		this.orientation = orientation;
	
	}
	
	
	
}
