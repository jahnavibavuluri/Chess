package pieces;

import java.awt.Point;
import java.util.*;
import board.Board;

public class Bishop extends Piece {
	
	public final String name;

	public Bishop(String color, int x, int y) {
		super(color, x, y);
		if (color.equals("white"))
			name = "wB";
		else 
			name = "bB";
	}
	
	public void basicMovement (Board b, ArrayList<Point> getMoves) {
		//adds diagonal moves to the list
		
	}
	
	public ArrayList<Point> getMoves(Board b) {
		//calls the above methods and finally checks if making this move will put its own king in check
		ArrayList<Point> moves = new ArrayList<Point>();
		return moves;
	}

}
