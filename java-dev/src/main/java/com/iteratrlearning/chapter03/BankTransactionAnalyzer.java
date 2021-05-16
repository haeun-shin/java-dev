package main.java.com.iteratrlearning.chapter03;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

import main.java.com.iteratrlearning.chapter02.BankStatementCSVParser;
import main.java.com.iteratrlearning.chapter02.BankTransaction;

public class BankTransactionAnalyzer {
	private static final String RESOURCES = "src/main/resources/";
	private static final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();
	
	public static void main(String[] args) throws IOException {
		final File files[] = new File(RESOURCES).listFiles();
		final String fileName = files[0].getName();
		final Path path = Paths.get(RESOURCES + fileName);
		final List<String> lines = Files.readAllLines(path);
		
		final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(lines);
		final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
		
		// 3-7. 특정 BankTransactionFilter 구현으로 findTransactions() 호출
//		final List<BankTransaction> transactions = bankStatementProcessor.findTransactions(new BankTransactionIsInFebruaryAndExpensive());
		
		// 3-8. 람다 표현식으로 BankTransactionFilter 구현하기
		final List<BankTransaction> transactions = bankStatementProcessor.findTransactions(bankTransaction -> 
				bankTransaction.getDate().getMonth() == Month.FEBRUARY && bankTransaction.getAmount() >= 1_000);
	}
}

// 2-6. BankTransactionFilter를 구현하는 클래스 선언
class BankTransactionIsInFebruaryAndExpensive implements BankTransactionFilter {
	@Override
	public boolean test(BankTransaction bankTransaction) {
		return bankTransaction.getDate().getMonth() == Month.FEBRUARY && bankTransaction.getAmount() >= 1000;
	}
}