package org.example.controllers;

import org.example.frontend.UI;
import org.example.interfaces.ControllerInterface;

public class MainController implements ControllerInterface {
    public MainController(){}

    public void displayScreen(){
        boolean mainLoop = true;
        AppointmentController appointmentController = new AppointmentController();
        while(mainLoop){
            UI.showMessage("Welcome To Hippie Hounds Pet Grooming");
            UI.showMessage("1) Start Appointment");
            UI.showMessage("2) End ");
            String response = UI.showPrompt("Enter your choice: ");

            switch (response){
                case "1" -> appointmentController.displayScreen();
                default -> UI.showMessage("Wrong choice");
            }

        }
    }
}
