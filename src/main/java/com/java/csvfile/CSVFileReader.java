package com.java.csvfile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVFileReader {

	private static final char DEFAULT_SEPARATOR = ',';
	private static final char DEFAULT_QUOTE = '"';

	public static void main(String[] args) throws Exception {
		String csvFile = "/Users/naresh.somagari/Downloads/File.csv";
		Scanner scanner = new Scanner(new File(csvFile));
		while (scanner.hasNext()) {
			List<String> line = parseLine(scanner.nextLine());
			System.out.println(line.get(0) + " " + line.get(1) + " " + line.get(2));
		}
		scanner.close();
	}

	public static List<String> parseLine(String cvsLine) {
		return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
	}

	public static List<String> parseLine(String cvsLine, char separators) {
		return parseLine(cvsLine, separators, DEFAULT_QUOTE);
	}

	@SuppressWarnings("null")
	public static List<String> parseLine(String cvsLine, char separators, char customQuote) {
		List<String> result = new ArrayList<>();
		if (cvsLine == null && cvsLine.isEmpty()) {
			return result;
		}
		if (customQuote == ' ') {
			customQuote = DEFAULT_QUOTE;
		}
		if (separators == ' ') {
			separators = DEFAULT_SEPARATOR;
		}
		StringBuffer curVal = new StringBuffer();
		boolean inQuotes = false;
		boolean startCollectChar = false;
		boolean doubleQuotesInColumn = false;
		char[] characters = cvsLine.toCharArray();
		for (char character : characters) {
			if (inQuotes) {
				startCollectChar = true;
				if (character == customQuote) {
					inQuotes = false;
					doubleQuotesInColumn = false;
				} else {
					if (character == '\"') {
						if (!doubleQuotesInColumn) {
							curVal.append(character);
							doubleQuotesInColumn = true;
						}
					} else {
						curVal.append(character);
					}
				}
			} else {
				if (character == customQuote) {

					inQuotes = true;
					if (characters[0] != '"' && customQuote == '\"') {
						curVal.append('"');
					}
					if (startCollectChar) {
						curVal.append('"');
					}

				} else if (character == separators) {

					result.add(curVal.toString());

					curVal = new StringBuffer();
					startCollectChar = false;

				} else if (character == '\r') {
					continue;
				} else if (character == '\n') {
					break;
				} else {
					curVal.append(character);
				}
			}
		}

		result.add(curVal.toString());
		return result;
	}
}