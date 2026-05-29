package org.example.controllers;

import org.example.entities.AppointmentAddon;
import org.example.entities.AppointmentItem;
import org.example.frontend.ConsoleColors;
import org.example.frontend.UI;
import org.example.interfaces.ControllerInterface;
import org.example.models.Transaction;
import org.example.repository.CsvTransactionRepository;

import java.util.ArrayList;
import java.util.List;

public class CombosController implements ControllerInterface {
    private static boolean isLooping = true;

    @Override
    public void displayScreen() {
        CsvTransactionRepository csvTransactionRepository = new CsvTransactionRepository("receipts/");

        while (isLooping) {
            Transaction transaction = null;
            boolean isLooping = true;
            String username = "";
            String petName = "";
            String email = "";
            while (isLooping) {
                String title = UI.createTitle("Personal Information");

                UI.showMessage(title);
                username = UI.showPrompt("Name: ");
                if (username.isEmpty()) {
                    UI.showMessage("Not a valid name");
                    continue;
                }
                petName = UI.showPrompt("Pet Name: ");
                if (petName.isEmpty()) {
                    UI.showMessage("Not a valid pet name");
                    continue;
                }

                while(true) {
                    email = UI.showPrompt("Email: ");
                    if (email.isEmpty() || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                        UI.showMessage("Not a valid email");
                        continue;
                    }
                    //inner loop
                    break;
                }
                // outter loop
                break;
            }


            boolean packageLoop = true;
            while (packageLoop) {
                UI.padding();
                UI.padding();
                UI.showMessage(UI.createTitle("Select Premade Package"));
                UI.showMessage("Packages are final and cannot be changed");


                UI.showMessage(ConsoleColors.BLUE+"[1]"+ConsoleColors.RESET+" Basic Package");
                UI.showMessage(ConsoleColors.BLUE+"[2]"+ConsoleColors.RESET+" Royal Treatment");
                UI.showMessage(ConsoleColors.BLUE+"[3]"+ConsoleColors.RESET+" Premium Treatment");

                String userInput = UI.showPrompt("Select Package: ");
                switch (userInput) {
                    case "1" -> {
                        transaction = Transaction.createBasicRefresh(petName, username, email, "package", "universal");
                        packageLoop = false;
                    }
                    case "2" -> {
                        transaction = Transaction.createRoyalTreatment(petName, username, email, "package", "universal");
                        packageLoop = false;
                    }
                    case "3" -> {
                        transaction = Transaction.createPremiumTreatment(petName, username, email, "package", "universal");
                        packageLoop = false;
                    }
                    default -> {
                        UI.error("Enter Valid Package");
                    }
                }
            }
            UI.showMessage(transaction.toString());
           String userConfirmation = UI.showPrompt("Enter Prompt to continue or n to cancel: ");

           switch (userConfirmation){
               case "" -> {
                   csvTransactionRepository.save(transaction);
                  return;
               }
               default -> {
                   UI.showMessage("Goodbye");
                   return;
               }
           }

        }
    }


    private void exitCondition(String exit) {

    }
}
