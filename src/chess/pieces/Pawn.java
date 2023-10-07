package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);

	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);

		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {

		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);

		if (getColor() == Color.WHITE) {
				p.setValues(position.getRow() - 1, position.getColumn());
					if (getBoard().positionExists(p) && !getBoard().theresAPiece(p)) {
						mat[p.getRow()][p.getColumn()] = true;
			}
				p.setValues(position.getRow() - 2, position.getColumn());
				Position p2 = new Position (position.getRow() - 1, position.getColumn()); // lembrando q sempre esse position. é a posição atual do peão
					if (getBoard().positionExists(p) && !getBoard().theresAPiece(p) && getMoveCount() == 0 && !getBoard().theresAPiece(p2) && getBoard().positionExists(p2)) {
						mat[p.getRow()][p.getColumn()] = true;
			}
				p.setValues(position.getRow() - 1, position.getColumn() - 1);
					if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
						mat[p.getRow()][p.getColumn()] = true;
						
				}
				p.setValues(position.getRow() - 1, position.getColumn() + 1);
					if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
						mat[p.getRow()][p.getColumn()] = true;
						
				}
			
		} else if ((getColor() == Color.BLACK)) {
			
			p.setValues(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().theresAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
	}
		p.setValues(position.getRow() + 2, position.getColumn());
		Position p2 = new Position (position.getRow() + 1, position.getColumn()); // lembrando q sempre esse position. é a posição atual do peão
			if (getBoard().positionExists(p) && !getBoard().theresAPiece(p) && getMoveCount() == 0 && !getBoard().theresAPiece(p2) && getBoard().positionExists(p2)) {
				mat[p.getRow()][p.getColumn()] = true;
	}
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
				
		}
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
				
		}
			
		}
		
		return mat;
	}

	public String toString() {
		return "P";
	}

}
