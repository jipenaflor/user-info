package com.userinfo;

public class Person {

    private String firstName;
    private String lastName;
    private String birtDate;
    private String email;
    private String sex;

    public Person(String firstName, String lastName, String birtDate,
                  String email, String sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birtDate = birtDate;
        this.email = email;
        this.sex = sex;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirtDate(String birtDate) {
        this.birtDate = birtDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirtDate() {
        return birtDate;
    }

    public String getEmail() {
        return email;
    }

    public String getSex() {
        return sex;
    }
}
