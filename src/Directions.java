
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Directions implements Iterator<Direction>, Iterable<Direction> {
	private List<Direction> list = new ArrayList<>();

	Directions() {
		Direction d1 = new Direction(1, 0, "가로");
		Direction d2 = new Direction(1, 1, "오른대각");
		Direction d3 = new Direction(0, 1, "세로");
		Direction d4 = new Direction(1, -1, "왼대각");

		list.add(d1);
		list.add(d2);
		list.add(d3);
		list.add(d4);
	}

	@Override
	public boolean hasNext() {
		return list.iterator().hasNext();
	}

	@Override
	public Direction next() {
		return list.iterator().next();
	}

	@Override
	public Iterator<Direction> iterator() {
		return list.iterator();
	}
}
