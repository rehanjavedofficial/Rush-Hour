package game;

/** 
 * @version 7/3/2017
 */
public class Cell {

	// Attributes
	private int x;
	private int y;
	private boolean isHead;
	
	/**
	 * Constructor to initialize the 
	 * variables.
	 * 
	 * @param x
	 * @param y
	 */
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return x
	 */
	public int getX() {
		
		return x;
	
	}

	/**
	 * Set new value for x
	 *
	 * @param x
	 */
	public void setX(int x) {
		
		this.x = x;
	
	}

	/**
	 * @return y
	 */
	public int getY() {
		
		return y;
	
	}

	/**
	 * Set new value for y
	 *
	 * @param y
	 */
	public void setY(int y) {
	
		this.y = y;
	
	}

	/**
	 * @return isHead
	 */
	public boolean isHead() {
	
		return isHead;
	
	}

	/**
	 * Set new value for isHead
	 *
	 * @param isHead
	 */
	public void setHead(boolean isHead) {
	
		this.isHead = isHead;
	
	}
	
}
