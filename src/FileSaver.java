import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class FileSaver {
	private Board board;

	public void setBoard(Board board) {
		this.board = board;
	}

	public void save() throws IOException {
		FileOutputStream fos = new FileOutputStream("res/record.txt");
		PrintStream ps = new PrintStream(fos);

		int boardSize = board.getSize();

		for (int r = 0; r < boardSize; r++) {
			for (int c = 0; c < boardSize; c++) {
				ps.print(board.getStoneAt(c, boardSize - 1 - r));
				if (c != boardSize - 1)
					ps.print('-');
			}
			ps.println();
		}

		if (board.getLastStone() != null) {
			ps.print(board.getLastStone().getX());
			ps.print(" ");
			ps.print(board.getLastStone().getY());

		}

		ps.close();
		fos.close();
	}

}
