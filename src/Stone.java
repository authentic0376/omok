
public class Stone {
	private int x;
	private int y;

	private char stone;
	private char colorName;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public char getStone() {
		return stone;
	}

	public void setStone(char stone) {
		this.stone = stone;
	}

	public char getColorName() {
		return colorName;
	}

	public void setColorName(char colorName) {
		this.colorName = colorName;
	}

	Stone(int x, int y, char c) {
		this.x = x;
		this.y = y;
		this.stone = c;

		if (c == '●')
			colorName = '흑';
		else
			colorName = '백';
	}

}
