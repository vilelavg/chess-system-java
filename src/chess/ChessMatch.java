package chess;

import boardgame.Board;
import boardgame.Piece;
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
	
	public ChessPiece performChessMove (ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition(); // como iremos inserir a posição no formato de xadrez, temos que converter pra matriz, realizando o método toPosition() sobre o sourcePosition inserido 
		Position target = targetPosition.toPosition(); // como iremos inserir a posição no formato de xadrez, temos que converter pra matriz, realizando o método toPosition() sobre o targetPosition inserido
		validateSourcePosition(source); // nesse método validamos a posição de origem. Se n existir, lançamos uma exceção
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source,target); 
		return (ChessPiece) capturedPiece; // downcasting para ChessPiece, já que a mesma era do tipo Piece, apenas uma posição ocupada no tabuleiro
		
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source); // a peça a ser removida é p. Com isso, criamos a variavel do tipo Piece e ela recebe o resultado do método removePiece tendo a posição de origem source como argumento
		Piece capturedPiece = board.removePiece(target); // a peça capturada será a da posição de destino. Deixando assim, a posição vaga
		board.PlacePiece(p, target);
		return capturedPiece;
	}
	
	private void validateSourcePosition (Position position) {
		if (!board.theresAPiece(position)) {
			throw new ChessException("Não existe peça na posição");
		} 
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("Não há movimentos para a peça");
		}
	}
	
	private void validateTargetPosition (Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("ERRO!");
		}
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) { // esse metodo já joga as coordenadas no
																			// sistema do xadrez
		board.PlacePiece(piece, new ChessPosition(column, row).toPosition()); // aqui chamamos o método
																							// PlacePiece e damos como
																							// argumento a piece,
																							// declarando uma
																							// chessPosition e aplicando
																							// o método toPosition para
																							// jogar às coordenadas da
																							// matriz

	}

	private void initialSetup() {

		placeNewPiece('d', 1, new King(board, Color.WHITE));
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));

		placeNewPiece('d', 8, new King(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));

	}

}
