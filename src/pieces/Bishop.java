package pieces;

import java.awt.Point;
import java.util.*;
import board.Board;

public class Bishop extends Piece {
	
	//public final String name;

	public Bishop(String color, int x, int y) {
		super(color, x, y);
		if (color.equals("white"))
			name = "wB ";
		else 
			name = "bB ";
	}
	
	public String getName() {
		return name;
	}
	
	public void basicMovement (Board b, ArrayList<Point> getMoves) {
		//adds diagonal moves to the list
		int x = location.x;//0
		int y = location.y;//2
		
		//right down
		int i = x+1;
		int j = y+1;
		while (i<8 && j<8) {
			Point p = new Point(i,j);
			if (b.getPieceAt(p) == null) {
				getMoves.add(p);
			} else if (!(b.getPieceAt(p).color.equals(this.color))) {
				getMoves.add(p);
				break;
			} else {
				break;
			}
			i++;
			j++;
		}
		
		//right up
		i = x+1;
		j = y-1;
		while (i<8 && j>=0) {
			Point p = new Point(i,j);
			if (b.getPieceAt(p) == null) {
				getMoves.add(p);
			} else if (!(b.getPieceAt(p).color.equals(this.color))) {
				getMoves.add(p);
				break;
			} else {
				break;
			}
			i++;
			j--;
		}
		
		//left down
		i = x-1;
		j = y+1;
		while (i>=0 && j<8) {
			Point p = new Point(i,j);
			if (b.getPieceAt(p) == null) {
				getMoves.add(p);
			} else if (!(b.getPieceAt(p).color.equals(this.color))) {
				getMoves.add(p);
				break;
			} else {
				break;
			}
			i--;
			j++;
		}
		
		//left up
		i = x-1;
		j = y-1;
		while (i>=0 && j>=0) {
			Point p = new Point(i,j);
			if (b.getPieceAt(p) == null) {
				getMoves.add(p);
			} else if (!(b.getPieceAt(p).color.equals(this.color))) {
				getMoves.add(p);
				break;
			} else {
				break;
			}
			i--;
			j--;
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
			    if (helper.kingInCheck()) {
			    	iter.remove();
				}
			}
		}
		
		return moves;
	}

}
