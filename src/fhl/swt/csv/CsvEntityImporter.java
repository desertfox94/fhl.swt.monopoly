package fhl.swt.csv;

import java.util.HashMap;
import java.util.Map;

public abstract class CsvEntityImporter<R extends CsvEntity> extends CsvImporter<R> {

	protected final Map<String, R> overall = new HashMap<>();

	@Override
	public final R apply(CsvLine line) {
		R entity = importEntity(line);
		if (entity != null) {
			overall.put(entity.getId(), entity);
		}
		return entity;
	}

	public Map<String, R> getOverall() {
		return overall;
	}

}
