package net.learnwithfun.springbootrestapi.models;

public class Student {
    private String firstName;
    private String LastName;
    private int id;

    public Student(int id, String firstName, String lastName) {
        this.firstName = firstName;
        LastName = lastName;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
