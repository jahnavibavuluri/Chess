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
		
		while (!game.checkmate()) {
			//game.drawBoard();
			
			String input = scanner.nextLine();
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
					game.makeMove(move);
					game.drawBoard();
				}
			} else {
				System.out.println("Illegal move, try again \n" + game.currentPlayer + "\'s turn: ");
			}
			
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
