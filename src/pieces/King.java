package pieces;

import java.awt.Point;
import java.util.*;
import board.Board;

public class King extends Piece{

	public final String name;
	
	public King(String color, int x, int y) {
		super(color, x, y);
		if (color.equals("white"))
			name = "wK";
		else 
			name = "bK";
	}
	
	public void basicMovement (Board b, ArrayList<Point> getMoves) {
		//checks if the king can move to any its surrounding squares
		
	}
	
	public ArrayList<Point> getMoves(Board b) {
		//calls the above methods and finally checks if making this move will put its own king in check
		ArrayList<Point> moves = new ArrayList<Point>();
		return moves;
	}

}
