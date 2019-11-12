package com.csv.file.csv_file_reader;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.java.csvfile.CSVFileReader;

import org.hamcrest.core.IsNull;

public class CSVFileTest {
	@Test
	public void test_custom_separator() {
		String line = "10|in|india";
		List<String> result = CSVFileReader.parseLine(line, '|');
		assertThat(result, IsNull.notNullValue());
		assertThat(result.size(), is(3));
		assertThat(result.get(0), is("10"));
		assertThat(result.get(1), is("in"));
		assertThat(result.get(2), is("india"));
	}

	@Test
	public void test_custom_separator_and_quote() {
		String line = "'10'|'in'|'india'";
		List<String> result = CSVFileReader.parseLine(line, '|', '\'');
		assertThat(result, IsNull.notNullValue());
		assertThat(result.size(), is(3));
		assertThat(result.get(0), is("10"));
		assertThat(result.get(1), is("in"));
		assertThat(result.get(2), is("india"));

	}

	@Test
	public void test_custom_separator_and_quote_but_custom_quote_in_column() {
		String line = "'10'|'in'|'Ind|tralia'";
		List<String> result = CSVFileReader.parseLine(line, '|', '\'');
		assertThat(result, IsNull.notNullValue());
		assertThat(result.size(), is(3));
		assertThat(result.get(0), is("10"));
		assertThat(result.get(1), is("in"));
		assertThat(result.get(2), is("Ind|tralia"));
	}

	@Test
	public void test_custom_separator_and_quote_but_double_quotes_in_column() {
		String line = "'10'|'in'|'Ind\"\"tralia'";
		List<String> result = CSVFileReader.parseLine(line, '|', '\'');
		assertThat(result, IsNull.notNullValue());
		assertThat(result.size(), is(3));
		assertThat(result.get(0), is("10"));
		assertThat(result.get(1), is("in"));
		assertThat(result.get(2), is("Ind\"tralia"));
	}
}
