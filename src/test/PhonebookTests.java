package test;

import org.junit.jupiter.api.*;
import phonebook.Record;
import phonebook.RecordManagement;
import phonebook.RecordWriter;
import phonebook.Utils;

public class PhonebookTests {

    private static boolean runAfterEach = false;
    private static RecordManagement rm = RecordManagement.getInstance();
    private static String fileBackup = "";
    private static RecordWriter recordWriter;

    @BeforeAll
    public static void init() {
        recordWriter = new RecordWriter();
        recordWriter.readFromFile(rm);
        System.out.println("Ran init");
        fileBackup = recordWriter.getFileString();
    }

    @AfterAll
    public static void tearDown() {
        recordWriter.overwriteFile(fileBackup);
        System.out.println("Finished teardown");
    }

    @AfterEach
    public void printList() {
        if (runAfterEach) {
            System.out.println("Current RecordList: \n===============");
            rm.display();
            System.out.println("------------------");
        }
    }

    @Test
    public void test() {
        System.out.println("Herro");
        boolean tCondition = 1 < 2;
        boolean fCondition = 1 > 2;
        Assertions.assertTrue(tCondition);
        Assertions.assertFalse(fCondition);
    }

    @Test
    public void test02() {
        System.out.println("Herro again");
        Assertions.assertSame("A", "A");
    }


    @Test
    public void addRecordTest() {
        int maxId = rm.getMaxRecordId() + 1;
        String testName = "TEST TESTING";
        String testPhone = "0123456789";
        Record record = new Record(testName, maxId, testPhone);
        rm.addRecord(record);
        System.out.println("Added test record: " + record);
        Record foundRecord = rm.searchRecordById(maxId);
        Assertions.assertTrue(foundRecord != null);

        System.out.println("Found test record: " + foundRecord);
        int id = foundRecord.getId();
        String name = foundRecord.getName();
        String phone = foundRecord.getPhoneNumber();

        Assertions.assertEquals(maxId, id);
        Assertions.assertEquals(testName, name);
        Assertions.assertEquals(testPhone, phone);
    }

    @Test
    public void failOnAddExistingPhoneNumber() {
        String name = "TEST TESTING TEST";
        int id = rm.getMaxRecordId() + 1;
        String phone = "";
        int existingRecId = -1;
        for (Record record : rm.getRecordList()) {
            if (phone.isEmpty() && !record.getPhoneNumber().isEmpty()) {
                phone = record.getPhoneNumber();
                existingRecId = record.getId();
                System.out.println("Fetched existing phone: " + phone + " (with ID " + existingRecId + ")");
                break;
            }
        }

        Record testRecord = new Record(name, id, phone);
        System.out.println("Trying to add record: " + testRecord);
        rm.addRecord(testRecord);

        Assertions.assertNotSame(existingRecId, id);
        boolean foundMultiplePhone = false;
        for (Record rec : rm.getRecordList()) {
            if (!(rec.getId() == existingRecId)) {
                if (rec.getPhoneNumber().equalsIgnoreCase(phone)) {
                    foundMultiplePhone = true;
                    System.out.println("Found record with phone: " + phone + " and ID " + rec.getId() + " (pre-existing: " + existingRecId + ")");
                    break;
                }
            }
        }
        Assertions.assertFalse(foundMultiplePhone);
    }

    @Test
    public void failOnAddExistingRecordId() {
        String name = "TEST TESTING";
        int existingId = rm.getMaxRecordId();
        String phone = "98124978249061";

        Record testRecord = new Record(name, existingId, phone);
        rm.addRecord(testRecord);

        Record existingRecord = rm.searchRecordById(existingId);
        Assertions.assertNotSame(existingRecord.getPhoneNumber(), phone);
    }

    @Test
    public void deleteRecordTest(){
        int id = rm.getMaxRecordId();
        String name = Utils.listOfStrings();
        String phone = "05026667777";

        int initSize = rm.getRecordList().size();
        Record recordToBeDeleted = new Record(name, id, phone);
        rm.addRecord(recordToBeDeleted);

        System.out.println("record to be deleted is: " + recordToBeDeleted);

        rm.deleteRecord(recordToBeDeleted.getId());
        int currentSize = rm.getRecordList().size();

        Assertions.assertEquals(initSize, currentSize);
    }

    @Test
    public void failOnRecordDeletion(){
        int id = rm.getMaxRecordId();
        String name = Utils.listOfStrings();
        String phone = "05026667777";

        Record recordToBeDeleted = new Record(name, id, phone);
        rm.addRecord(recordToBeDeleted);

        boolean listEmptyStatus = false;

        System.out.println("record to be deleted is: " + recordToBeDeleted);

        rm.deleteRecord(5);

        if(rm.getRecordList().isEmpty()){
            listEmptyStatus = true;
        }

        Assertions.assertFalse(listEmptyStatus);
    }


}
