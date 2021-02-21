package com.example.locobusz;

// Class to show all the details required

public class Item {

    private int id;
    private String name;
    private int rollNo;
    private String department;
    private int departmentCode;
    private String dateOfBirth;
    private String gender;
    private String timeStamp;

    public Item(){
    }

    public Item(int id, String name, int rollNo, String department, int departmentCode, String dateOfBirth, String gender,String timeStamp) {
        this.id = id;
        this.name = name;
        this.rollNo = rollNo;
        this.department = department;
        this.departmentCode = departmentCode;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.timeStamp=timeStamp;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(int departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
