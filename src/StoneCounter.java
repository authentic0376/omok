
public class StoneCounter {
	private Validator validator;
	private OneWayStoneCounter oneCounter;
	private Board board;

	class OneWayStoneCounter {
		private int count(int x, int y, int r, int c) {
			int count = 0;

			int x_ = x + r;
			int y_ = y + c;

			char stone = board.getStoneAt(x, y);
			while (validator.stoneInRange(x_, y_)) {
				if (stone == board.getStoneAt(x_, y_)) {
					count++;
				} else
					break;
				x_ += r;
				y_ += c;
			}
			return count;
		}
	}

	StoneCounter() {
		this.oneCounter = new OneWayStoneCounter();
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public int count(int x, int y, Direction direction) {
		int cnt = 1;
		cnt += oneCounter.count(x, y, direction.x, direction.y);
		cnt += oneCounter.count(x, y, -direction.x, -direction.y);
		System.out.printf("%s 갯수: %d\n", direction.name, cnt);
		return cnt;
	}

}
