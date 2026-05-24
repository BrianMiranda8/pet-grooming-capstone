package org.example.repository;

import org.example.interfaces.TransactionRepository;
import org.example.models.Transaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CsvTransactionRepository implements TransactionRepository {
    String directory;
    public CsvTransactionRepository(String directory) {
        this.directory = directory;
    }
    @Override
    public void save(Transaction transaction) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd:hh:mm:ss");
        String filename = transaction.getTransactionDate().format(dtf);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(directory + filename+".txt", true))) {
            bw.write(transaction.toString());
        } catch ( IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
