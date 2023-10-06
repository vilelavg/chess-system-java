package app;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static ChessPosition readChessPosition (Scanner sc) {
			try {
				String s = sc.nextLine();
				char column = s.charAt(0);
				int row = Integer.parseInt(s.substring(1)); // aqui pegamos a String s a partir da posição 1 em diante e convertemos para inteiro
				return new ChessPosition(column, row);
			} catch (RuntimeException e) {
				throw new InputMismatchException("ERRO! Posições inválidas.");
		}
	}

	public static void printBoard(ChessPiece[][] pieces) {

		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");

			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false);

			}
			System.out.println();

		}
		System.out.println("  a b c d e f g h");

	}
	
	public static void printMatch (ChessMatch chessMatch, List <ChessPiece> captured) {
		printBoard(chessMatch.getPieces()); // imprimimos o tabuleiro com suas peças
		System.out.println();
		printCapturedPieces(captured);
		System.out.println();
		System.out.println("Turn: " + chessMatch.getTurn()); // declaramos o turno 
		System.out.println("Aguardando jogador: " + chessMatch.getCurrentPlayer()); // declaramos qual jogador deve jogar agora
		
	}
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {

		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");

			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);

			}
			System.out.println();

		}
		System.out.println("  a b c d e f g h");

	}

	// como será um metodo para mostrar todas as peças de uma
	// vez, damos a condição for. Declaramos que a variavel i
	// deve ser executada enquanto for menor que o length da
	// matriz pieces.

	// como a matriz de linhas começa de 8 para baixo, imprimimos na tela de
	// começo 8-i=8-0=8, depois i + 1 e assim por diante, até o for de
	// variavel i parar de ser executado

	// aqui declaramos o metodo com a matriz pieces[i][j] como parametro

	// esse sysout é para declarar uma quebra de linha e ir para a próxima linha
	// verificar as condições

	/*
	 * private static void printPiece(ChessPiece piece) { // metodo simples para
	 * imprimir apenas uma peça. Nesse caso, se a // variavel piece do tipo
	 * ChessPiece não estiver no tabuleiro, // imprime apenas um - if (piece ==
	 * null) { System.out.print("-"); } else { System.out.print(piece); }
	 * System.out.print(" "); // espaçamento que sempre será executado, a cada vez q
	 * uma das condições for // true }
	 */
	private static void printPiece(ChessPiece piece, boolean background) {
		
		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");

	}
	
	private static void printCapturedPieces(List <ChessPiece> captured) {
		List <ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList()); // nessa expressão, filtramos as peças da lista ChessPiece q possuem cor branca e jogamos para a lista de peças brancas capturadas
		List <ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		
			System.out.println("Pecas capturadas:");
			System.out.print("Brancas: ");
			System.out.print(ANSI_WHITE); // para definir a cor de impressão
			System.out.println(Arrays.toString(white.toArray())); // pegamos os elementos da lista white e imprimimos em formato de String
			System.out.print(ANSI_RESET); // reseta a cor impressa anteriormente 
			
			System.out.print("Pretas: ");
			System.out.print(ANSI_YELLOW);
			System.out.println(Arrays.toString(black.toArray()));
			System.out.print(ANSI_RESET);
		
	}
}
