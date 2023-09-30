package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;

	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
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

	private void initialSetup() {
		board.PlacePiece(new Rook(board, Color.WHITE), new Position(2, 1)); // esse metodo é responsavel pelo inicio da
																			// partida. Assim chamamos o metodo
																			// PlacePiece lá da classe board. No lugar
																			// da peça, podemos instanciar um novo tipo
																			// de peça: Rook, King... depois, declaramos
																			// seus parametros, que é o board e sua cor.
																			// Após isso, podemos instanciar um novo
																			// tipo Position.
		board.PlacePiece(new King(board, Color.BLACK), new Position(5, 3));
		board.PlacePiece(new Rook(board, Color.BLACK), new Position(7, 5));
	}

}
