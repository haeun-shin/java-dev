package main.java.com.iteratrlearning.chapter02.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Assert;
import org.junit.Test;

import main.java.com.iteratrlearning.chapter02.BankStatementCSVParser;
import main.java.com.iteratrlearning.chapter02.BankTransaction;

public class BankStatementCSVParserTest {
	// 2-15. 어서션 구문 사용하기
	private final BankStatementCSVParser statementParser = new BankStatementCSVParser();
	@Test
	public void shouldParseOneCorrectLine() throws Exception {
		final String line = "30-01-2017,-50,Tesco";
		
		final BankTransaction result = statementParser.parseFromCSV(line);
		
		final BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");
		final double tolerance = 0.0d;
		
		Assert.assertEquals(expected.getDate(), result.getDate());
		Assert.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
		Assert.assertEquals(expected.getDescription(), result.getDescription());
	}

}