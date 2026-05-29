package org.example.repository;

import org.example.entities.Addons;
import org.example.entities.AppointmentAddon;
import org.example.entities.AppointmentItem;
import org.example.models.Transaction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvTransactionRepositoryTest {


    @Test
    void create_file_from_transaction() {
        CsvTransactionRepository csvTransactionRepository = new CsvTransactionRepository("tests/");
        Transaction transaction = new Transaction();
        transaction.setOwnerEmail("brianmirandamontiel@gmail.com");
        transaction.setPetName("Clown");
        transaction.setOwnerName("Omar Miranda");
        transaction.setItems("1",new AppointmentItem("test", 10.0, 1));
        transaction.setItems("2",new AppointmentItem("tes 1", 10.0, 1));
        transaction.setAddons("3",new AppointmentAddon("test", 10.0, 1));
        transaction.setAddons("4",new AppointmentAddon("tes 2", 10.0, 1));
        IO.println(transaction);
//        csvTransactionRepository.save(transaction);


    }
}