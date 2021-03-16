//creates the abstract Piece class that the other pieces will extend
package pieces;

import java.awt.Point;
import java.util.*;
import board.Board;

public abstract class Piece {
	
	public String color;
	public Point location;
	public String name;
	
	//initializes the piece with a color (black or white) and a point location
	public Piece (String color, int x, int y) {
		this.color = color;
		this.location = new Point(x,y);
		name = "";
	}
	 
	public Point getPoint(Piece p) {
		return location;
	}
	
	public String color(Piece p) {
		return color;
	}
	
	public abstract String getName();
	
	public abstract ArrayList<Point> getMoves(Board b);
	
}
