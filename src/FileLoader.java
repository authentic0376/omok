import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileLoader {
	private Board board;

	public void setBoard(Board board) {
		this.board = board;
	}

	public void load() throws IOException {
		FileInputStream fis = new FileInputStream("res/record.txt");
		Scanner scan = new Scanner(fis);

		for (int r = 0; r < board.getSize(); r++) {
			String[] lineTokens = scan.nextLine().split("-");
			for (int x = 0; x < board.getSize(); x++) {
				board.setStoneAt(x, board.getSize() - 1 - r, lineTokens[x].charAt(0));
			}
		}

		int x, y;
		if (scan.hasNextLine()) {
			x = scan.nextInt();
			y = scan.nextInt();

			Stone stone = new Stone(x, y, board.getStoneAt(x, y));
			board.setLastStone(stone);
		}

		scan.close();
		fis.close();
	}
}
