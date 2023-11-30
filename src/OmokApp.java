import java.util.Scanner;

public class OmokApp {

	public static void main(String[] args) {

		// 빈 판을 출력
		BoardPrinter.print();

		Scanner scanner = new Scanner(System.in);
		char stone;

		// 게임 루프
		gameloop: while (true) {
			int x, y;
			stone = AlternateStoneGenerator.generate();
			char colorChar = stone == '●' ? '흑' : '백';

			// x,y 입력
			System.out.println(colorChar + "돌의 좌표를 입력하세요");
			Messages.printInputRule();

			// 검사
			while (true) {

				// 형식 검사
				try {
					x = scanner.nextInt();
					y = scanner.nextInt();

				} catch (Exception e) {
					System.out.println("잘못된 입력입니다. 다시 입력하세요");
					Messages.printInputRule();
					scanner.next();
					continue;
				}

				// 범위 검사
				if (!Validator.stoneInRange(x, y)) {
					System.out.println("보드의 범위를 넘었습니다. 다시 입력하세요");
					Messages.printInputRule();
					continue;
				}

				// 중복 검사
				if (Validator.stoneDuplicated(x, y)) {
					System.out.println("중복된 자리입니다. 다시 입력하세요");
					Messages.printInputRule();
					continue;
				}
				// 금수 검사

				break;
			}
			// 착수
			BoardDataManager.setStoneAt(x, y, stone);

			// 출력
			BoardPrinter.print();

			// 승패 검사
			// 놓은 돌을 기준으로 상하, 좌우, 왼대각선, 오른대각선의 돌 갯수를 센다
			int stoneCount;
			for (int i = 0; i < Directions.directionArr.length; i++) {
				stoneCount = StoneCounter.count(x, y, Directions.directionArr[i]);
				if (stoneCount == 5) {
					System.out.printf("%c돌 승리\n", colorChar);
					break gameloop;
				}
			}

		}
	}
}

class Directions {
	static Direction vertical = new Direction(0, 1, 0, -1, "세로");
	static Direction rightDiagonal = new Direction(1, 1, -1, -1, "오른대각");
	static Direction leftDiagonal = new Direction(1, -1, -1, 1, "왼대각");
	static Direction horizontal = new Direction(1, 0, -1, 0, "가로");

	static Direction[] directionArr = { vertical, rightDiagonal, leftDiagonal, horizontal };

	static class Direction {
		int x1, y1, x2, y2;
		String name;

		Direction(int x1, int y1, int x2, int y2, String name) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.name = name;
		}
	}
}

class StoneCounter {
	static int count(int x, int y, Directions.Direction direction) {
		int cnt = 1;
		cnt += OneWayStoneCounter.count(x, y, direction.x1, direction.y1);
		cnt += OneWayStoneCounter.count(x, y, direction.x2, direction.y2);
		System.out.printf("%s 갯수: %d\n", direction.name, cnt);
		return cnt;
	}
}

class BoardPrinter {

	// 주어진 x 또는 y 좌표가 보드의 처음인지 끝인지 판단
	static boolean isFirst(int n) {
		return n == 0;
	}

	static boolean isLast(int n) {
		return n == BoardDataManager.boardSize - 1;
	}

	// 보드에 놓인 돌을 기록하는 배열을 토대로 보드를 프린트
	static void print() {

		/*
		 * ┌ ─ ┬ ─ ┬ ─ ┐
		 * 
		 * ├ ─ ┼ ─ ┼ ─ ┤
		 * 
		 * └ ─ ┴ ─ ┴ ─ ┘
		 */

		for (int row = 0; row < BoardDataManager.boardSize; row++) {
			for (int col = 0; col < BoardDataManager.boardSize; col++) {

				// 사용자가 돌을 놓는 용도가 아닌, 배열 위쪽부터 단순히 출력하는 것이므로 getStoneAt을 안쓴다
				char stone = BoardDataManager.boardArray[row][col];

				if (stone == '●') {
					System.out.print('●');
					if (isLast(col))
						System.out.printf("%d", BoardDataManager.boardSize - 1 - row);
				}

				else if (stone == '○') {
					System.out.print('○');
					if (isLast(col))
						System.out.printf("%d", BoardDataManager.boardSize - 1 - row);
				}

				else if (isFirst(row) && isFirst(col))
					System.out.print('┌');
				else if (isFirst(row) && isLast(col)) {
					System.out.print('┐');
					System.out.printf("%d", BoardDataManager.boardSize - 1);
				}

				else if (isLast(row) && isFirst(col))
					System.out.print('└');
				else if (isLast(row) && isLast(col)) {
					System.out.print('┘');
					System.out.print(0);
				} else if (isFirst(row))
					System.out.print('┬');
				else if (isLast(row))
					System.out.print('┴');
				else if (isFirst(col))
					System.out.print('├');
				else if (isLast(col)) {
					System.out.printf("┤%d", BoardDataManager.boardSize - 1 - row);
					// 옆
				} else
					System.out.print('┼');

				if (!isLast(col))
					System.out.print('─');
			}
			System.out.println();
		}
		for (int i = 0; i < BoardDataManager.boardSize; i++)
			System.out.printf("%d ", i % 10);

		System.out.println();
	}
}

// 보드의 크기와 돌의 위치를 배열로 기록
class BoardDataManager {
	static int boardSize = 15;
	static char boardArray[][] = new char[boardSize][boardSize];

	static void setStoneAt(int x, int y, char stone) {
		boardArray[boardSize - 1 - y][x] = stone;
	}

	static char getStoneAt(int x, int y) {
		return boardArray[boardSize - 1 - y][x];
	}
}

class Validator {
	static boolean stoneInRange(int x, int y) {
		return 0 <= x && x < BoardDataManager.boardSize && 0 <= y && y < BoardDataManager.boardSize;
	}

	static boolean stoneDuplicated(int x, int y) {
		return BoardDataManager.getStoneAt(x, y) == '●' || BoardDataManager.getStoneAt(x, y) == '○';
	}
}

// 돌의 한쪽 방향에 있는 돌을 센다
// 반대방향도 세서 둘을 합쳐야 완전한 직선에서 센 결과가 된다
class OneWayStoneCounter {
	static int count(int x, int y, int r, int c) {
		int count = 0;

		int x_ = x + r;
		int y_ = y + c;

		char stone = BoardDataManager.getStoneAt(x, y);
		while (Validator.stoneInRange(x_, y_)) {
			if (stone == BoardDataManager.getStoneAt(x_, y_)) {
				count++;
			} else
				break;
			x_ += r;
			y_ += c;
		}
		return count;
	}
}

class Messages {
	static void printInputRule() {
		System.out.printf("('정수 정수' 띄어쓰기, 범위 0~%d)", BoardDataManager.boardSize - 1);
	}
}

class AlternateStoneGenerator {
	static int cnt = 1;

	static char generate() {
		cnt = ++cnt % 2;

		if (cnt % 2 == 0)
			return '●';
		else
			return '○';
	}

}
