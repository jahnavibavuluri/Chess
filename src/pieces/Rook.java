package pieces;

import java.awt.Point;
import java.util.ArrayList;
import board.Board;

public class Rook extends Piece{

	public boolean castling;
	//public final String name;
	
	public Rook(String color, int x, int y, boolean castling) {
		super(color,x,y);
		this.castling = castling; 
		//though it may seem that castling will always be set to false when a rook is first created, this is needed for promotion
		if (color.equals("white"))
			name = "wR ";
		else 
			name = "bR ";
	}
	
	public String getName() {
		return name;
	}
	
	public void basicMovement(Board b, ArrayList<Point> getMoves) {
		//checks the basic horizontal and vertical movements of the rook
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
	
	public ArrayList<Point> getMoves(Board b) {
		//calls the above methods and finally checks if making this move will put its own king in check
		ArrayList<Point> moves = new ArrayList<Point>();
		basicMovement(b, moves);
		/*for (Point p: moves) {
			Board helper = b.tryMove(this.location, p);
			if (helper.kingInCheck()) {
				moves.remove(p);
			}
		}*/
		return moves;
	}

}
