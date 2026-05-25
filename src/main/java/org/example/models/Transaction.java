package org.example.models;

import org.example.entities.Addons;
import org.example.entities.AppointmentAddon;
import org.example.entities.AppointmentItem;
import org.example.entities.ServiceItem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    LocalDateTime transactionDate = LocalDateTime.now();
    String petType;
    String petName;
    String ownerName;
    String ownerEmail;
    List<AppointmentAddon> addons = new ArrayList<>();
    List<AppointmentItem> items = new ArrayList<>();
    double totalAmount;

    public Transaction(String petName, String ownerName, String ownerEmail, List<AppointmentAddon> addons, List<AppointmentItem> items) {
        this.petName = petName;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
        this.addons = addons;
        this.items = items;

    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public Transaction() {
    }


    public LocalDateTime getTransactionDate() {
        return transactionDate;
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

    public List<AppointmentAddon> getAddons() {
        return addons;
    }

    public void setAddons(AppointmentAddon addon) {
        // todo fix this to add quantity
        this.addons.add(addon);
        this.totalAmount += this.addons.stream()
                .mapToDouble(AppointmentAddon::price).sum();
    }

    public List<AppointmentItem> getItems() {
        return items;
    }

    public void setItems(AppointmentItem item) {
        this.items.add(item);
        this.totalAmount += this.items.stream()
                .mapToDouble(AppointmentItem::price).sum();
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

        StringBuilder title = new StringBuilder();
        StringBuilder sb = new StringBuilder();

        title.append("Hippy Hounds Receipt \n");
        title.append("----------------------\n");
        title.append(transactionDate.format(dtf)).append("\n");
        sb.append(petName).append(" | ");
        sb.append(ownerName).append(" | ");
        sb.append(ownerEmail);
        sb.append("\n").append("-".repeat(sb.toString().length())).append("\n");

        sb.insert(0, title);
        sb.append("Services: ").append("\n");
        this.items.forEach(item -> sb.append(item.toString()));
        sb.append("Addons: ").append("\n");
        this.addons.forEach(addon -> sb.append(addon.toString()));
        sb.append("Total:").append(" ".repeat(10)).append(totalAmount).append("\n");

        return sb.toString();
    }
}
