package main.java.com.iteratrlearning.chapter02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionAnalyzer {
	private static final String RESOURCES = "src/main/resources/";
	private static final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();
	
	public static void main(String[] args) throws IOException {
		// 2-5. 입출금 내역 CSV 파서 사용하기
		final File files[] = new File(RESOURCES).listFiles();
		final String fileName = files[0].getName();
		final Path path = Paths.get(RESOURCES + fileName);
		final List<String> lines = Files.readAllLines(path);
		
		final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(lines);
		final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
		
		collectSummary(bankStatementProcessor);
	}
	
	private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {
		System.out.println("The total for all transactions is " + bankStatementProcessor.calculateTotalAmount());
		System.out.println("The total for all transactions in January is " + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
		System.out.println("The total for all transactions in February is " + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));
		System.out.println("The total salary received is " + bankStatementProcessor.calculateTotalForCateogry("Salary"));
	}
	
}
