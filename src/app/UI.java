package app;

import chess.ChessPiece;

public class UI {

	public static void printBoard(ChessPiece[][] pieces) { // como será um metodo para mostrar todas as peças de uma
															// vez, damos a condição for. Declaramos que a variavel i
															// deve ser executada enquanto for menor que o length da
															// matriz pieces.

		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " "); // como a matriz de linhas começa de 8 para baixo, imprimimos na tela de
												// começo 8-i=8-0=8, depois i + 1 e assim por diante, até o for de
												// variavel i parar de ser executado
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j]); // aqui declaramos o metodo com a matriz pieces[i][j] como parametro
			}
			System.out.println(); // esse sysout é para declarar uma quebra de linha e ir para a próxima linha
									// verificar as condições
		}
		System.out.println("  a b c d e f g h");
	}

	private static void printPiece(ChessPiece piece) { // metodo simples para imprimir apenas uma peça. Nesse caso, se a
														// variavel piece do tipo ChessPiece não estiver no tabuleiro,
														// imprime apenas um -
		if (piece == null) {
			System.out.print("-");
		} else {
			System.out.print(piece);
		}
		System.out.print(" "); // espaçamento que sempre será executado, a cada vez q uma das condições for
									// true
	}
}
