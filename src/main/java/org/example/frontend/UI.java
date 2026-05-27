package org.example.frontend;

import java.util.Scanner;

public class UI {
    private static Scanner scanner = new Scanner(System.in);


    public static String getUserInteraction() {
        return scanner.nextLine();
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }

    public static String showPrompt(String message) {
        System.out.print(message);
       return getUserInteraction();
    }

    public static void padding(){
        UI.showMessage("");
    }

    public static String createTitle(String title){
        StringBuilder sb = new StringBuilder();
        sb.append("-".repeat(title.length() + 4)).append("\n");

        sb.append("| ").append(title).append(" |").append("\n");
        sb.append("-".repeat(title.length() + 4));


        return sb.toString();
    }
}
