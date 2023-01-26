package phonebook;

import java.util.Random;
import java.util.Scanner;

public class Utils {

    private static Scanner scan = new Scanner(System.in);

    private static boolean hasSpecialChars_old(String name){

        for(int i = 0; i<name.length(); i++){

            if(name.charAt(i) > 64 && name.charAt(i) < 123){
                return true;
            }
        } return false;
    }


    public static boolean hasSpecialChars(String input) {
        boolean hasSpecialChars = false;
        for (char c : input.toCharArray()) {
            if (c < 65 || c > 122) {
                if (c != ' ') {
                    hasSpecialChars = true;
                    break;
                }
            }
        }
        return hasSpecialChars;
    }

    public static boolean hasSpecialCharsZeroToNine(String input) {
        boolean hasSpecialChars = false;
        for (char c : input.toCharArray()) {
            if (c < 48 || c > 57) {
                hasSpecialChars = true;
                break;
            }
        }
        return hasSpecialChars;
    }

    public static void title(){

        System.out.println("  _____  _                      ____              _                __  \n" +
                " |  __ \\| |                    |  _ \\            | |           _   \\ \\ \n" +
                " | |__) | |__   ___  _ __   ___| |_) | ___   ___ | | __       (_)   | |\n" +
                " |  ___/| '_ \\ / _ \\| '_ \\ / _ |  _ < / _ \\ / _ \\| |/ /             | |\n" +
                " | |    | | | | (_) | | | |  __| |_) | (_) | (_) |   <         _    | |\n" +
                " |_|    |_| |_|\\___/|_| |_|\\___|____/ \\___/ \\___/|_|\\_\\       (_)   | |\n" +
                "                                                                   /_/ \n" +
                "                                                                       ");
    }

    public static String listOfStrings(){

        Random random = new Random();
        String[] names = new String[]{"Led Zeppelin", "Black Sabbath", "Pink Floyd", "Rolling Stones", "Jimi Hendrix" };
        int randomIndex = random.nextInt(names.length);


        return names[randomIndex];
    }

//    public static void showMainMenu() {
//        String mainMenuText = "Phonebook's Menu - please choose one of the following options:\n";
//        String[] mainMenuOpts = new String[] {"Add Record", "Delete Record", "Update Record", "Show ALL Records", "Search Record", "Exit Program :("};
//        int selectedOption = showTextAndGetOption(mainMenuText, mainMenuOpts);
//        System.out.println(String.format("Launching %d -- %s", selectedOption, mainMenuOpts[selectedOption - 1]));
//    }
//
//    private static int showTextAndGetOption(String text, String[] options) {
//        System.out.println(text);
//        int optIndex = 1;
//        for (String opt : options) {
//            String line = String.format("%d - %s", optIndex, opt);
//            System.out.println(line);
//            optIndex++;
//        }
//        int selectedOption = scan.nextInt();
//        return selectedOption;
//    }


}
