package board;

import java.awt.Point;
import java.util.*;
import pieces.*;

public class Board implements Cloneable {
	
	public ArrayList<Piece> pieces; //contains all the pieces currently in play
	String currentPlayer; //the color of who is currently playing
	//String inCheck --> color that is currently in check if there is one?
	
	public Board() {
		currentPlayer = "White";
		pieces = new ArrayList<Piece>();
		pieces.add(new Pawn("white", 6,0,true));
		pieces.add(new Pawn("white", 6,1,true));
		pieces.add(new Pawn("white", 6,2,true));
		pieces.add(new Pawn("white", 6,3,true));
		pieces.add(new Pawn("white", 6,4,true));
		pieces.add(new Pawn("white", 6,5,true));
		pieces.add(new Pawn("white", 6,6,true));
		pieces.add(new Pawn("white", 6,7,true));
		
		pieces.add(new Rook("white", 0,0,true));
		pieces.add(new Rook("white", 0,7,true));
		pieces.add(new Knight("white", 0,1));
		pieces.add(new Knight("white", 0,6));
		pieces.add(new Bishop("white", 0,2));
		pieces.add(new Bishop("white", 0,5));
		pieces.add(new Queen("white",7,3));
		pieces.add(new King("white", 7,4));
		
		pieces.add(new Pawn("black", 1,0,true));
		pieces.add(new Pawn("black", 1,1,true));
		pieces.add(new Pawn("black", 1,2,true));
		pieces.add(new Pawn("black", 1,3,true));
		pieces.add(new Pawn("black", 1,4,true));
		pieces.add(new Pawn("black", 1,5,true));
		pieces.add(new Pawn("black", 1,6,true));
		pieces.add(new Pawn("black", 1,7,true));
		
		pieces.add(new Rook("black", 7,0,true));
		pieces.add(new Rook("black", 7,7,true));
		pieces.add(new Knight("black", 7,1));
		pieces.add(new Knight("black", 7,6));
		pieces.add(new Bishop("black", 7,2));
		pieces.add(new Bishop("black", 7,5));
		pieces.add(new Queen("black",0,3));
		pieces.add(new King("white", 0,4));
		
		
		Pawn whitePawn = new Pawn("white", 0, 0, true);
		pieces.add(whitePawn);
		//init all pieces in their respective positions and populate pieces
		
	}
	
	public Board clone() {
		//init a new board that is a clone --> need to do more research on how to implement Cloneable
		return new Board();
	}
	
	public void removePiece (Piece p) {
		//this parameter might be changed to point to remove a piece at a specific location
		pieces.remove(p);
	}
	
	public void addPiece (Piece p) {
		pieces.add(p);
	}
	
	public Piece getPieceAt (Point p) {
		for (Piece piece:pieces) {
			if (piece.location.equals(p)) {
				return piece;
			}
		}
		return null;
	}
	
	public boolean kingInCheck () { //checks if the current players king is in check
		for (Piece p: pieces) {
			for (Point point:p.getMoves(this)) {
				if (getPieceAt(point) instanceof King && getPieceAt(point).color.equals(currentPlayer)) {
					//if the piece is a King and is the same color as the current player this board is invalid
					return true;
				}
			}
		}
		return false;
	}
	
	public Board tryMove(Piece piece, Point point) {
		//make a clone and try the move on that board to check if the move will put the king in check
		//need to finish the clone implementation for this method
		return new Board();
	}
	
	public void makeMove (Piece piece, Point point) {
		
	}
	
	public void drawBoard() {
		String[][] board = new String[8][8];
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				for (Piece p: pieces) {
					if (p.location.equals(new Point(i,j))) {
						//one if the pieces has a location at (i,j)
						board[i][j] = "hi ";
						break;//this might not work idk how to do final variables for abstract classes and how inheritance works for it
					} 
				}
				if (board[i][j] == null) {
					if ((i+j)%2==1) 
						board[i][j] = "## ";
					else
						board[i][j] = "   ";
				}
			}
		}
		
		for (int i = 0; i<8; i++) {
			for (int j = 0; j<8; j++) {
				System.out.print(board[i][j]);
			}
			System.out.print(7-i+1);
			System.out.println();
		}
		
		System.out.println(" a  b  c  d  e  f  g  h ");
		System.out.println();
		System.out.print(currentPlayer + "\'s turn: ");
		
	}

	
}
