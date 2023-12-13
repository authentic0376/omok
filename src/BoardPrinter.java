

public class BoardPrinter {
	private Board board;

	public void setBoard(Board board) {
		this.board = board;
	}

	public void print() {
		int boardSize = board.getSize();

		for (int r = 0; r < boardSize; r++) {
			for (int c = 0; c < boardSize; c++) {
				System.out.print(board.getStoneAt(c, boardSize - 1 - r));
				if (c == boardSize - 1) {
					System.out.print(board.getSize() - 1 - r);
					continue;
				}
				System.out.print('-');
			}
			System.out.println();
		}
		for (int i = 0; i < board.getSize(); i++)
			System.out.printf("%d ", i % 10);
		System.out.println();

	}

}
