package pieces;

import java.awt.Point;
import java.util.*;
import board.Board;

public class Queen extends Piece {

	//public final String name;
	
	public Queen(String color, int x, int y) {
		super(color, x, y);
		if (color.equals("white"))
			name = "wQ ";
		else 
			name = "bQ ";
	}
	
	public String getName() {
		return name;
	}
	
	public void lineMovement (Board b, ArrayList<Point> getMoves) {
		//adds vertical and horizontal moves to the list
		int x = location.x; //7
		int y = location.y; //0

		//down
		for(int i=x+1; i<8; i++){
			Point p = new Point(i,y);
			if (b.getPieceAt(p) == null){
				getMoves.add(p);
			} else if (!(b.getPieceAt(p).color.equals(this.color))) {
				getMoves.add(p);
				break;
			} else {;
				break;
			}
		}

		//up
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

		//right
		for(int j=y+1; j<8; j++){
			Point p = new Point(x,j);
			if (b.getPieceAt(p) == null){
				getMoves.add(p);
			} else if (!(b.getPieceAt(p).color.equals(this.color))) {
				getMoves.add(p);
				break;
			} else {
				break;
			}
		}

		//left
		for(int j=y-1; j>=0; j--){
			Point p = new Point(x,j);
			if (b.getPieceAt(p) == null){
				getMoves.add(p);
			} else if (!(b.getPieceAt(p).color.equals(this.color))) {
				getMoves.add(p);
				break;
			} else {
				break;
			}
		}
	}
	
	public void diagMovement (Board b, ArrayList<Point> getMoves) {
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
	
	public ArrayList<Point> getMoves(Board b) {
		//calls the above methods and finally checks if making this move will put its own king in check
		ArrayList<Point> moves = new ArrayList<Point>();
		lineMovement(b,moves);
		diagMovement(b,moves);
		return moves;
	}

}
