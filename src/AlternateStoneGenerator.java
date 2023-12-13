
class AlternateStoneGenerator {
	private Board board;

	public void setBoard(Board board) {
		this.board = board;
	}

	public char generate() {
		char stone;
		if (board.getLastStone() == null)
			return '●';
		if (board.getLastStone().getStone() == '●')
			stone = '○';
		else
			stone = '●';
		return stone;
	}

}
