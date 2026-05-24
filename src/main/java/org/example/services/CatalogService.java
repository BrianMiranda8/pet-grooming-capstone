package org.example.services;

import org.example.entities.AppointmentItem;
import org.example.entities.ServiceItem;
import org.example.interfaces.AnimalRepository;
import org.example.models.Catalog;


import java.util.List;


public class CatalogService {
    List<Catalog> catalogs;
    AnimalRepository animalRepository;

    public CatalogService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
        this.catalogs = this.animalRepository.getCatalogs();
    }

    public List<String> getCatalogNames() {
        return this.catalogs.stream()
                .map(Catalog::getPetType)
                .toList();
    }

    public List<Catalog> getCatalogs() {
        return this.catalogs;
    }

    public List<ServiceItem> getServiceItems(String petType) {
        return this.getCatalog(petType).getServices();
    }

    public List<String> getAvailableSizing(String petType) {
        return getCatalog(petType).getAvailableSizing();
    }

    public Catalog getCatalog(String petType) {

        // dont return null or else you create an anti pattern where your methods that call this would
        // crash a null pointer exception is not something you want to deal with instead give an error message
        // that return some type of information
        return this.catalogs.stream()
                .filter(c -> c.getPetType().equalsIgnoreCase(petType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No pet type found of type" + petType));
    }

    public AppointmentItem getServiceItem(String petType, String size, String name) {
        return this.getCatalog(petType).getServices().stream()
                .filter(si -> si.name().equals(name))
                .filter(si -> si.price().containsKey(size))
                .map(c -> new AppointmentItem(c.name(), c.price().get(size), 1))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No service item found for name '" + name + "' and size '" + size + "'"
                ));
    }

}
