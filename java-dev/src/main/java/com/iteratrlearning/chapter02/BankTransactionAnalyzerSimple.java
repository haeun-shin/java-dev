package main.java.com.iteratrlearning.chapter02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class BankTransactionAnalyzerSimple {
	private static final String RESOURCES = "src/main/resources/";

	public static void main(final String... args) throws IOException {
		// 2-1. 모든 거래 내역의 합 계산하기
//		final File files[] = new File(RESOURCES).listFiles();
//		final Path path = Paths.get(files[0].toString());
//		final List<String> lines = Files.readAllLines(path);
//		
//		double total = 0d;
//		for(final String line : lines) {
//			final String[] columns = line.split(",");
//			final double amount = Double.parseDouble(columns[1]);
//			total += amount;
//		}
//		
//		System.out.println("The total for all transactions is " + total);
		
		// 2-2. 1월 입출금 내역 합계 계산하기
//		final File files[] = new File(RESOURCES).listFiles();
//		final Path path = Paths.get(files[0].toString());
//		final List<String> lines = Files.readAllLines(path);
//		double total = 0d;
//		final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//		for(final String line : lines) {
//			final String[] columns = line.split(",");
//			final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
//			if(date.getMonth() == Month.JANUARY) {
//				final double amount = Double.parseDouble(columns[1]);
//				total += amount;
//			}
//		}
//		
//		System.out.println("The total for all transactions in January is " + total);
		// 2-5. 입출금 내역 CSV 파서 사용하기
		final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();
		final File files[] = new File(RESOURCES).listFiles();
		final String fileName = files[0].getName();
		final Path path = Paths.get(RESOURCES + fileName);
		final List<String> lines = Files.readAllLines(path);
		
		final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(lines);
		
		System.out.println("The total for all transactions is " + calculateTotalAmount(bankTransactions));
		System.out.println("Transactions in January " + selectInMonth(bankTransactions, Month.JANUARY));
	}
	
	public static double calculateTotalAmount(final List<BankTransaction> bankTransactions) {
		double total = 0d;
		for(final BankTransaction bankTransaction : bankTransactions) {
			total += bankTransaction.getAmount();
		}
		return total;
	}
	
	public static List<BankTransaction> selectInMonth(final List<BankTransaction> bankTransactions, final Month month) {
		final List<BankTransaction> bankTransactionsInMonth = new ArrayList<>();
		for(final BankTransaction bankTransaction : bankTransactions) {
			if(bankTransaction.getDate().getMonth() == month) {
				bankTransactionsInMonth.add(bankTransaction);
			}
		}
		return bankTransactionsInMonth;
	}
}
