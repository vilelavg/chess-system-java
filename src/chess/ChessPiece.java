package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {

	private Color color;
	private int moveCount;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	public void increaseMoveCount () {
		moveCount++;
	}
	
	public void decreaseMoveCount () {
		moveCount--;
	}
	
	protected boolean isThereOpponentPiece (Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position); // pegamos a peça p que está na posição inserida
			return p != null && p.getColor() != color; // retorna p se a mesma for diferente de null e se a cor da peça p for diferente da cor da peça onde eu estou
	}
	
	public ChessPosition getChessPosition () {
		return ChessPosition.fromPosition(position);
		
	}

}
