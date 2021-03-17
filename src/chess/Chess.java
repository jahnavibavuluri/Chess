package chess;

import java.awt.Point;

import board.Board;
import pieces.*;

public class Chess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board start = new Board();
		start.drawBoard();
		
		Point[] a = start.move("e2", "e4");
		//System.out.println(a[0]);
		//System.out.println(a[1]);
		Board clone = start.tryMove(a);
		clone.drawBoard();
		start.drawBoard();
		
		//start.makeMove(new Point(6,3), new Point(4,3));
		//start.drawBoard();
		//start.makeMove(new Point(1,4), new Point(3,4));
		//start.drawBoard();
		
		//Board start_clone = start.clone();
		//start_clone.drawBoard();
		
	}

}
