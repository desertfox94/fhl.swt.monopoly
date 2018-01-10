package fhl.swt.csv;

import java.util.function.Function;

public abstract class CsvImporter<R> implements Function<CsvLine, R> {

	private static final int UNLIMITED = -1;
	private int limit;

	public CsvImporter(int limit) {
		this.limit = limit;
	}

	public CsvImporter() {
		this(UNLIMITED);
	}

	@Override
	public R apply(CsvLine line) {
		return importEntity(line);
	}

	public boolean continueImport(int row) {
		if (limit == UNLIMITED) {
			return true;
		}
		return row < limit;
	}

	protected abstract R importEntity(CsvLine line);

}
