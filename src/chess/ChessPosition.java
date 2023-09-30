package chess;

import boardgame.Position;

public class ChessPosition {

	private char column;
	private Integer row;

	public ChessPosition(char column, Integer row) {
		if (column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Dados de linha/ coluna incorretos!");
		}
		this.column = column;
		this.row = row;

	}

	public char getColumn() {
		return column;
	}

	public Integer getRow() {
		return row;
	}

	protected Position toPosition(int row, char column) {
		return new Position(8 - row, column - 'a'); // na matriz, as linhas são contadas do zero em diante. E onde seria
													// o zero, no nosso tabuleiro é 8. Assim, 8 = 0, 7 = 1, e assim por
													// diante.
	}

	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char) ('a' - position.getColumn()), 8 - position.getRow()); // aqui apenas retorna a
																								// posiçao de acordo com
																								// o tabuleiro de
																								// xadrez, onde vem a
																								// letra seguida do
																								// numero
	}

	@Override
	public String toString() {
		return "" + column + " " + row;
	}

}
