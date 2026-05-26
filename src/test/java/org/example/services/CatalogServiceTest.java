package org.example.services;

import org.example.entities.ServiceItem;
import org.example.interfaces.AnimalRepository;
import org.example.repository.JsonAnimalRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class CatalogServiceTest {
    // arrange
    private AnimalRepository jsonAnimalRepository = new JsonAnimalRepository("tests/test_animals.json");
    private CatalogService catalogService = new CatalogService(jsonAnimalRepository);

    @Test
    void get_catalog_pet_names() {

        // act
        List<String> animalTypes = catalogService.getCatalogNames();
        //assert
        Assertions.assertNotNull(animalTypes, "animalTypes should not be null");
        Assertions.assertFalse(animalTypes.isEmpty(), "animalTypes should not be empty");
        Assertions.assertEquals(2, animalTypes.size(), "animalTypes size should be 2");
        Assertions.assertEquals("Cat", animalTypes.get(1), "animalTypes should be Cat");
    }

    @Test
    void get_animal_catalog_by_name() {

        List<String> animalTypes = catalogService.getCatalogNames();

        Assertions.assertNotNull(animalTypes, "animalTypes should not be null");
        Assertions.assertEquals(2, animalTypes.size(), "animalTypes size should be 2");
        Assertions.assertEquals("Cat", animalTypes.get(1), "animalTypes should be Cat");
    }


    @Test
    void get_animal_catalog_services_by_name() {
        //act
        List<ServiceItem> serviceItems = catalogService.getServiceItems("Cat");

        // assert
        ServiceItem serviceItem = serviceItems.getFirst();
        Assertions.assertEquals("Hair Cut", serviceItem.name(), "serviceItems should contain De-shedding Treatment");
        serviceItems.forEach(si -> Assertions.assertNotNull(si, "serviceItems should not be null"));
    }

    @Test
    void get_service_item_by_name(){
        var appointmentItem = catalogService.getAppointmentItem("Cat", "small", "Hair Cut");

        Assertions.assertNotNull(appointmentItem, "appointmentItem should not be null");
        Assertions.assertEquals("Hair Cut", appointmentItem.name(), "serviceItems should contain hair cut");
//        Assertions.assertEquals(15.00, appointmentItem.price(), "serviceItems price should be 15.00");
    }
}