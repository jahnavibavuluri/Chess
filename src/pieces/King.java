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
	
	public ArrayList<Point> getMoves(Board b, boolean check) {
		//calls the above methods and finally checks if making this move will put its own king in check
		ArrayList<Point> moves = new ArrayList<Point>();
		basicMovement(b, moves);
		
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
