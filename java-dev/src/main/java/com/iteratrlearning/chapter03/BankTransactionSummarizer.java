package main.java.com.iteratrlearning.chapter03;

import main.java.com.iteratrlearning.chapter02.BankTransaction;

@FunctionalInterface
public interface BankTransactionSummarizer {
    double summarize(double accumulator, BankTransaction bankTransaction);
}