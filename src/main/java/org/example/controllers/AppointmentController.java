package org.example.controllers;

import org.example.entities.Addons;
import org.example.entities.AppointmentAddon;
import org.example.entities.AppointmentItem;
import org.example.entities.ServiceItem;
import org.example.frontend.ConsoleColors;
import org.example.frontend.UI;
import org.example.interfaces.AnimalRepository;
import org.example.interfaces.ControllerInterface;
import org.example.interfaces.TransactionRepository;
import org.example.models.Transaction;
import org.example.repository.CsvTransactionRepository;
import org.example.repository.JsonAnimalRepository;
import org.example.services.CatalogService;

import java.util.List;

public class AppointmentController implements ControllerInterface {
    boolean appointmentLoop = true;

    @Override
    public void displayScreen() {
        appointmentLoop = true;
        AnimalRepository animalRepository = new JsonAnimalRepository("animals.json");
        CatalogService catalogService = new CatalogService(animalRepository);
        TransactionRepository transactionRepository = new CsvTransactionRepository("receipts/");
        while (appointmentLoop) {
            try {

                Transaction transaction = new Transaction();

                getPersonalInfo(transaction);
                getPetType(catalogService, transaction);
                getPetSize(catalogService, transaction);

                List<ServiceItem> availableServiceItems = catalogService.getServiceItems(transaction.getPetType());
                List<Addons> availableAddOns = catalogService.getAddOnList(transaction.getPetType());

                getServices(availableServiceItems, transaction, catalogService);
                getAddons(availableAddOns, transaction, catalogService);

                checkoutScreen(transaction, transactionRepository);


            } catch (NumberFormatException e) {
                UI.showMessage("Invalid input try again!");
            } catch (RuntimeException ex) {
                UI.showMessage(ex.getMessage());
            }
        }
    }

    private void checkoutScreen(Transaction transaction, TransactionRepository transactionRepository) {
        boolean exitCheckout = true;
        while (exitCheckout) {
            UI.padding();
            UI.padding();
            UI.showMessage(UI.createTitle("Checkout"));
            UI.showMessage(ConsoleColors.BLUE+ "["+ "1]"+ConsoleColors.RESET+" Checkout");
            UI.showMessage(ConsoleColors.BLUE+ "["+ "2]"+ConsoleColors.RESET+" View Cart");
            UI.showMessage(ConsoleColors.BLUE+ "["+ "3]"+ConsoleColors.RESET+" Remove Items");
            UI.showMessage(ConsoleColors.BLUE+ "["+ "3]"+ConsoleColors.RESET+" Cancel");

            String userInput = UI.showPrompt("Select Option: ");
            if (userInput.equals("1")) {
                checkout(transaction, transactionRepository);
                UI.showMessage("Thank You For Trusting Us With Your Pet!");
                UI.showPrompt("Press Enter To Continue: ");
                exitCheckout = false;
            } else if (userInput.equals("2")) {
                UI.padding();
                showCart(transaction);
                UI.showPrompt("Press Enter To Continue: ");
            } else if (userInput.isEmpty()) {
                UI.showMessage("Enter Selection");
            } else {
                exitCheckout = false;
            }
        }
        exitCondition("exit");
    }


    private void getPersonalInfo(Transaction transaction) {
        boolean isLooping = true;
        while (isLooping) {
            String title = UI.createTitle("Personal Information");
            UI.padding();
            UI.padding();
            UI.showMessage(title);
            String username = UI.showPrompt("Name: ");
            if (username.isEmpty()) {
                UI.error("Not a valid name");
                continue;
            }
            String petName = UI.showPrompt("Pet Name: ");
            if (petName.isEmpty()) {
                UI.error("Not a valid pet name");
                continue;
            }

            while(true) {
                String email = UI.showPrompt("Email: ");
                if (email.isEmpty() || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                    UI.error("Not a valid email");
                    continue;
                }
                transaction.setOwnerEmail(email);

                break;
            }
            transaction.setOwnerName(username);
            transaction.setPetName(petName);
            break;
        }
    }

    private void checkout(Transaction transaction, TransactionRepository csvTransactionRepository) {
        UI.showMessage(transaction.toString());
        String userInput = UI.showPrompt("Would You like to continue or cancel? (y/n) ");
        switch (userInput) {
            case "y" -> csvTransactionRepository.save(transaction);
            case "n" -> exitCondition("exit");
            default -> UI.showMessage("Try again");
        }
    }

    public void showCart(Transaction transaction) {
        UI.showMessage(transaction.toString());
    }

    private void getAddons(List<Addons> addonsList, Transaction transaction, CatalogService catalogService) {
        while (true) {
            UI.padding();

            UI.showMessage(UI.createTitle("Select Add Ons"));
            UI.showMessage( "Press Enter When done or if you dont need add Ons");
            UI.showMessage(ConsoleColors.BG_RED+ ConsoleColors.BLACK + "Add a - sign to remove add on EX -0,-1" + ConsoleColors.RESET);
            for (int i = 0; i < addonsList.size(); i++) {
                Addons addon = addonsList.get(i);
                String selected = "";
                if (transaction.containsAddon(Integer.toString(i))) {
                    selected = ConsoleColors.GREEN + "(Selected)"+ ConsoleColors.RESET;
                }
                String output = String.format(ConsoleColors.BLUE+ "[%d]"+ ConsoleColors.RESET+ " %-35s $%-5.2f %-5s", i, addon.name(),addon.price(),selected );
                UI.showMessage(output);

            }

            String userInput = UI.showPrompt("Select Add ons: ");

            if (userInput.isEmpty()) {

                break;

            }

            int index = Integer.parseInt(userInput);


            if (index > catalogService.getAddOnList(transaction.getPetType()).size()) {
                UI.showMessage(ConsoleColors.BG_RED+ConsoleColors.WHITE + "Invalid service item" + ConsoleColors.RESET);
                continue;
            }

            if (index > 0 && transaction.containsAddon(catalogService.getCatalog(transaction.getPetType()).getAddons().get(Math.abs(index)).name())) {
                UI.showMessage(ConsoleColors.BG_RED+ConsoleColors.WHITE + "Service item already exists if you wish to delete pass it as a negative number EX: -1 " + ConsoleColors.RESET);
                continue;
            }


            // todo change this
            if (index < 0 || userInput.equals("-0")) {

                transaction.removeAddon(Integer.toString(index));

                UI.padding();
                UI.showMessage("Removed Add");

                UI.padding();

            } else if (index >= 0) {

                AppointmentAddon appointmentAddon = catalogService.getAppointmentAddon(transaction.getPetType(), catalogService.getAddOnList(transaction.getPetType()).get(index).name());
                transaction.setAddons(userInput, appointmentAddon);

            }

        }
    }

    private void getServices(List<ServiceItem> serviceItems, Transaction transaction, CatalogService catalogService) {
        while (true) {
            UI.padding();
            UI.padding();
            UI.showMessage(UI.createTitle("Select Services"));
            UI.showMessage( "Press Enter When You are done selecting services");
            UI.showMessage(ConsoleColors.BG_RED+ ConsoleColors.BLACK + "Add a - sign to remove selection EX -0,-1" + ConsoleColors.RESET);


            for (int i = 0; i < serviceItems.size(); i++) {

                String selected = "";
                ServiceItem serviceItem = serviceItems.get(i);
                if (transaction.containsItem(Integer.toString(i))) {
                    selected = ConsoleColors.GREEN + "(Selected)"+ ConsoleColors.RESET;
                }
                String output = String.format(ConsoleColors.BLUE+ "[%d]"+ ConsoleColors.RESET+ " %-35s $%-5.2f %-5s", i, serviceItem.name(),serviceItem.price().get(transaction.getPetSizing()),selected );
                UI.showMessage(output);
            }

            String userInput = UI.showPrompt("Select service item: ");
            exitCondition(userInput);
            if (userInput.isEmpty() && !transaction.getItems().isEmpty()) {
                // continue condition. If user input is empty and they have items selected
                break;

            } else if (userInput.isEmpty() && transaction.getItems().isEmpty()) {

                UI.showMessage("");
                UI.showMessage("Please Select a service item");
                continue;
            }

            int serviceItemId = Integer.parseInt(userInput);

            if (serviceItemId > catalogService.getServiceItems(transaction.getPetType()).size()) {
                UI.showMessage("Invalid service item");
                continue;
            }

            if (serviceItemId > 0 && transaction.containsItem(catalogService.getCatalog(transaction.getPetType()).getServices().get(Math.abs(serviceItemId)).name())) {
                UI.showMessage("Service item already exists if you wish to delete pass it as a negative number EX: -1 ");
                continue;
            }


            if (serviceItemId < 0 || userInput.equals("-0") ) {
                transaction.removeItem(Integer.toString(serviceItemId));
            } else if (serviceItemId >= 0) {
                AppointmentItem appointmentItem = catalogService.getAppointmentItem(transaction.getPetType(), transaction.getPetSizing(), serviceItemId);
                transaction.setItems(userInput, appointmentItem);

            }

        }
    }

    private void getPetSize(CatalogService catalogService, Transaction transaction) {

        while (true) {
            UI.padding();
            UI.padding();
            String title = UI.createTitle("Select Pet Size");
            UI.showMessage(title);

            List<String> petSizelList = catalogService.getAvailableSizing(transaction.getPetType());
            for (int i = 0; i < petSizelList.size(); i++) {
                UI.showMessage(ConsoleColors.BLUE+"[" +i + "] "+ ConsoleColors.RESET + petSizelList.get(i));
            }
            try {
                String userInput = UI.showPrompt("Select Pet Size: ");
                exitCondition(userInput);
                String petSize = petSizelList.get(Integer.parseInt(userInput));

                transaction.setPetSizing(petSize);
                break;
            } catch (RuntimeException e) {
                UI.showMessage("Error try again");
            }
        }
    }

    private void getPetType(CatalogService catalogService, Transaction transaction) {
        while (true) {
            UI.padding();
            UI.padding();
            String title = UI.createTitle("Please select a pet type");
            UI.showMessage(title);

            List<String> petTypes = catalogService.getCatalogNames();

            for (int i = 0; i < petTypes.size(); i++) {
                UI.showMessage(ConsoleColors.BLUE+"["+i + "] "+ ConsoleColors.RESET + petTypes.get(i));
            }

            try {
                String userInput = UI.showPrompt("Enter Selection: ");
                exitCondition(userInput);
                int petTypeIndex = Integer.parseInt(userInput);

                String petType = catalogService.getPetType(petTypeIndex);
                transaction.setPetType(petType);
                break;

            } catch (RuntimeException e) {
                UI.showMessage("Wrong selection");
            }
        }
    }

    private void exitCondition(String condition) {
        String terminalCondition = "exit";

        if (terminalCondition.equalsIgnoreCase(condition)) {
            appointmentLoop = false;
        }


    }
}
