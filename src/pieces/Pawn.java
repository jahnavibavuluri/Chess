package pieces;

import java.awt.Point;
import java.util.*;
import board.Board;

public class Pawn extends Piece{
	
	public boolean enpassant;
	//public final String name;

	public Pawn(String color, int x, int y, boolean enpassant) {
		//initializes pawn as a piece with boolean firstMove
		super(color,x,y);
		if (color.equals("white"))
			name = "wp ";
		else 
			name = "bp ";
		this.enpassant = enpassant;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getEnpassant() {
		return enpassant;
	}
	
	public void setEnpassant(boolean b) {
		this.enpassant = b;
	}

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
		}else if(this.color.equals("white") && !(x == 6) && (x!=0)) {
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
		}else if(this.color.equals("black") && !(x == 1) && (x!=7)) {
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
	
	public void empassant(Board b, ArrayList<Point> getMoves) {
		//checks if the pawn has an opportunity for empassment 
		int row = this.location.x;
		int col = this.location.y;
		
		//check to the immediate right and left of this pawn
		//edge case when pawn is in the 0th column
//--------------------------------------------------white--------------------------------------------------
		if (row == 3) {
			//edge case when pawn in 0th col
			if (col == 0) {
				Piece p = b.getPieceAt(new Point(row, col+1));
				if (p!= null 
						&& p instanceof Pawn 
						&& !(p.color).equals(this.color) 
						&& ((Pawn) p).getEnpassant()) {
					getMoves.add(new Point(row-1, col+1));
				}
			//edge case when pawn is in 7th row
			} else if (col == 7) {
				Piece p = b.getPieceAt(new Point(row, col-1));
				if (p!= null 
						&& p instanceof Pawn 
						&& !(p.color).equals(this.color) 
						&& ((Pawn) p).getEnpassant()) {
					getMoves.add(new Point(row-1, col-1));
				}
			} else {
				Piece right = b.getPieceAt(new Point(row, col+1));
				Piece left = b.getPieceAt(new Point(row, col-1));
				if (right!= null 
						&& right instanceof Pawn 
						&& !(right.color).equals(this.color) 
						&& ((Pawn) right).getEnpassant()) {
					getMoves.add(new Point(row-1, col+1));
				}
				if (left!= null 
						&& left instanceof Pawn 
						&& !(left.color).equals(this.color) 
						&& ((Pawn) left).getEnpassant()) {
					getMoves.add(new Point(row-1, col-1));
				}
			}
		}
		
//---------------------------------------------------black---------------------------------------------------
		if (row == 4) {
			if (col == 0) {
				Piece p = b.getPieceAt(new Point(row, col+1));
				if (p!= null 
						&& p instanceof Pawn 
						&& !(p.color).equals(this.color) 
						&& ((Pawn) p).getEnpassant()) {
					getMoves.add(new Point(row+1, col+1));
				}
			//edge case when pawn is in 7th row
			} else if (col == 7) {
				Piece p = b.getPieceAt(new Point(row, col-1));
				if (p!= null 
						&& p instanceof Pawn 
						&& !(p.color).equals(this.color) 
						&& ((Pawn) p).getEnpassant()) {
					getMoves.add(new Point(row+1, col-1));
				}
			} else {
				Piece right = b.getPieceAt(new Point(row, col+1));
				Piece left = b.getPieceAt(new Point(row, col-1));
				if (right!= null 
						&& right instanceof Pawn 
						&& !(right.color).equals(this.color) 
						&& ((Pawn) right).getEnpassant()) {
					getMoves.add(new Point(row+1, col+1));
				}
				if (left!= null 
						&& left instanceof Pawn 
						&& !(left.color).equals(this.color) 
						&& ((Pawn) left).getEnpassant()) {
					getMoves.add(new Point(row+1, col-1));
				}
			}
		}
	}
	
	public void capture (Board b, ArrayList<Point> getMoves) {
		//checks if the pawn can capture a piece 
		int x = location.x;
		int y = location.y;
		//------------------------------------white capture--------------------------------------------------
		if(this.color.equals("white") && ((y != 0) && (y != 7))){
			Point left = new Point(x-1,y-1);
			Point right = new Point(x-1,y+1);
			if((b.getPieceAt(left) != null) && ((b.getPieceAt(left)).color.equals("black"))) {
				getMoves.add(left);
			}
			if((b.getPieceAt(right) != null) && ((b.getPieceAt(right)).color.equals("black"))) {
				getMoves.add(right);
			}
		}
		if(this.color.equals("white") && ((y == 0))) {
			Point right = new Point(x-1,y+1);
			if((b.getPieceAt(right) != null) && ((b.getPieceAt(right)).color.equals("black"))) {
				getMoves.add(right);
			}
		}
		if(this.color.equals("white") && ((y == 7))) {
			Point left = new Point(x-1,y-1);
			if((b.getPieceAt(left) != null) && ((b.getPieceAt(left)).color.equals("black"))) {
				getMoves.add(left);
			}
		}
//------------------------------------black capture--------------------------------------------------		
		if(this.color.equals("black") && ((y != 0) && (y != 7))){
			Point left = new Point(x+1,y-1);
			Point right = new Point(x+1,y+1);
			if((b.getPieceAt(left) != null) && ((b.getPieceAt(left)).color.equals("white"))) {
				getMoves.add(left);
			}
			if((b.getPieceAt(right) != null) && ((b.getPieceAt(right)).color.equals("white"))) {
				getMoves.add(right);
			}
		}
		if(this.color.equals("black") && ((y == 0))){
			Point right = new Point(x+1,y+1);
			if((b.getPieceAt(right) != null) && ((b.getPieceAt(right)).color.equals("white"))) {
				getMoves.add(right);
			}
		}
		if(this.color.equals("black") && ((y == 7))) {
			Point left = new Point(x+1,y-1);
			if((b.getPieceAt(left) != null) && ((b.getPieceAt(left)).color.equals("white"))) {
				getMoves.add(left);
			}
		}
	}
	
	public ArrayList<Point> getMoves(Board b, boolean check) {
		//calls the above methods and finally checks if making this move will put its own king in check
		ArrayList<Point> moves = new ArrayList<Point>();
		basicMovement(b, moves);
		capture(b, moves);
		empassant(b, moves);
		
		Iterator<Point> iter = moves.iterator();

		if (check) {
			while (iter.hasNext()) {
			    Point p = iter.next();
			    Board helper = b.tryMove(new Point[] {this.location, p});
			    if (helper.kingInCheck()) {
			    	iter.remove();
				}
			}
		}
		
		return moves;
	}

}
