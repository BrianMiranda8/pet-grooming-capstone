package org.example.controllers;

import org.example.entities.AppointmentItem;
import org.example.entities.ServiceItem;
import org.example.frontend.UI;
import org.example.interfaces.AnimalRepository;
import org.example.interfaces.ControllerInterface;
import org.example.models.Transaction;
import org.example.repository.JsonAnimalRepository;
import org.example.services.CatalogService;

import java.util.ArrayList;
import java.util.List;

public class AppointmentController implements ControllerInterface {
    boolean appointmentLoop = true;

    @Override
    public void displayScreen() {
        Transaction transaction = new Transaction();
        AnimalRepository animalRepository = new JsonAnimalRepository("animals.json");
        CatalogService catalogService = new CatalogService(animalRepository);

        while (appointmentLoop) {
            try {
                /**
                 * Get pet type
                 * Get pet size
                 * get user name
                 * get user email
                 * get user phone number
                 * get services
                 *  - when selecting a service dont display it again
                 * get add ons
                 * get
                 */
//                String petType = selectPetType(catalogService);
//                String petSize = selectPetSize(catalogService);

            } catch (NumberFormatException e) {
                UI.showMessage("Invalid input try again!");
            } catch (RuntimeException ex) {
                UI.showMessage(ex.getMessage());
            }
        }
    }

//    private String selectPetSize(CatalogService catalogService) {
//    }
//
//    private String selectPetType(CatalogService catalogService) {
//    }


    private void exitCondition(String condition) {
        String terminalCondition = "exit";

        if (terminalCondition.equalsIgnoreCase(condition)) {
            appointmentLoop = false;
        }

    }
}
