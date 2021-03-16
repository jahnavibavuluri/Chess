package pieces;

import java.awt.Point;
import java.util.*;
import board.Board;

public class Pawn extends Piece{
	
	public boolean firstMove;
	//public final String name;

	public Pawn(String color, int x, int y, boolean firstMove) {
		//initializes pawn as a piece with boolean firstMove
		super(color,x,y);
		this.firstMove = firstMove;
		if (color.equals("white"))
			name = "wp ";
		else 
			name = "bp ";
	}
	
	public String getName() {
		return name;
	}
	
	public void basicMovement(Board b, ArrayList<Point> getMoves) {
		//checks for the moves where the pawn can move up by one or two steps
		return;
		
	}
	
	public void empassment(Board b, ArrayList<Point> getMoves) {
		//checks if the pawn has an opportunity for empassment 
		
	}
	
	public void capture (Board b, ArrayList<Point> getMoves) {
		//checks if the pawn can capture a piece 
		
	}
	
	public ArrayList<Point> getMoves(Board b) {
		//calls the above methods and finally checks if making this move will put its own king in check
		ArrayList<Point> moves = new ArrayList<Point>();
		return moves;
	}

}
