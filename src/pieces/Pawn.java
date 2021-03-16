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
	//boolean firstMove = true -> 
	public void basicMovement(Board b, ArrayList<Point> getMoves) {
		//checks for the moves where the pawn can move up by one or two steps
		int x = location.x;
		int y = location.y;
		
		if(this.color.equals("white") && (x == 6)) {
			Point p = new Point(5,y);
			Point p2 = new Point(4,y);
			if (b.getPieceAt(p) == null){
				getMoves.add(p);
			}
			if (b.getPieceAt(p) == null && b.getPieceAt(p2) == null){
				getMoves.add(p2);
			}
		}else if(this.color.equals("white") && !(x == 6)) {
			Point p3 = new Point(x-1,y);
			if (b.getPieceAt(p3) == null){
				getMoves.add(p3);
			}
		}
		
		if(this.color.equals("black") && (x == 1)) {
			Point p = new Point(2,y);
			Point p2 = new Point(3,y);
			if (b.getPieceAt(p) == null){
				getMoves.add(p);
			}
			if (b.getPieceAt(p) == null && b.getPieceAt(p2) == null){
				getMoves.add(p2);
			}
		}else if(this.color.equals("black") && !(x == 1)) {
			Point p3 = new Point(x+1,y);
			if (b.getPieceAt(p3) == null){
				getMoves.add(p3);
			}
		}
		
		/*
		 for(int i=x-1; i>=0; i--){
			Point p = new Point(i,y);
			if (b.getPieceAt(p) == null){
				getMoves.add(p);
			} else if (!(b.getPieceAt(p).color.equals(this.color))) {
				getMoves.add(p);
				break;
			} else {
				break;
			}
		}
		 */
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
