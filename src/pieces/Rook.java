package pieces;

import java.awt.Point;
import java.util.ArrayList;
import board.Board;

public class Rook extends Piece{

	public boolean castling;
	public final String name;
	
	public Rook(String color, int x, int y, boolean castling) {
		super(color,x,y);
		this.castling = castling; 
		//though it may seem that castling will always be set to false when a rook is first created, this is needed for promotion
		if (color.equals("white"))
			name = "wR";
		else 
			name = "bR";
	}
	
	public void basicMovement(Board b, ArrayList<Point> getMoves) {
		//checks the basic horizontal and vertical movements of the rook
		
	}
	
	public ArrayList<Point> getMoves(Board b) {
		//calls the above methods and finally checks if making this move will put its own king in check
		ArrayList<Point> moves = new ArrayList<Point>();
		return moves;
	}

}
