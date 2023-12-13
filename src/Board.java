

public class Board {

	private final int SIZE;
	private char[][] array;

	public Board(int size) {
		this.SIZE = size;
		array = new char[SIZE][SIZE];

		initializeBoard();
	}

	private void initializeBoard() {
		for (int x = 0; x < SIZE; x++)
			for (int y = 0; y < SIZE; y++)
				array[x][y] = '┼';

		for (int x = 0; x < SIZE; x++) {
			array[0][x] = '┬';
			array[SIZE - 1][x] = '┴';
		}
		for (int y = 0; y < SIZE; y++) {
			array[y][0] = '├';
			array[y][SIZE - 1] = '┤';
		}

		array[0][0] = '┌';
		array[0][SIZE - 1] = '┐';
		array[SIZE - 1][0] = '└';
		array[SIZE - 1][SIZE - 1] = '┘';
	}

	public char getStoneAt(int x, int y) {
		return array[SIZE - 1 - y][x];
	}

	public void setStoneAt(int x, int y, char stone) {
		array[SIZE - 1 - y][x] = stone;
	}

	public int getSize() {
		return SIZE;
	}

}
