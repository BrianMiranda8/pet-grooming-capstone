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




    public static  void error(String text){
        System.out.println(ConsoleColors.RED + text + ConsoleColors.RESET);
    }

    public static String showPrompt(String message) {
        System.out.print(ConsoleColors.BLUE + message + ConsoleColors.RESET);
       return getUserInteraction();
    }

    public static void padding(){
        UI.showMessage("");
    }

    public static String createTitle(String title){
        StringBuilder sb = new StringBuilder();
        sb.append(ConsoleColors.BLUE).append("-".repeat(title.length() + 4)).append(ConsoleColors.RESET).append("\n");

        sb.append(ConsoleColors.BLUE).append("| ").append(ConsoleColors.RESET).append(ConsoleColors.RED).append(title).append(ConsoleColors.RESET).append(ConsoleColors.BLUE).append(" |").append(ConsoleColors.RESET).append("\n");
        sb.append(ConsoleColors.BLUE).append("-".repeat(title.length() + 4)).append(ConsoleColors.RESET).append("\n");


        return sb.toString();
    }
}
