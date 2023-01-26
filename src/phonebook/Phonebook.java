package phonebook;

import java.util.LinkedList;
import java.util.Scanner;

public class Phonebook {
    public void runPhonebook() {

        Utils.title();

        RecordManagement recordManagement = RecordManagement.getInstance();
        RecordWriter recordWriter = new RecordWriter();
        recordWriter.readFromFile(recordManagement);
        Scanner scan = new Scanner(System.in).useDelimiter("\n");

        LinkedList<Record> LIST = recordManagement.getRecordList();

        String option = null;


        do {
            menu();
            option = scan.next();

            switch (option) {

                //add new record
                case "1":

                    Record newRec = new Record();
                    newRec.setName(askInput(scan, "Please insert new record NAME:"));
                    while (Utils.hasSpecialChars(newRec.getName())) {
                        newRec.setName(askInput(scan, "Please insert new record NAME using ENGLISH LETTERS ONLY!:"));
                    }
                    newRec.setPhoneNumber(askInput(scan, "Please insert new record PHONE NUMBER:"));
                    while (Utils.hasSpecialCharsZeroToNine(newRec.getPhoneNumber())) {
                        newRec.setPhoneNumber(askInput(scan, "Please insert new record PHONE NUMBER using NUMBERS 0-9 ONLY!"));
                    }
                    recordManagement.addRecord(newRec);

                    System.out.println("\n" + LIST);

                    break;

                //remove record
                case "2":

                    System.out.println("Insert record ID that you want to delete: \n");
//                    int removeId = scan.nextInt();
                    String removeId = scan.next();

                    while (Utils.hasSpecialCharsZeroToNine(removeId) || removeId.equalsIgnoreCase("")) {
                        System.out.println("please insert ID with numbers 0-9 ONLY");
                        removeId = scan.next();
                    }

                    int removeIdInt = Integer.parseInt(removeId);
                    recordManagement.deleteRecord(removeIdInt);
                    break;

                //Display all records
                case "3":

                    recordManagement.display();
                    break;

                //Update record
                case "4":

                    Record record = new Record();
                    recordManagement.updateRecord(record);
                    break;

                //Search record by name
                case "5":

                    String recName = null;
                    recordManagement.searchRecordByName(recName);
                    break;

                //Terminate program
                case "9":

                    recordWriter.writeToFile(recordManagement.getRecordList());
                    System.out.println("\nPhonebook TERMINATED. see you again next time :)\n");
                    System.exit(0);
                    break;

                default:

                    System.out.println("Unidentified option chosen - please choose a VALID option!\n");
                    break;
            }
        }

        while (option != "9");
    }

    public static void menu() {

        System.out.println("\nPhonebook's Menu - please choose one of the following options:\n");
        System.out.println("1. Add Record");
        System.out.println("2. Delete Record");
        System.out.println("3. Show All Records");
        System.out.println("4. Update Existing Record");
        System.out.println("5. Search Record by Name");
        System.out.println("9. Exit Program :(\n");
    }

    public static String askInput(Scanner scan, String myAsk) {
        String input = "";
        while (input.isEmpty()) {
            System.out.println(myAsk);
            input = scan.next();
        }

        return input;
    }
}

