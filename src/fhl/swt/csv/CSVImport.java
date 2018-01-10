package fhl.swt.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CSVImport {

	public static <R extends CsvEntity> Map<String, R> fromFile(File file, CsvEntityImporter<R> importer) throws IOException {
		fromFile(file, (CsvImporter<R>) importer);
		return importer.getOverall();
	}

	public static <R> List<R> fromFile(File file, CsvImporter<R> importer) throws IOException {
		List<R> result = new LinkedList<>();
		int row = 0;
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		reader.readLine();
		while ((line = reader.readLine()) != null && importer.continueImport(row++)) {
			result.add(importer.apply(new CsvLine(line, row)));
		}
		reader.close();
		return result;
	}

}
