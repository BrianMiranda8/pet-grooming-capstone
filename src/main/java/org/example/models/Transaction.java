package org.example.models;

import org.example.entities.Addons;
import org.example.entities.AppointmentAddon;
import org.example.entities.AppointmentItem;
import org.example.entities.ServiceItem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transaction {
    LocalDateTime transactionDate = LocalDateTime.now();
    String petType;
    String petName;
    String ownerName;
    String ownerEmail;
    String petSizing;
    HashMap<String, AppointmentAddon> addons = new HashMap<>();
    HashMap<String, AppointmentItem> items = new HashMap<>();

    public Transaction(String petName, String ownerName, String ownerEmail, HashMap<String,AppointmentAddon> addons, HashMap<String,AppointmentItem> items) {
        this.petName = petName;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
        this.addons = addons;
        this.items = items;

    }

    public boolean containsItem(String name){
        return items.containsKey(name);
    }

    public boolean containsAddon(String name){
        return addons.containsKey(name);
    }

    public String getPetSizing() {
        return petSizing;
    }

    public void setPetSizing(String petSizing) {
        this.petSizing = petSizing;
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



    public void setPetName(String petName) {
        this.petName = petName;
    }


    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }


    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }


    public void setAddons(String id, AppointmentAddon addon) {
        // todo fix this to add quantity
        this.addons.put(id,addon);

    }

    public List<AppointmentItem> getItems() {
        return items.values().stream().toList();
    }

    public void setItems(String id, AppointmentItem item) {
        this.items.put(id, item);
    }

    public void removeItem(String id){
        this.items.remove(id);
    }
    public void removeAddon(String id){
        this.addons.remove(id);
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
        this.items.values().forEach(item -> sb.append(item.toString()));
        sb.append("Addons: ").append("\n");
        this.addons.values().forEach(addon -> sb.append(addon.toString()));
        sb.append(String.format("%-34s %10.2f","Total:",getTotal() )).append("\n");

        return sb.toString();
    }

    public double getTotal() {
        double itemsTotal = this.items.values().stream()
                .mapToDouble(AppointmentItem::price)
                .sum();

        double addonsTotal = this.addons.values().stream()
                .mapToDouble(AppointmentAddon::price)
                .sum();

        return itemsTotal + addonsTotal;
    }
}
