import java.util.*;
import java.io.*;

public class Executive extends Manager {

    private double totalComp = 0.0;
    ArrayList<Manager> managedManagers = new ArrayList<Manager>();

    public Executive(String name, int iD, int age, double salary, String title){
        super(name, iD, age, salary, title);
    }

    public void setTotalComp(double profit, double percent) {
        totalComp = getSalary() + (profit *(percent/100));
    }

    public double getTotalComp() {
        return totalComp;
    }

    public ArrayList<Manager> getManagedManagers() {
        return managedManagers;
    }

    public void setManagedManagers(Manager newManager) {
        managedManagers.add(newManager);
    }

    public int getManagedManagerListSize(){
        return managedManagers.size();
    }

    public Employee getManagedManagerAtIndex(int index){
        return managedManagers.get(index);
    }

    @Override
    public String toString(){
        String nameTab = "";
        String titleTab = "";
        if(getName().length() <8)nameTab = getName() + "\t\t";
        else nameTab = getName() + "\t";
        if(getTitle().length() <8)titleTab = getTitle() + "\t\t";
        else titleTab = getTitle() + "\t";
        return String.format("%s%s%d\t%d\t$%,.2f\t$%,.2f", nameTab, titleTab, getiD(), getAge(), getSalary(), totalComp);
    }

    

}