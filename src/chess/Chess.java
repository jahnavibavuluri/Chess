package chess;

import java.awt.Point;

import java.util.Scanner;

import board.Board;
import pieces.*;

public class Chess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board game = new Board();
		Scanner scanner = new Scanner(System.in);
		game.drawBoard();
		
		boolean resign = false;
		boolean drawPrompted = false;
		boolean draw = false;
		
		/*
		for (Piece p: game.pieces) {
			if (p instanceof Rook || p instanceof King)
				System.out.println(p.toString());
		}*/
		
		while (!game.checkmate() && !resign && !draw) {
			
			String input = scanner.nextLine();
			if (input.toLowerCase().equals("resign") && !drawPrompted) {
				resign = true;
				break;
			} 
			
			//if the last move was a draw prompted by the opponent, the current player MUST accept and enter "draw"
			if (drawPrompted) {
				if (!input.equals("draw")) {
					System.out.println("Illegal move, try again \n" + game.currentPlayer + "\'s turn: ");
					//break;
				} else {
					draw = true;
					//System.out.println("draw");
					break;
				}
			} else {
				
				Point[] move = game.move(input.substring(0,2), input.substring(3,5));
				
				
				if (game.isValidMove(move)) {
					if (game.checkPromotion(move)) {
						//System.out.println("this is a promotion move!");
						if (input.length() ==  7) {
							//can either be Q, N, R, B
							switch (input.charAt(6)) {
								case 'Q':
									game.promotion(new Queen(game.currentPlayer.toLowerCase(),0,0), move);
									game.drawBoard();
									break;
								case 'N':
									game.promotion(new Knight(game.currentPlayer.toLowerCase(),0,0), move);
									game.drawBoard();
									break;
								case 'R':
									game.promotion(new Rook(game.currentPlayer.toLowerCase(),0,0, false), move);
									game.drawBoard();
									break;
								case 'B':
									game.promotion(new Bishop(game.currentPlayer.toLowerCase(),0,0), move);
									game.drawBoard();
									break;
								default:
									System.out.println("Illegal move, try again \n" + game.currentPlayer + "\'s turn: ");
									break;
							}
						} else {
							game.promotion(new Queen(game.currentPlayer.toLowerCase(),0,0), move);
							game.drawBoard();
						}
					} else {
						//if the move is valid and the user prompts a draw, drawPrompted equals true
						if (input.length() == 11 && input.substring(6,11).equals("draw?")) {
							drawPrompted = true;
						}

						game.firstMove(move); //changes castling field for rook and king
						game.enpassant(move); //changes enpassant field for pawn
						if (game.checkEnpassant(move)) {
							game.doEnpassant(move);
							game.drawBoard();
						} else {
							game.doCastle(move); //moves the rook if its a castling move
							game.makeMove(move); //makes the move
							game.drawBoard(); //draws the board after move is made
						}
					}
				} else {
					System.out.println("Illegal move, try again \n" + game.currentPlayer + "\'s turn: ");
				}	
			}
		}
		
		if (!draw && !resign) {
			System.out.println("Checkmate");
		}
		
		if (!draw) {
			if (game.currentPlayer.toLowerCase().equals("white"))
				System.out.println("Black wins");
			else 
				System.out.println("White wins");
		}
		
		scanner.close();
		
		//game.drawBoard();
		//System.out.println(start.kingInCheck);
		
		/*
		Point[] a = game.move("g7", "h8");
		System.out.println(game.currentPlayer);
		
		if (game.isValidMove(a)) {
			System.out.println(game.currentPlayer);
			if (game.checkPromotion(a)) {
				//System.out.println(start.currentPlayer);
				game.promotion(new Knight(game.currentPlayer.toLowerCase(),0,0), a);
			} else {
				game.makeMove(a);
			}
			
			//start.makeMove(a);
			game.drawBoard();
			//System.out.println(start.kingInCheck);
			//System.out.println(start.getPieceAt(new Point(5,3)))
		} else {
			System.out.println("Illegal move, try again \n" + game.currentPlayer + "\'s turn: ");
		}
		*/
		
		//System.out.println(a[0]);
		//System.out.println(a[1]);
		//Board copy = start.tryMove(a);
		
		
		//copy.drawBoard();
		//start.makeMove(new Point(6,3), new Point(4,3));
		//start.drawBoard();
		//start.makeMove(new Point(1,4), new Point(3,4));
		//start.drawBoard();
		
		//Board start_clone = start.clone();
		//start_clone.drawBoard();
		
	}

}
