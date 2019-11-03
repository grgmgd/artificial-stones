package searching.algorithms;

public enum SearchingAlgorithms {
	BF, DF, ID, UC, GR1, GR2, AS1, AS2;

	public boolean oneOf(SearchingAlgorithms[] algorithms) {
		for (SearchingAlgorithms algorithm : algorithms) {
			if (this == algorithm)
				return true;
		}

		return false;
	}

}
