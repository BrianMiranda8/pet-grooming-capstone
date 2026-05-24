package org.example.frontend;

import java.util.Scanner;

public class UI {
    private static Scanner scanner = new Scanner(System.in);


    public static String getUserInteraction(){
        return scanner.nextLine();
    }
    public static void showMessage(String message){
        System.out.println(message);
    }
}
