package org.example.models;

import org.example.entities.Addons;
import org.example.entities.Extra;
import org.example.entities.ServiceItem;

import java.util.List;

public class Catalog {
    String petType;
    List<ServiceItem> services;
    List<Addons> addons;
    List<String> availableSizing;
    List<Extra> extras;


    Catalog() {
    }

    public List<Extra> getExtras() {
        return extras;
    }

    public void setExtras(List<Extra> extras) {
        this.extras = extras;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public List<ServiceItem> getServices() {
        return services;
    }

    public void setServices(List<ServiceItem> services) {
        this.services = services;
    }

    public List<Addons> getAddons() {
        return addons;
    }

    public void setAddons(List<Addons> addons) {
        this.addons = addons;
    }

    public List<String> getAvailableSizing() {
        return availableSizing;
    }

    public void setAvailableSizing(List<String> availableSizing) {
        this.availableSizing = availableSizing;
    }
}
