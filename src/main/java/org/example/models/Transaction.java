package org.example.models;

import org.example.entities.*;

import java.security.KeyStoreException;
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
    HashMap<String, ExtraItem> extras = new HashMap<>();

    private Transaction(String petName, String ownerName, String ownerEmail, String petType, String petSizing) {
        this.petName = petName;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
        this.petType = petType;
        this.petSizing = petSizing;
    }

    public Transaction(String petName, String ownerName, String ownerEmail, HashMap<String,AppointmentAddon> addons, HashMap<String,AppointmentItem> items) {
        this.petName = petName;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
        this.addons = addons;
        this.items = items;

    }

    public Transaction() {
    }

    public static Transaction createBasicRefresh(String petName, String ownerName, String ownerEmail, String petType, String petSizing) {
        Transaction tx = new Transaction(petName, ownerName, ownerEmail, petType, petSizing);
        tx.items.put("SERV_BATH", new AppointmentItem("Bath & Brush", 30.00, 1));
        tx.addons.put("ADD_NAIL", new AppointmentAddon("Nail Trim", 10.00, 1));
        return tx;
    }

    public static Transaction createRoyalTreatment(String petName, String ownerName, String ownerEmail, String petType, String petSizing) {
        Transaction tx = new Transaction(petName, ownerName, ownerEmail, petType, petSizing);
        tx.items.put("SERV_CUT", new AppointmentItem("Hair Cut", 45.00, 1));
        tx.items.put("SERV_BATH", new AppointmentItem("Bath & Brush", 30.00, 1));
        tx.addons.put("ADD_TEETH", new AppointmentAddon("Teeth Brushing", 6.0, 1));
        tx.addons.put("ADD_EAR", new AppointmentAddon("Ear Cleaning", 0.0, 1));
        return tx;
    }

    public static Transaction createPremiumTreatment(String petName, String ownerName, String ownerEmail, String petType, String petSizing) {
        Transaction tx = new Transaction(petName, ownerName, ownerEmail, petType, petSizing);
        tx.items.put("SERV_DESHED", new AppointmentItem("Premium Bath", 50.00, 1));
        tx.items.put("SERV_BATH", new AppointmentItem("Bath & Brush", 30.00, 1));
        tx.addons.put("ADD_OATMEAL", new AppointmentAddon("Oatmeal Shampoo Upgrade", 0.0, 1));
        tx.addons.put("ADD_NAIL", new AppointmentAddon("Nail Trim", 0.0, 1));
        return tx;
    }



    public boolean containsItem(String name){
        return items.containsKey(name);
    }

    public boolean containsAddon(String name){
        return addons.containsKey(name);
    }
    public boolean containsExtra(String name) { return  extras.containsKey(name);}
    public ExtraItem getExtras(String id){return extras.get(id);}
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

    public void setExtra(String id, Extra extra){
        if (extras.containsKey(id)){
            ExtraItem storeExtra = extras.get(id);
            extras.put(id, new ExtraItem(extra.name(), extra.price(), storeExtra.quantity() + 1));
        }else{
            extras.put(id, new ExtraItem(extra.name(), extra.price(), 1));
        }
    }


    public void setAddons(String id, AppointmentAddon addon) {
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


    public void removeExtra(String id) throws KeyStoreException {
        if (!extras.containsKey(id)){
            throw new KeyStoreException("Key not found");
        }
        if (extras.get(id).quantity() == 1){
            extras.remove(id);
            return;
        }

        if (extras.get(id).quantity() > 1){
            ExtraItem current = extras.get(id);
            extras.put(id, new ExtraItem(current.name(),current.price(), current.quantity()-1));
        }


    }
    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

        StringBuilder title = new StringBuilder();
        StringBuilder sb = new StringBuilder();

        title.append("The DogFather Groomer== \n");
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
        sb.append("Extras: ").append("\n");
        this.extras.values().forEach(extra -> sb.append(extra.toString()));
        sb.append(String.format("%-34s %-15s","Total:","$"+getTotal() )).append("\n");

        return sb.toString();
    }

    public double getTotal() {
        double itemsTotal = this.items.values().stream()
                .mapToDouble(AppointmentItem::price)
                .sum();

        double addonsTotal = this.addons.values().stream()
                .mapToDouble(AppointmentAddon::price)
                .sum();

        double extrasTotal = this.extras.values().stream()
                .mapToDouble(x-> x.price() * x.quantity())
                .sum();

        return itemsTotal + addonsTotal + extrasTotal;
    }
}
