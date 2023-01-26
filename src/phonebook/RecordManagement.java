package phonebook;

import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Scanner;

public class RecordManagement {

    private static RecordManagement instance = new RecordManagement();

    public static synchronized RecordManagement getInstance() {
        return instance;
    }
    private LinkedList<Record> recordList;

    private RecordManagement() {
        recordList = new LinkedList<>();
    }

    private int counter = 1;

    public LinkedList<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(LinkedList<Record> recordList) {
        this.recordList = recordList;
        counter = getMaxRecordId() + 1;
    }

    public int getMaxRecordId() {
        int maxId = 0;
        for (Record rec : getRecordList()) {
            if (rec.getId() > maxId) {
                maxId = rec.getId();
            }
        }
        return maxId;
    }

    public void addRecord(Record record){

        for( Record rec : recordList){
            if(rec.getPhoneNumber().equals(record.getPhoneNumber())) {
                System.out.println("Phone number already exists in phonebook!");
                return;
            }
        }

        record.setId(counter++);
        recordList.add(record);
    }

    public void deleteRecord(int removeId) {

        Record recordDeletion = null;

        for(Record rec : recordList){
            if(rec.getId() == removeId){
                recordDeletion = rec;
            }
        }

        if(recordDeletion == null){
            System.out.println("Invalid Record ID inserted");
        }
        else{
            recordList.remove(recordDeletion);
            System.out.println("Record removed successfully.");
        }
    }

    public void display() {

        if (recordList.isEmpty()) {
            System.out.println("Phonebook is Empty - please add a new record!\n");
        }
        for (Record record : recordList) {
            System.out.println(record.toString());
        }
    }

    public String searchRecordByName(String recName) {

        RecordManagement.getInstance().display();
        System.out.println("\n");
        System.out.println("enter Name of record you'd like to search for:");
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        recName = scanner.next();

        if (recName == null) {
            System.out.println("please enter a valid name");
        }

        Record foundRecord = null;
        for(Record rec : recordList){
            if(rec.getName().equalsIgnoreCase(recName)){
                System.out.println(rec);
                foundRecord = rec;
            }
        }
        return foundRecord.toString();
    }

    public Record getRecordByName(String name) {

        Record rec = new Record();
        Scanner input = new Scanner(System.in);
        System.out.println("Please insert NAME:");
        name = input.next();

        if (name == null) {
            System.out.println("please enter a valid name");
        }

        for(Record record : recordList){
            if(record.getName().equalsIgnoreCase(name)){
                rec = record;
            }
        } return rec;
    }


    public Record searchRecordById(int searchID){

        Record record = new Record();
        for(Record rec : recordList){
            if(rec.getId() == searchID){
                record = rec;

                break;
            }
        }
        return record;
    }

    public void updateRecord(Record record){

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("please insert ID number to update:");
        String input = scanner.next();
        while(Utils.hasSpecialCharsZeroToNine(input)){
            System.out.println("please insert id >>NUMBER<< to update:");
            input = scanner.next();
        }
//        System.out.println("Please insert ID number of record to update:");
        int inputInt = Integer.parseInt(input);
        record = searchRecordById(inputInt);

        if(record.getName() == null || record.getPhoneNumber() == null){
            System.out.println("Record DOES NOT EXIST - use only existing ID numbers");
            return;
        }

        System.out.println(record.toString());
        System.out.println("Choose one of the following options that you'd like to update:\n");
        System.out.println("1 - Update NAME / 2 - Update PHONE NUMBER ");
        String updateOption = scanner.next();

        switch(updateOption){

            case "1":
                System.out.println("Please enter new record's NAME:");
                String newName = scanner.next();
                if(Utils.hasSpecialChars(newName)){
                    System.out.println("Invalid name entered - please use ENGLISH LETTERS only!");
                }else{
                    record.setName(newName);
                    System.out.println("Record's name was set to " + newName);
                }

                break;

            case "2":
                System.out.println("Please enter new record's PHONE NUMBER:");
                String newPhoneNumber = scanner.next();

                if(Utils.hasSpecialCharsZeroToNine(newPhoneNumber)){
                    System.out.println("Invalid phone number entered - please use 0-9 numbers only!");
                }else{
                    record.setPhoneNumber(newPhoneNumber);
                    System.out.println("Record's phone number was set to " + newPhoneNumber);
                }

                break;

            default:
                System.out.println("No such option a**hole ;)");
        }

    }










}