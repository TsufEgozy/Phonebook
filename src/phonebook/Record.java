package phonebook;

import java.util.Objects;

public class Record {

    private int id;
    private String name;
    private String phoneNumber;

    public Record(){}

    public Record(String name, int id, String phoneNumber){
        this.id=id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String
    getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return id == record.id && phoneNumber == record.phoneNumber && Objects.equals(name, record.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, phoneNumber);
    }

    @Override
    public String toString() {
        return String.format("%d. %s - %s", id, name, phoneNumber);
    }

//    @Override
//    public String toString() {
//        return String.format("%d %s %s", id, name, phoneNumber);
//    }

    public String readerToString() {
        return String.format("%d,%s,%s", id, name, phoneNumber);
    }
}
