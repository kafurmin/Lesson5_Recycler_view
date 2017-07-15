package com.example.kif.lesson5_recycler_view;

/**
 * Created by Kif on 13.07.2017.
 */

public class Student {
    public String FirstName;
    public String LastName;
    public int Age;
    boolean checked;

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Student(String firstName, String lastName, int age, boolean checked) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Age = age;
        this.checked=checked;
    }
}