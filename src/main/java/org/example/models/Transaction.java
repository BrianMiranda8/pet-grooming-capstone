package org.example.models;

import org.example.entities.Addons;
import org.example.entities.ServiceItem;

import java.time.LocalDate;
import java.util.List;

public class Transaction {
    LocalDate transactionDate;
    String petName;
    String ownerName;
    String ownerEmail;
    List<Addons> addons;
    List<ServiceItem> items;

    public Transaction() {

    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public List<Addons> getAddons() {
        return addons;
    }

    public void setAddons(List<Addons> addons) {
        this.addons = addons;
    }

    public List<ServiceItem> getItems() {
        return items;
    }

    public void setItems(List<ServiceItem> items) {
        this.items = items;
    }
}
