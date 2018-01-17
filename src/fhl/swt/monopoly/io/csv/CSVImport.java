package fhl.swt.monopoly.io.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CSVImport {

	public static <R extends CsvEntity> Map<String, R> fromFile(File file, CsvEntityImporter<R> importer) throws IOException {
		fromFile(file, (CsvImporter<R>) importer);
		return importer.getOverall();
	}

	public static <R> List<R> fromFile(File file, CsvImporter<R> importer) throws IOException {
		return from(new FileInputStream(file), importer);
	}

	public static <R> List<R> from(InputStream stream, CsvImporter<R> importer) throws IOException {
		List<R> result = new LinkedList<>();
		int row = 0;
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String line;
		reader.readLine();
		while ((line = reader.readLine()) != null && importer.continueImport(row++)) {
			result.add(importer.apply(new CsvLine(line, row)));
		}
		reader.close();
		return result;
	}

}
