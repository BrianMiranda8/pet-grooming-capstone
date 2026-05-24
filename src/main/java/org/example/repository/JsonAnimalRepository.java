package org.example.repository;

import org.example.interfaces.AnimalRepository;
import org.example.models.Catalog;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;


public class JsonAnimalRepository implements AnimalRepository {
    List<Catalog> catalogs;

    @Override
    public List<Catalog> getCatalogs() {
        return catalogs;
    }

    public JsonAnimalRepository(String fileName){
        try{
            var jsonFile = JsonAnimalRepository.class.getClassLoader().getResourceAsStream(fileName);
            JsonMapper mapper =  JsonMapper.builder().build();

            List<Catalog> catalog = mapper.readValue(jsonFile, new TypeReference<List<Catalog>>(){});

            this.catalogs = catalog;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
