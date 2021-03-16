//creates the abstract Piece class that the other pieces will extend
package chess;

import java.awt.Point;

public class Piece {
	public String color;
	
	public Point location;
	
	//initializes the piece with a color (black or white) and a point location
	public Piece (String color, int x, int y) {
		this.color = color;
		this.location = new Point(x,y);
	}
	 
}
