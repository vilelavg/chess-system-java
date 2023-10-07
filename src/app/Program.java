package app;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List <ChessPiece> captured = new ArrayList<>();

		while (!chessMatch.getCheckMate()) { // o true faz com que repita sempre 
			
			try {
				UI.clearScreen();
				
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
								
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				if (capturedPiece != null) { // se a peça capturada for diferente de nulo, eu adiciono a peça em questão à lista de peças capturadas 
					captured.add(capturedPiece);
					
				}
			} catch (ChessException e) {
				System.out.println("ERRO! " + e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("ERRO! " + e.getMessage());
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);

	}

}
