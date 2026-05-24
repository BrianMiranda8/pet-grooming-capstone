package org.example.managers;

import org.example.interfaces.AnimalRepository;
import org.example.models.Catalog;
import org.example.repository.JsonAnimalRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class JsonAnimalRepositoryTest {


    @Test
    void parseAnimal() {
        // arrange
        AnimalRepository jsonParser = new JsonAnimalRepository("tests/test_animals.json");
        List<Catalog> catalog = jsonParser.getCatalogs();
        // act
        Catalog animal = catalog.getFirst();
        // assert

        Assertions.assertNotNull(animal);
        Assertions.assertEquals("Dog", animal.getPetType(), "Animal type not correct");

    }

}