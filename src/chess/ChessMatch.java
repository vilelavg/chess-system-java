package chess;

import boardgame.Board;

public class ChessMatch {

	private Board board;

	public ChessMatch() {
		board = new Board(8, 8);
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; // essa matriz ChessPiece terá a
																					// quantidade de linhas e colunas
																					// que há no tabuleiro
		for (int i = 0; i < board.getRows(); i++) { // nessa condição for percorremos cada peça que ocupada uma posição
													// no board (row e column) e fazemos o downcasting para uma
													// chesspiece
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j); // aqui, fazemos com que cada posição i, j na matriz mat
															// receba uma peça do tabuleiro i,j, chamando o método piece
															// da classe Board. ChessPiece entre () para declarar o
															// downcasting

			}
		}
		return mat;
	}

}
