package main.java.com.iteratrlearning.chapter03;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import main.java.com.iteratrlearning.chapter02.BankTransaction;

public class BankStatementProcessor {
	private final List<BankTransaction> bankTransactions;
	
	public void BankStatementProcessor() {};
	
	public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
		this.bankTransactions = bankTransactions;
	}
	
	public double calculateTotalAmount() {
		double total = 0;
		for(final BankTransaction bankTransaction : bankTransactions) {
			total += bankTransaction.getAmount();
		}
		return total;
	}
	
	public double calculateTotalInMonth(final Month month) {
		double total = 0;
		for(final BankTransaction bankTransaction : bankTransactions) {
			if(bankTransaction.getDate().getMonth() == month) {
				total += bankTransaction.getAmount();
			}
		}
		return total;
	}
	
	public double calculateTotalForCateogry(final String category) {
		double total = 0;
		for(final BankTransaction bankTransaction : bankTransactions) {
			if(bankTransaction.getDescription().equals(category)) {
				total += bankTransaction.getAmount();
			}
		}
		return total;
	}
	
	// 3-1. 특정 금액 이상의 은행 거래 내역 찾기
	public List<BankTransaction> findTransactionsGreaterThnaEqual(final int amount) {
		final List<BankTransaction> result = new ArrayList<>();
		for(final BankTransaction bankTransaction : bankTransactions) {
			if(bankTransaction.getAmount() >= amount) {
				result.add(bankTransaction);
			}
		}
		return result;
	}
	
	// 3-2. 특정 월의 입출금 내역 찾기
	public List<BankTransaction> findTransactionsInMonth(final Month month) {
		final List<BankTransaction> result = new ArrayList<>();
		for(final BankTransaction bankTransaction : bankTransactions) {
			if(bankTransaction.getDate().getMonth() == month) {
				result.add(bankTransaction);
			}
		}
		return result;
	}
	
	// 3-3. 특정 월이나 금액으로 입출금 내역 검색하기
	public List<BankTransaction> findTransactionsInMonthAndGreater(final Month month, final int amount) {
		final List<BankTransaction> result = new ArrayList<>();
		for(final BankTransaction bankTransaction : bankTransactions) {
			if(bankTransaction.getDate().getMonth() == month && bankTransaction.getAmount() >= amount) {
				result.add(bankTransaction);
			}
		}
		return result;
	}
	
	// 3-5. 개방/폐쇄 원칙을 적용한 후 유연해진 findTransactions() 메서드
	public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter) {
		final List<BankTransaction> result = new ArrayList<>();
		for(final BankTransaction bankTransaction : bankTransactions) {
			if(bankTransactionFilter.test(bankTransaction)) {
				result.add(bankTransaction);
			}
		}
		return result;
	}
}
