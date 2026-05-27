package org.example.controllers;

import org.example.entities.AppointmentItem;
import org.example.interfaces.ControllerInterface;
import org.example.models.Transaction;

import java.util.List;

public class CombosController implements ControllerInterface {
    private static boolean isLooping = false;
    @Override
    public void displayScreen() {
        Transaction transaction = new Transaction();
        while (isLooping){

        }
    }

    private void exitCondition(String exit){

    }
}
