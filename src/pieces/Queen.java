package pieces;

import java.awt.Point;
import java.util.*;
import board.Board;

public class Queen extends Piece {

	public final String name;
	
	public Queen(String color, int x, int y) {
		super(color, x, y);
		if (color.equals("white"))
			name = "wQ";
		else 
			name = "bQ";
	}
	
	public void lineMovement (Board b, ArrayList<Point> getMoves) {
		//adds vertical and horizontal moves to the list
		
	}
	
	public void diagMovement (Board b, ArrayList<Point> getMoves) {
		//adds diagonal moves to the list
		
	}
	
	public ArrayList<Point> getMoves(Board b) {
		//calls the above methods and finally checks if making this move will put its own king in check
		ArrayList<Point> moves = new ArrayList<Point>();
		return moves;
	}

}
