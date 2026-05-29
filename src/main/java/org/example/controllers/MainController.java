package org.example.controllers;

import org.example.frontend.ConsoleColors;
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

            UI.showMessage(UI.createTitle("Welcome To The Dog Father!"));
            UI.showMessage(ConsoleColors.BLUE +"[1]" + ConsoleColors.RESET  + " Build Your Appointment" + ConsoleColors.RESET);
            UI.showMessage(ConsoleColors.BLUE +"[2]" + ConsoleColors.RESET  + " Combo Appointments" + ConsoleColors.RESET);
            UI.showMessage(ConsoleColors.BLUE +"[0]" + ConsoleColors.RESET  + " Cancel" + ConsoleColors.RESET);

            String response = UI.showPrompt("Enter your choice: ");
            switch (response) {
                case "1" -> appointmentController.displayScreen();
                case "2" -> comboController.displayScreen();
                case "0" -> mainLoop= false;
                default -> UI.error("Wrong choice");
            }

        }
    }

}
