package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private boolean check;
	private int turn;
	private Color currentPlayer;
	private Board board;
	
	List<Piece> piecesOnTheBoard = new ArrayList<>(); 
	List<Piece> capturedPieces = new ArrayList<>(); 
	
	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	public boolean getCheck() {
		return check;
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
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

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		
		Position source = sourcePosition.toPosition(); 
		Position target = targetPosition.toPosition();
		validateSourcePosition(source); 
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
				
		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("Movimento resulta em check");
		}
		
		check = (testCheck(opponent(currentPlayer))) ? true : false; // caso o testCheck do oponente do jogador atual dê true, o check vira true. Assim com false tb
		
		nextTurn();
		return (ChessPiece) capturedPiece; 

	}

	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source); 
		Piece capturedPiece = board.removePiece(target); 
		board.PlacePiece(p, target);
		
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		return capturedPiece;
	}
	
	private void undoMove (Position source, Position target, Piece capturedPiece) {
		Piece p = board.removePiece(target);
		board.PlacePiece(p, source);
		
		if (capturedPiece != null) {
			board.PlacePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
		
	}

	private void validateSourcePosition(Position position) {
		if (!board.theresAPiece(position)) {
			throw new ChessException("Não existe peça na posição");
		}
		if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) { // se a cor da peça nessa posição do tabuleiro for diferente do currentPlayer
			throw new ChessException("Peça adiversária");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("Não há movimentos para a peça");
		}
	}

	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("ERRO!");
		}
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer= (currentPlayer== Color.WHITE) ? Color.BLACK : Color.WHITE; // se o currentPlayer for branco, então o proximo sera black. Caso contrario, sera white 
	}
	
	private Color opponent (Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;  // se a cor for preta, eu retorno branco. Caso contrario, retorno preto
			
	}
	
	private ChessPiece king (Color color) {
		List <Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor()==color).collect(Collectors.toList()); // pegamos um elemento x, tal qual tenha a mesma cor da q a inserida como argumento
			for (Piece p : list) {
				if (p instanceof King) {
					return (ChessPiece)p;
				}
			} 
			throw new IllegalStateException("Não existe rei da cor " + color + " no tabuleiro");
	
	}
	
	private boolean testCheck (Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List <Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor()== opponent(color)).collect(Collectors.toList());
		for (Piece p : opponentPieces) { // para cada peça p presente na lista, vamos transformar em uma matriz de v ou f para seus possiveis movimentos
			boolean [][] mat = p.possibleMoves();
				if (mat [kingPosition.getRow()][kingPosition.getColumn()]) { // se a posição do rei estiver dentro dos movimentos possiveis de alguma peça adiversaria, retorna true
					return true;
				}
			
		}
		return false;
	
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) { // esse metodo já joga as coordenadas no
																			// sistema do xadrez
		board.PlacePiece(piece, new ChessPosition(column, row).toPosition()); // aqui chamamos o método
		piecesOnTheBoard.add(piece);																		// PlacePiece e damos como
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
