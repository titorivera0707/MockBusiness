import java.io.*;
import java.util.*;

public class Employee{

    private String name;
    private int iD;
    private int age;
    private double salary;
    private String title;

    public Employee(){   
    }

    public Employee(String name, int iD, int age, double salary, String title){

        this.name = name;
        this.iD = iD;
        this.age = age;
        this.salary = salary;
        this.title = title;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void changeSalary(double percent){
        salary *= (1.0+(percent/100.0));
    }

    @Override
    public String toString(){
        String nameTab = "";
        String titleTab = "";
        if(name.length() <8)nameTab = name + "\t\t";
        else nameTab = name + "\t";
        if(title.length() <8)titleTab = title + "\t\t";
        else titleTab = title + "\t";
        return String.format("%s%s%d\t%d\t$%,.2f", nameTab, titleTab, iD, age, salary);
    }

}