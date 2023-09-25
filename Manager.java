import java.util.*;
import java.io.*;

public class Manager extends Employee{

    private ArrayList<Employee> managedEmployees = new ArrayList<Employee>();

    public Manager(String name, int iD, int age, double salary, String title){
        super(name, iD, age, salary, title);
        super.setSalary(salary);
        this.managedEmployees = managedEmployees;
        super.getAge();
        super.getName();
        super.getSalary();
        super.getTitle();
        super.getiD();

    }

    public void setManagedEmployees(Employee newEmployee) {
        managedEmployees.add(newEmployee);
    }

    public ArrayList<Employee> getManagedEmployees() {
    return managedEmployees;
    }

    public int getManagedEmployeesListSize(){
        return managedEmployees.size();
    }

    public Employee getManagedEmployeeAtIndex(int index){
        return managedEmployees.get(index);
    }

    @Override
    public String toString(){
        String nameTab = "";
        String titleTab = "";
        if(getName().length() <8)nameTab = getName() + "\t\t";
        else nameTab = getName() + "\t";
        if(getTitle().length() <8)titleTab = getTitle() + "\t\t";
        else titleTab = getTitle() + "\t";
        return String.format("%s%s%d\t%d\t$%,.2f", nameTab, titleTab, getiD(), getAge(), getSalary());
    }

}