package pieces;

import java.awt.Point;
import java.util.*;
import board.Board;

public class Knight extends Piece{

	public final String name;
	
	public Knight(String color, int x, int y) {
		super(color, x, y);
		if (color.equals("white"))
			name = "wN";
		else 
			name = "bN";
	}
	
	public void basicMovement (Board b, ArrayList<Point> getMoves) {
		//adds knight moves to the list
		
	}
	
	public ArrayList<Point> getMoves(Board b) {
		//calls the above methods and finally checks if making this move will put its own king in check
		ArrayList<Point> moves = new ArrayList<Point>();
		return moves;
	}

}
