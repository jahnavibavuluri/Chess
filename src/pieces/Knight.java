package pieces;

import java.awt.Point;
import java.util.*;
import board.Board;

public class Knight extends Piece{

	//public final String name;
	
	public Knight(String color, int x, int y) {
		super(color, x, y);
		if (color.equals("white"))
			name = "wN ";
		else 
			name = "bN ";
	}
	
	public String getName() {
		return name;
	}
	
	public void basicMovement (Board b, ArrayList<Point> getMoves) {
		//adds knight moves to the list
		int x = location.x;
		int y = location.y;
				
		Point TwoUpOneRight = new Point(x-2,y+1);
		Point TwoUpOneLeft = new Point(x-2,y-1);
		Point TwoDownOneRight = new Point(x+2,y+1);
		Point TwoDownOneLeft = new Point(x+2,y-1);
				
		Point OneUpTwoRight = new Point(x-1,y+2);
		Point OneUpTwoLeft = new Point(x-1,y-2);
		Point OneDownTwoRight = new Point(x+1,y+2);
		Point OneDownTwoLeft = new Point(x+1,y-2);
				
		getMoves.add(TwoUpOneRight);
		getMoves.add(TwoUpOneLeft);
		getMoves.add(TwoDownOneRight);
		getMoves.add(TwoDownOneLeft);
				
		getMoves.add(OneUpTwoRight);
		getMoves.add(OneUpTwoLeft);
		getMoves.add(OneDownTwoRight);
		getMoves.add(OneDownTwoLeft);
				
		Iterator<Point> iter = getMoves.iterator();

		while (iter.hasNext()) {
		    Point p = iter.next();

		    if (p.x>7 || p.x<0 || p.y>7 ||p.y<0)
		        iter.remove();
		}
		
		/*for (Point p : getMoves) { 		      
			if(p.x>7 || p.x<0 || p.y>7 ||p.y<0) {
				getMoves.remove(p);
			}
		}*/
			
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
		
		/*for (Point p : getMoves) {
			if(this.color.equals("white")) {
				if (b.getPieceAt(p) != null && (b.getPieceAt(p)).equals("white")){
					getMoves.remove(p);
				}
					}
			if(this.color.equals("black")) {
				if (b.getPieceAt(p) != null && (b.getPieceAt(p)).equals("black")){
					getMoves.remove(p);
				}
			}
		}*/	
	
	
	public ArrayList<Point> getMoves(Board b) {
		//calls the above methods and finally checks if making this move will put its own king in check
		ArrayList<Point> moves = new ArrayList<Point>();
		basicMovement(b, moves);
		return moves;
	}

}
