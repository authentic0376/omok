
import java.io.IOException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws IOException {
		final int BOARD_SIZE = 15;
		Board board = new Board(BOARD_SIZE);

		BoardPrinter printer = new BoardPrinter();
		printer.setBoard(board);

		AlternateStoneGenerator stoneGen = new AlternateStoneGenerator();
		stoneGen.setBoard(board);

		Messages messages = new Messages();
		messages.setBoard(board);

		Validator validator = new Validator();
		validator.setBoard(board);

		StoneCounter counter = new StoneCounter();
		counter.setValidator(validator);
		counter.setBoard(board);

		FileSaver saver = new FileSaver();
		saver.setBoard(board);

		FileLoader loader = new FileLoader();
		loader.setBoard(board);

		Scanner scanner = new Scanner(System.in);

		// 불러오기
		System.out.println("불러오려면 l을 입력하세요. 불러오지 않으려면 아무키나 누르세요");
		if (scanner.next().equals("l"))
			loader.load();

		// 판을 출력
		printer.print();

		char stone;

		// 게임 루프
		while (true) {
			String sx, sy;
			String s;
			int x, y;
			stone = stoneGen.generate();
			char colorChar = stone == '●' ? '흑' : '백';

			// x,y 입력
			System.out.println(colorChar + "돌의 좌표를 입력하세요. 저장하려면 s를 입력하세요. 종료하려면 x를 누르세요");
			messages.printInputRule();

			// 저장
			s = scanner.next();
			if (s.equals("s")) {
				saver.save();
				System.out.println("저장되었습니다.");
				stoneGen.generate();
				continue;
			}

			if (s.equals("x")) {
				System.out.println("종료합니다.");
				return;
			}

			// 검사
			while (true) {
				sx = s;
				sy = scanner.next();

				// 형식 검사
				try {
					x = Integer.parseInt(s);
					y = Integer.parseInt(sy);

				} catch (Exception e) {
					System.out.println("잘못된 입력입니다. 다시 입력하세요");
					messages.printInputRule();
					scanner.next();
					continue;
				}

				// 범위 검사
				if (!validator.stoneInRange(x, y)) {
					System.out.println("보드의 범위를 넘었습니다. 다시 입력하세요");
					messages.printInputRule();
					continue;
				}

				// 중복 검사
				if (validator.stoneDuplicated(x, y)) {
					System.out.println("중복된 자리입니다. 다시 입력하세요");
					messages.printInputRule();
					continue;
				}
				// 금수 검사

				break;
			}
			// 착수
			board.setStoneAt(x, y, stone);

			// 출력
			printer.print();

			// 승패 검사
			// 놓은 돌을 기준으로 상하, 좌우, 왼대각선, 오른대각선의 돌 갯수를 센다
			int stoneCount;
			Directions directions = new Directions();
			for (Direction direction : directions) {
				stoneCount = counter.count(x, y, direction);
				if (stoneCount == 5) {
					System.out.printf("%c돌 승리\n", colorChar);
					return;
				}
			}
		}
	}

}
