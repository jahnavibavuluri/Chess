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
	public String currentPlayer; //the color of who is currently playing
	public Piece kingInCheck = null;
	//String inCheck --> color that is currently in check if there is one?
	
	public Board() {
		currentPlayer = "White";
		pieces = new ArrayList<Piece>();
		pieces.add(new Pawn("white", 6,0,true));
		//pieces.add(new Pawn("white", 6,1,true));
		//pieces.add(new Pawn("white", 6,2,true));
		//pieces.add(new Pawn("white", 6,3,true));
		pieces.add(new Pawn("white", 6,4,true));
		//pieces.add(new Pawn("white", 6,5,true));
		pieces.add(new Pawn("white", 6,6,true));
		//pieces.add(new Pawn("white", 6,7,true));
		
		pieces.add(new Rook("white", 7,0,true));
		pieces.add(new Rook("white", 7,7,true));
		//pieces.add(new Knight("white", 7,1));
		//pieces.add(new Knight("white", 7,6));
		//pieces.add(new Bishop("white", 7,2));
		//pieces.add(new Bishop("white", 7,5));
		//pieces.add(new Queen("white",7,3));
		pieces.add(new King("white", 7,4, true));
		
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
		//pieces.add(new Knight("black", 0,1));
		//pieces.add(new Knight("black", 0,5));
		pieces.add(new Bishop("black", 4,0));
		//pieces.add(new Bishop("black", 0,6));
		//pieces.add(new Queen("black",5,5));
		pieces.add(new King("black", 0,4,true));
		
		//init all pieces in their respective positions and populate pieces
		
	}
	
//------------------------------------------------------deepCopy-------------------------------------------------------	
	public Board deepCopy() throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(this);
        
        ByteArrayInputStream bis = new   ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bis);
        Board copied = (Board) in.readObject();
        
        return copied;
	}
	
//------------------------------------------------------removePiece-------------------------------------------------------	
	public void removePiece (Piece p) {
		//this parameter might be changed to point to remove a piece at a specific location
		pieces.remove(p);
	}
//------------------------------------------------------addPiece-------------------------------------------------------	
	public void addPiece (Piece p) {
		pieces.add(p);
	}
//------------------------------------------------------getPieceAt-------------------------------------------------------	
	public Piece getPieceAt (Point p) {
		for (Piece piece:pieces) {
			if (piece.location.equals(p)) {
				return piece;
			}
		}
		return null;
	}

//------------------------------------------------------kingInCheck-------------------------------------------------------
	public boolean kingInCheck () { //checks if the current players king is in check
		for (Piece p: pieces) {
			//System.out.println("the current player is: "+ currentPlayer);
			//when this method is called, a move is already made which is why we are checking if the piece p is the same color as the current player 
			//(they are going to be opposite)
			if ((p.color).equals(currentPlayer.toLowerCase())) {
				for (Point point: p.getMoves(this, false)) {
					//get the basic moves of this piece which is why false is being passed in
					if (getPieceAt(point) instanceof King) {
						//king will be captured
						if (!getPieceAt(point).color.equals(currentPlayer.toLowerCase())) {
						//if the piece is a King and is the NOT same color as the current player this board is invalid
							//(this is because the current player is actually the opposite so !opposite equals the same color as the player)
							//System.out.println("king in check!!!");
							//this.kingInCheck = getPieceAt(point);
							//System.out.println(this.kingInCheck);
							return true;
						}
					}
				}
			}	
		}
		return false;
	}
	
//------------------------------------------------------check-------------------------------------------------------	
	public Piece check() {
		for (Piece p: pieces) {
			//go through opposing teams pieces and check if currentPlayer king is in check
			if (!((p.color).equals(currentPlayer.toLowerCase()))) {
				for (Point point:p.getMoves(this, false)) {
					if ((getPieceAt(point) instanceof King) && (getPieceAt(point)).color.equals(currentPlayer.toLowerCase())) {
						kingInCheck = getPieceAt(point);
						return getPieceAt(point);
					}
				}
			}
		}
		return null;
	}
	

//------------------------------------------------------letterToNumber-------------------------------------------------------	
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
	
//------------------------------------------------------move-------------------------------------------------------	
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
	
//------------------------------------------------------isValidMove-------------------------------------------------------
	//checks if the move is valid 
	public boolean isValidMove(Point[] points) {
		Piece moving = this.getPieceAt(points[0]);
		//System.out.println(moving.getMoves(this, true));
		if (moving == null) 
			return false;
		if (points[1].x > 7 || points[1].x < 0 || points[1].y < 0 || points[1].y > 7) 
			return false;
		if (!(moving.color).equals(currentPlayer.toLowerCase())) {
			//System.out.println(moving.color);
			//System.out.println(currentPlayer);
			return false;
		} if (!(moving.getMoves(this, true).contains(points[1]))) {
			return false;
		}
		return true;
	}
	
//------------------------------------------------------tryMove-------------------------------------------------------
	//make the move on a helper board that Pieces classes can use 
	public Board tryMove(Point[] points) {
	//check if start location is NOT null
		try {
			Board copy = this.deepCopy();
			copy.makeMove(points);
			return copy;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
//------------------------------------------------------makeMove-------------------------------------------------------
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
	
//------------------------------------------------------firstMove-------------------------------------------------------
	public void firstMove(Point[] points) {
		Piece pieceOnBoard = this.getPieceAt(points[0]);
		if (pieceOnBoard instanceof Rook) {
			((Rook) pieceOnBoard).setCastling(false);
			//this.addPiece(new Rook(currentPlayer.toLowerCase(), points[0].x, points[0].y, false));
			//this.removePiece(pieceOnBoard);
		}
		
		//if king is being moved, a new king is add to account for the false castling boolean
		if (pieceOnBoard instanceof King) {
			((King) pieceOnBoard).setCastling(false);
			//this.addPiece(new King(currentPlayer.toLowerCase(), points[0].x, points[0].y, false));
			//this.removePiece(pieceOnBoard);
		}
	}
	
//------------------------------------------------------checkPromotion-------------------------------------------------------
	public boolean checkPromotion(Point[] points) {
		if(this.getPieceAt(points[0]) instanceof Pawn && points[0].x == 1 && ((this.getPieceAt(points[0])).color).equals("white")) {
			return true;
		} else if((this.getPieceAt(points[0]) instanceof Pawn && points[0].x == 6 && ((this.getPieceAt(points[0])).color).equals("black"))) {
			return true;
		} else {
			return false;
		}
	}
	
//------------------------------------------------------promotion-------------------------------------------------------
	public void promotion(Piece promotionPiece, Point[] points) {
		if(this.getPieceAt(points[1]) == null) {
			pieces.remove(this.getPieceAt(points[0]));
			pieces.add(promotionPiece);
			promotionPiece.location = points[1];
		}else {
			pieces.remove(this.getPieceAt(points[1]));
			pieces.remove(this.getPieceAt(points[0]));
			pieces.add(promotionPiece);
			promotionPiece.location = points[1];
		}
		if (currentPlayer.equals("White"))
			currentPlayer = "Black";
		else
			currentPlayer = "White";
	}

//------------------------------------------------------checkmate-------------------------------------------------------
	public boolean checkmate() {
		
		ArrayList<Point> white = new ArrayList<Point>();
		ArrayList<Point> black = new ArrayList<Point>();
		
		for (Piece p:pieces) {
			if (p.color.equals("white")) {
				for (Point point: p.getMoves(this, true)) {
					white.add(point);
				}
			}
			else 
				for (Point point: p.getMoves(this, true)) {
					black.add(point);
				}
		}
		
		//returns true if a player is checkmated
		return (white.size() == 0 || black.size() == 0) ? true:false;
	}

//------------------------------------------------------enpassant-------------------------------------------------------
	public void enpassment(Point[] points) {
		for(Piece p: pieces) {
			if(p instanceof Pawn && p.color.equals(currentPlayer.toLowerCase())) {
				((Pawn) p).setEnpassant(false);
				/*
				String temp = p.color;
				int x = p.location.x;
				int y = p.location.y;
				this.removePiece(p);
				Piece newPawn = new Pawn(temp,x,y,false);
				pieces.add(newPawn);
				*/
			}
		}
			
		Piece pawn = getPieceAt(points[0]);
		if(pawn instanceof Pawn && pawn.color.equals("white")) {
			if(points[1].x==4) {
				String temp = pawn.color;
				int x = pawn.location.x;
				int y = pawn.location.y;
				this.removePiece(pawn);
				Piece newPawn = new Pawn(temp,x,y,true);
				pieces.add(newPawn);
						//public Pawn(String color, int x, int y, boolean firstMove)
			}
		}
		if(pawn instanceof Pawn && pawn.color.equals("black")) {
			if(points[1].x==3) {
				String temp = pawn.color;
				int x = pawn.location.x;
				int y = pawn.location.y;
				this.removePiece(pawn);
				Piece newPawn = new Pawn(temp,x,y,true);
				pieces.add(newPawn);
			}
		}
	}
	
	
//------------------------------------------------------drawBoard-------------------------------------------------------
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
		//call check to print out "Check"
		
		if (this.check() != null && !this.checkmate())
			System.out.println("Check");
		if (!this.checkmate())
			System.out.println(currentPlayer + "\'s turn: ");
		
		//this.getPieceAt(new Point(5,4)).getMoves(this);;
		
		//System.out.println(this.getPieceAt(new Point(7,4)).getMoves(this,false));//wB
		
	}

	
}
