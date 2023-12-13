

public class Messages {
	private Board board;
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public void printInputRule() {
		System.out.printf("('정수 정수' 띄어쓰기, 범위 0~%d)", board.getSize() - 1);
	}
}