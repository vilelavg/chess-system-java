package app;

import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {

		ChessMatch chessMatch = new ChessMatch(); // aqui instanciamos a classe CheesMatch, dando "inicio" a partida
		UI.printBoard(chessMatch.getPieces()); // aqui instanciamos o metodo printBoard criado na classe UI, que
												// imprimirá o tabuleiro com as peças da variavel instanciada como new
												// ChessMatch()

	}

}
