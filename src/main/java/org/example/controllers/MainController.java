package org.example.controllers;

import org.example.frontend.UI;
import org.example.interfaces.ControllerInterface;

public class MainController implements ControllerInterface {
    public MainController() {
    }

    public void displayScreen() {
        boolean mainLoop = true;
        AppointmentController appointmentController = new AppointmentController();
        CombosController comboController = new CombosController();

        while (mainLoop) {

            UI.showMessage(UI.createTitle("Welcome To Hippie Hounds Pet Grooming!"));
            UI.showMessage("1) Build Your Appointment");
            UI.showMessage("2) Combo Appointments");
            UI.showMessage("3) Help");
            UI.showMessage("4) Cancel ");

            String response = UI.showPrompt("Enter your choice: ");
            switch (response) {
                case "1" -> appointmentController.displayScreen();
                case "2" -> comboController.displayScreen();
//                case "3" -> "";
                default -> UI.showMessage("Wrong choice");
            }

        }
    }

}
