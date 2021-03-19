package pieces;

import java.awt.Point;
import java.util.*;
import board.Board;

public class King extends Piece{

	public boolean castling;
	
	public King(String color, int x, int y, boolean castling) {
		super(color, x, y);
		if (color.equals("white"))
			name = "wK ";
		else 
			name = "bK ";
		
		this.castling = castling;
	}
	
	public String getName() {
		return name;
	}
	
	public void setCastling(boolean b) {
		this.castling = b;
	}
	
	public void basicMovement (Board b, ArrayList<Point> getMoves) {
		int x = location.x;
		int y = location.y;
		Point up;
		Point down;
		Point right;
		Point left;
		
		Point upRight;
		Point upLeft;
		Point downRight;
		Point downLeft;
		
		if(this.color.equals("white")) {
			 up = new Point(x-1,y);
			 down = new Point(x+1,y);
			 right = new Point(x,y+1);
			 left = new Point(x,y-1);
			 
			 upRight = new Point(x-1,y+1);
			 upLeft = new Point(x-1,y-1);
			 downRight = new Point(x+1,y+1);
			 downLeft = new Point(x+1,y-1);
			 
		}else {
			 up = new Point(x-1,y);
			 down = new Point(x+1,y);
			 right = new Point(x,y+1);
			 left = new Point(x,y-1);
			 
			 upRight = new Point(x-1,y+1);
			 upLeft = new Point(x-1,y-1);
			 downRight = new Point(x+1,y+1);
			 downLeft = new Point(x+1,y-1);
		}
			
		getMoves.add(up);
		getMoves.add(down);
		getMoves.add(right);
		getMoves.add(left);
		getMoves.add(upRight);
		getMoves.add(upLeft);
		getMoves.add(downRight);
		getMoves.add(downLeft);
			
			
		Iterator<Point> iter = getMoves.iterator();

		while (iter.hasNext()) {
			Point p = iter.next();

			if (p.x>7 || p.x<0 || p.y>7 ||p.y<0)
				iter.remove();
			}
			
			Iterator<Point> iter2 = getMoves.iterator();
			
			while (iter2.hasNext()) {
			    Point p = iter2.next();
			    
			    if(this.color.equals("white")) {
					if((b.getPieceAt(p) != null) && ((b.getPieceAt(p)).color).equals("white")){
						iter2.remove();
					}
				}
				if(this.color.equals("black")) {
					if (b.getPieceAt(p) != null && ((b.getPieceAt(p)).color).equals("black")){
						iter2.remove();
					}
				}
			}
	}
	
	public void castling(Board b, ArrayList<Point> moves) {
		//check if king has not moved
		if (!this.castling) return;
		
		//check if king is NOT in check
		if (b.kingInCheck!= null && b.kingInCheck.equals(this)) return;
		
		//check that rooks are the same color and have not moved yet
		ArrayList<Piece> rooksThatCanCastle = new ArrayList<Piece>();
		for (Piece p:b.pieces) {
			if (p instanceof Rook && p.color.equals(this.color) && ((Rook) p).castling) {
				//System.out.println(this.color);
				//h2 h4
			//System.out.println(p.toString() + p.location);
				rooksThatCanCastle.add(p);
			}
		}
		if (rooksThatCanCastle.isEmpty()) return;
		
		
		//checks that the squares between the rook and king are EMPTY
		Iterator<Piece> iter = rooksThatCanCastle.iterator();
		while (iter.hasNext()) {
			Piece p = iter.next();
			if (p.location.y == 0) {
				if (b.getPieceAt(new Point(p.location.x, 1))!=null 
						|| b.getPieceAt(new Point(p.location.x, 2))!=null 
						|| b.getPieceAt(new Point(p.location.x, 3))!=null) {
					
					iter.remove();
				}
			} else if (p.location.y == 7) {
				if (b.getPieceAt(new Point(p.location.x, 5))!=null 
						|| b.getPieceAt(new Point(p.location.x, 6))!=null ) {
					iter.remove();
				}
			} else {
				//System.out.println("ERROR: loop 1 should not go here");
			}
		}
		if (rooksThatCanCastle.isEmpty()) return;
		
		
		//check if none of the squares that the king is passing through is in check
		
		Iterator<Piece> iter2 = rooksThatCanCastle.iterator();
		while(iter2.hasNext()) {
			Piece p = iter2.next();
			if (p.location.y == 0) {
				for (int i = 1; i<4; i++) {
					//Board helper = b.tryMove(new Point[] {this.location, new Point(this.location.x, i)});	
					if (this.kingInCheck(b,new Point(this.location.x, i) )) {
				    	iter2.remove();
				    	break;
					}
				}
			} else if (p.location.y == 7) {
				for (int i = 5; i<7; i++) {
					//Board helper = b.tryMove(new Point[] {this.location, new Point(this.location.x, i)});	
					if (this.kingInCheck(b, new Point(this.location.x, i))) {
				    	iter2.remove();
				    	break;
					}
				}
			} else {
				//System.out.println("ERROR: loop 2 should not go here");
			}
		}
		if (rooksThatCanCastle.isEmpty()) return;
		
		//FINALLY add the castling point to the array list moves
		Iterator<Piece> iter3 = rooksThatCanCastle.iterator();
		while(iter3.hasNext()) {
			Piece p = iter3.next();
			if (p.location.y == 0) {
				moves.add(new Point(this.location.x, 2));
			} else if (p.location.y == 7) {
				moves.add(new Point(this.location.x, 6));
			} else {
				//System.out.println("ERROR: loop 3 should not go here");
			}
		}
	}
	
	
	public boolean kingInCheck(Board b, Point point) {
		ArrayList<Piece> opposingPieces = new ArrayList<Piece>();
		for (Piece p: b.pieces) {
			if (!p.color.equals(this.color) && !(p instanceof King)) {
				opposingPieces.add(p);
			}
		}
		
		for (Piece p: opposingPieces) {
			for (Point point1 : p.getMoves(b,false)) {
				if (point1.equals(point))
					return true;
			}
		}
		return false;
	}
	
	
	public ArrayList<Point> getMoves(Board b, boolean check) {
		//calls the above methods and finally checks if making this move will put its own king in check
		ArrayList<Point> moves = new ArrayList<Point>();
		basicMovement(b, moves);
		castling(b, moves);
		Iterator<Point> iter = moves.iterator();

		if (check) {
			while (iter.hasNext()) {
			    Point p = iter.next();
			    Board helper = b.tryMove(new Point[] {this.location, p});
			    //helper.drawBoard();
			    if (helper.kingInCheck()) {
			    	iter.remove();
			    	//System.out.println("removing!");
				}
			}
		}
		
		return moves;
	}
	
	public String toString() {
		return "King: " + castling;
	}

}
