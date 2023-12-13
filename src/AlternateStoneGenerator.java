

class AlternateStoneGenerator {
	private int cnt = 1;

	public char generate() {
		cnt = ++cnt % 2;

		if (cnt % 2 == 0)
			return '●';
		else
			return '○';
	}

}
