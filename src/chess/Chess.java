package chess;

import java.awt.Point;

import java.util.Scanner;

import board.Board;
import pieces.*;

public class Chess {

	/**
	 * The main method initializes the chess game and looks for
	 * user input to make the necessary moves. 
	 * <p>
	 * The user must enter the input of the form "FileRank FileRank", 
	 * where the first file (column) and rank (row) are the coordinates 
	 * of the piece to be moved, and the second file and rank are the 
	 * coordinates of where it should end up. As an example, 
	 * advancing the white king's pawn two spaces would be 
	 * input as "e2 e4".
	 * <p>
	 * A pawn promotion is indicated by putting the piece to be 
	 * promoted to after the move. So, promoting a pawn to a 
	 * knight might be "g7 g8 N". If no promotion piece is 
	 * indicated, it is assumed to be a queen.
	 * <p>
	 * If checkmate occurs, the game ends immediately with the winning 
	 * team displayed. A player may resign by entering in resign.
	 * A player may offer a draw by appending "draw?" to the end of an 
	 * otherwise regular move. When a draw is offered, the other 
	 * player is obligated to accept, and the game ends, 
	 * whatever the actual situation may be. So the other player will 
	 * simply submit "draw" as the entirety of their next move.
	 * 
	 * @author Jahnavi Bavuluri and Chiraag Rekhari
	 * @param args
	 */
	public static void main(String[] args) {
		Board game = new Board();
		Scanner scanner = new Scanner(System.in);
		game.drawBoard();
		
		boolean resign = false;
		boolean drawPrompted = false;
		boolean draw = false;
		
		while (!game.checkmate() && !resign && !draw) {
			
			String input = scanner.nextLine();
			if (input.toLowerCase().equals("resign") && !drawPrompted) {
				resign = true;
				break;
			} 
			
			//if the last move was a draw prompted by the opponent, the current player MUST accept and enter "draw"
			if (input.toLowerCase().equals("undo")) {
				game.undoMove();
			} else if (drawPrompted) {
				if (!input.equals("draw")) {
					System.out.println("Illegal move, try again \n" + game.currentPlayer + "\'s move: ");
				} else {
					draw = true;
					break;
				}
			} else {
				
				Point[] move = new Point[2];
				if (input.toLowerCase().equals("ai")) {
					move = game.aiMove();
				} else {
					move = game.move(input.substring(0,2), input.substring(3,5));
				}
				
				if (game.isValidMove(move)) {
					if (game.checkPromotion(move)) {
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
									game.promotion(new Rook(game.currentPlayer.toLowerCase(),0,0, true), move);
									game.drawBoard();
									break;
								case 'B':
									game.promotion(new Bishop(game.currentPlayer.toLowerCase(),0,0), move);
									game.drawBoard();
									break;
								default:
									System.out.println("Illegal move, try again \n" + game.currentPlayer + "\'s move: ");
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
					System.out.print("Illegal move, try again \n" + game.currentPlayer + "\'s move: ");
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
	}

}
