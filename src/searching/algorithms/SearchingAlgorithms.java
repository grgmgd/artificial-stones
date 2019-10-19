package searching.algorithms;

public enum SearchingAlgorithms {
	BF, DF, ID, UC, GRi, ASi;

	public boolean oneOf(SearchingAlgorithms[] algorithms) {
		for (SearchingAlgorithms algorithm : algorithms) {
			if (this == algorithm)
				return true;
		}

		return false;
	}
}
