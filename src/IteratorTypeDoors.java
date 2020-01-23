import java.util.Iterator;

public class IteratorTypeDoors implements Iterable<TypeDoors>, Iterator<TypeDoors> {
	
	private TypeDoors[] types;
	private int currentIndex = -1;
	
	public IteratorTypeDoors() {
		types = TypeDoors.values();
	}

	@Override
	public boolean hasNext() {
		if (currentIndex + 1 >= types.length) {
			currentIndex = -1;
			return false;
		}
		return true;
	}

	@Override
	public TypeDoors next() {
		return types[++currentIndex];
	}

	@Override
	public Iterator<TypeDoors> iterator() {
		return this;
	}
}
