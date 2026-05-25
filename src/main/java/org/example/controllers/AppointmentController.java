package org.example.controllers;

import org.example.entities.ServiceItem;
import org.example.frontend.UI;
import org.example.interfaces.AnimalRepository;
import org.example.interfaces.ControllerInterface;
import org.example.models.Transaction;
import org.example.repository.JsonAnimalRepository;
import org.example.services.CatalogService;

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
                //todo show exit message
                selectPet(catalogService, transaction);

                List<String> availableSizing = catalogService.getAvailableSizing(transaction.getPetType());

                Integer selectedSizing = selectPetSizing(availableSizing);
                
//                List<TransactionS> selectedServices = selectServices(catalogService, transaction, availableSizing);


            } catch (NumberFormatException e) {
                UI.showMessage("Invalid input try again!");
            } catch (RuntimeException ex) {
                UI.showMessage(ex.getMessage());
            }
        }
    }

    private static String selectServices(CatalogService catalogService, Transaction transaction, List<String> availableSizing) {
        UI.showMessage("");
        UI.showMessage("");

        UI.showMessage("Services:");

        List<ServiceItem> serviceItems = catalogService.getServiceItems(transaction.getPetType());
        for (int i = 0; i < serviceItems.size(); i++) {
            ServiceItem serviceItem = serviceItems.get(i);
            UI.showMessage(i + ") " + serviceItem.name() + " $" + serviceItem.price().get(availableSizing.get(i)));
        }

        return UI.showPrompt("Select Service: ");
    }

    private Integer selectPetSizing(List<String> availableSizing) {
        for (int i = 0; i < availableSizing.size(); i++) {
            UI.showMessage(i + ") " + availableSizing.get(i));
        }
        int petSizingResponse = Integer.parseInt(UI.showPrompt("Please select your sizing: "));
        if (petSizingResponse + 1 > availableSizing.size()) {
            throw new RuntimeException("Invalid Option");
        }
        return petSizingResponse;
    }

    private void selectPet(CatalogService catalogService, Transaction transaction) {
        List<String> options = catalogService.getCatalogNames();
        UI.showMessage("Select Your Pet");

        for (int i = 0; i < options.size(); i++) {
            UI.showMessage(i + ") " + options.get(i));
        }

        String animalTypeResponse = UI.showPrompt("Please select an option: ");
        exitCondition(animalTypeResponse);
        int animalType = Integer.parseInt(animalTypeResponse);
        transaction.setPetType(options.get(animalType));
    }

    private void exitCondition(String condition) {
        String terminalCondition = "exit";

        if (terminalCondition.equalsIgnoreCase(condition)) {
            appointmentLoop = false;
        }

    }
}
