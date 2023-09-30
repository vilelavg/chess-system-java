package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("ERRO! É necessário pelo menos uma coluna e/ ou linha");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) { // essa condição diz que, se a posição não existir, lançamos a exceção
			throw new BoardException("Posição não existe");
		}
		return pieces[row][column];
	}

	public Piece piece(Position position) {
		if (!positionExists(position)) { // essa condição diz que, se a posição não existir, lançamos a exceção
			throw new BoardException("Posição não existe");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	public void PlacePiece(Piece piece, Position position) {
		if (theresAPiece(position)) {
			throw new BoardException("Já possui uma peça nessa posição: " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece; // aqui pegamos a matriz pieces, na posição (linha e
																	// coluna) indicada e atribuimos a essa posição na
																	// matriz a peça indicada
		piece.position = position; // como a posição inicial declarada de uma peça na classe Piece é null,
									// declaramos que agora é a posição informada ali como parametro do metodo
	}

	public boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns; // a row e column inserida deve ser menor ou
																			// igual a zero, porem, sempre menores que a
																			// quantidade de rows e columns definida
																			// como padrão.
	}

	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn()); // aqui exacutamos o método positionExists com a
																		// row e column da posição indicada
	}

	public boolean theresAPiece(Position position) {
		if (!positionExists(position)) { // essa condição diz que, se a posição não existir, lançamos a exceção
			throw new BoardException("Posição não existe");
		}
		return piece(position) != null; // vai retornar true ou false, dependendo se na posição que está de argumento no
										// método piece há uma peça ou não
	}
}
