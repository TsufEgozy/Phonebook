package phonebook;

import java.io.*;
import java.util.LinkedList;

public class RecordWriter {
    private final static String FILE_PATH = "Records.txt";
    Record record = new Record();
    private String fileString = "";

    public void writeToFile(LinkedList<Record> recordList) {
        BufferedWriter recordWriter;
        {
            try {
                recordWriter = new BufferedWriter(new FileWriter(FILE_PATH));

                recordWriter.newLine();

                for(Record record : recordList){
                    recordWriter.write(record.readerToString());

                    recordWriter.newLine();
                }
                recordWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void overwriteFile(String overwriteContent) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            writer.newLine();
            writer.write(overwriteContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Record> readFromFile(RecordManagement recordManagement){
        LinkedList<Record> records = new LinkedList<>();
        String stringForFile = "";
        try {
            BufferedReader recordReader = new BufferedReader(new FileReader(FILE_PATH));

            int intFirstLine;

            while(recordReader.ready()){
                Record recordRead = new Record();
                String recordLine = recordReader.readLine();
                stringForFile = stringForFile.concat(recordLine).concat("\n");
                if (!recordLine.isEmpty()) {
                    String[] recordArr = recordLine.split(",");
                    String idLine = recordArr[0];
                    intFirstLine = Integer.parseInt(idLine);

                    recordRead.setId(intFirstLine);

                    String nameLine = recordArr[1];
                    recordRead.setName(nameLine);

                    String phoneNumberLine = recordArr[2];
                    recordRead.setPhoneNumber(phoneNumberLine);

                    System.out.println(recordRead.toString());
                    records.add(recordRead);
                }
            }
            stringForFile = stringForFile.trim();
            setFileString(stringForFile);
        } catch (FileNotFoundException e) {
            System.out.println("Hello and thank you for choosing my digital phonebook app! :)");
            System.out.println("\n--- New Database Created ---");
        } catch (IOException e) {
            e.printStackTrace();
        }
        recordManagement.setRecordList(records);
        return records;
    }

    public String getFileString() {
        return fileString;
    }

    public void setFileString(String fileString) {
        this.fileString = fileString;
    }
}
