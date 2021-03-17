package board;

import java.awt.Point;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import pieces.*;

public class Board implements Serializable {
	
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
		//pieces.add(new Pawn("white", 6,4,true));
		pieces.add(new Pawn("white", 6,5,true));
		pieces.add(new Pawn("white", 6,6,true));
		pieces.add(new Pawn("white", 6,7,true));
		
		pieces.add(new Rook("white", 5,4,true));
		pieces.add(new Rook("white", 7,7,true));
		pieces.add(new Knight("white", 7,1));
		pieces.add(new Knight("white", 7,6));
		pieces.add(new Bishop("white", 7,2));
		pieces.add(new Bishop("white", 7,5));
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
		
		pieces.add(new Rook("black", 0,0,true));
		pieces.add(new Rook("black", 0,7,true));
		pieces.add(new Knight("black", 0,1));
		pieces.add(new Knight("black", 0,6));
		pieces.add(new Bishop("black", 0,2));
		pieces.add(new Bishop("black", 0,5));
		pieces.add(new Queen("black",3,4));
		pieces.add(new King("black", 0,4));
		
		//init all pieces in their respective positions and populate pieces
		
	}
	
	/*private Board (ArrayList<Piece> pieces, String currentPlayer) {
		this.pieces = pieces;
		this.currentPlayer = currentPlayer;
	}*/
	
	public Board deepCopy() throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(this);
        
        ByteArrayInputStream bis = new   ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bis);
        Board copied = (Board) in.readObject();
        
        return copied;
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
	
	public int letterToNumber(String s) {
		switch (s) {
		case "a":
			return 0;
		case "b":
			return 1;
		case "c":
			return 2;
		case "d":
			return 3;
		case "e":
			return 4;
		case "f":
			return 5;
		case "g":
			return 6;
		case "h":
			return 7;
		}
		return -1;
	}
	
	public Point[] move(String start, String end) {
		//takes the user's input and converts them into Points
		Point startPoint = new Point();
		Point endPoint = new Point();
		Point[] m = new Point[2];
		
		startPoint.y = this.letterToNumber(start.substring(0,1));
		endPoint.y = this.letterToNumber(end.substring(0,1));
		
		startPoint.x = 7-(Integer.parseInt(start.substring(1,2)))+1;
		endPoint.x = 7-(Integer.parseInt(end.substring(1,2)))+1;
		
		m[0] = startPoint;
		m[1] = endPoint;
		
		return m;
	}
	
	public Board tryMove(Point[] points) {
	//check if start location is NOT null
		try {
			Board copy = this.deepCopy();
			if (this.getPieceAt(points[0]) == null) {
				System.out.println("Illegal move, try again \n" + currentPlayer + "\'s turn: ");
				return null;
			}
			//location not out of bounds
			if (points[1].x > 7 || points[1].x < 0 || points[1].y < 0 || points[1].y > 7) {
				System.out.println("Illegal move, try again \n" + currentPlayer + "\'s turn: ");
				return null;
			}
			//move is in pieces getMoves array list
			Piece moving = this.getPieceAt(points[0]);
			if (!(moving.getMoves(this).contains(points[1]))) {
				System.out.println("Illegal move, try again \n" + currentPlayer + "\'s turn: ");
				return null;
			}
				
			copy.makeMove(points);
			return copy;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	public void makeMove(Point[] points) {
		Piece pieceOnBoard = this.getPieceAt(points[0]); //this is the piece we are moving
		if (this.getPieceAt(points[1]) != null) { //this IS a capture
			pieces.remove(this.getPieceAt(points[1])); //remove the captured piece
		}
		pieceOnBoard.location = points[1]; //move piece to end
		
		if (currentPlayer.equals("White"))
			currentPlayer = "Black";
		else
			currentPlayer = "White";
	}
	
	public void drawBoard() {
		String[][] board = new String[8][8];
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				for (Piece p: pieces) {
					if (p.location.equals(new Point(i,j))) {
						//one if the pieces has a location at (i,j)
						board[i][j] = p.getName();
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
		
		System.out.println(" 0  1  2  3  4  5  6  7");
		
		for (int i = 0; i<8; i++) {
			System.out.print(i);
			for (int j = 0; j<8; j++) {
				System.out.print(board[i][j]);
			}
			System.out.print(7-i+1);
			
			System.out.println();
		}
		
		System.out.println("  a  b  c  d  e  f  g  h ");
		System.out.println();
		System.out.println(currentPlayer + "\'s turn: ");
		
		System.out.println(this.getPieceAt(new Point(5,4)).getMoves(this));//wR
		//System.out.println(this.getPieceAt(new Point(3,4)).getMoves(this));//wB
		//System.out.println(this.getPieceAt(new Point(7,3)).getMoves(this));//wQ
		//System.out.println(this.getPieceAt(new Point(0,0)).getMoves(this));//bR
		//System.out.println(this.getPieceAt(new Point(0,2)).getMoves(this));//bB
		//System.out.println(this.getPieceAt(new Point(4,4)).getMoves(this));//bQ
		//System.out.println(this.getPieceAt(new Point(7,1)).getMoves(this));
		//System.out.println(this.getPieceAt(new Point(7,0)).getMoves(this));
		
	}

	
}
