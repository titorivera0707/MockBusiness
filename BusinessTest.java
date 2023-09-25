import java.util.*;
import java.io.*;
import java.text.ParseException;

public class BusinessTest{
    public static void main(String[] args)throws FileNotFoundException, ParseException{

        //Creates all of the arrayLists needed to get appropriate information.
        ArrayList<Employee> employeeAL = new ArrayList<Employee>();
        ArrayList<Manager> managerAL = new ArrayList<Manager>();
        ArrayList<Executive> executiveAL = new ArrayList<Executive>();

        //Gets user input that will be used.

        //Checks whether the file input exists or not.
        Scanner readFile = new Scanner(fileCheck("input"));
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter the total company profit for the year: ");
        double newProfit = keyboard.nextDouble();
        System.out.println("Please enter the executive bonus percentage for the year correct to 1 decimal place: ");
        double newPercent = keyboard.nextDouble();
        keyboard.nextLine();
        File outputFile = new File("");
        outputFile = fileCheck("output");
        System.out.println("Please enter the salary change for a Manager as a percentage: ");
        double managerPercent = keyboard.nextDouble();

        double totalPay = 0.0;

        while(readFile.hasNext()){
            String readIn = readFile.nextLine();
            String[] tempStr = readIn.split("\\s+");
            int idRead = Integer.parseInt(tempStr[2]);
            double tempPayRoll = Double.parseDouble(tempStr[4]);
            
            if(idRead <=2000){
                totalPay += tempPayRoll;
                employeeAL.add(new Employee(tempStr[0], idRead, Integer.parseInt(tempStr[3]), tempPayRoll, tempStr[1]));
            }else if(idRead > 2000 && idRead <=3000){
                managerAL.add(new Manager(tempStr[0], idRead, Integer.parseInt(tempStr[3]), tempPayRoll, tempStr[1]));
            }else{
                executiveAL.add((new Executive(tempStr[0], idRead, Integer.parseInt(tempStr[3]), tempPayRoll, tempStr[1])));

            }
        }
        int counter=0;

        for(int x = 0; x < employeeAL.size(); x++){
            if(counter >= managerAL.size())counter-=managerAL.size();
            Manager temp = managerAL.get(counter);
            //temp.changeSalary(managerPercent);
            temp.setManagedEmployees(employeeAL.get(x));
            counter++;
        }
        int newCounter =0;

        for(int x = 0; x < managerAL.size(); x++){
            if(newCounter >= executiveAL.size())newCounter-=executiveAL.size();
            Executive temp = executiveAL.get(newCounter);
            Manager m = managerAL.get(x);
            m.changeSalary(managerPercent);
            totalPay += m.getSalary();
            temp.setManagedManagers(managerAL.get(x));
            newCounter++;
        }

        for(Executive x: executiveAL){
            x.setTotalComp(newProfit, newPercent);
            totalPay += x.getTotalComp();
        }

        PrintWriter printwrter = new PrintWriter(outputFile);
        printwrter.print(String.format("The total payroll of the business is $%,.2f\n\nCompensation Table\n", totalPay));
        String template = "Name\t\tTitle\t\tID\tAge\tSalary";
        String executiveStr = "";
        String managerStr = "";
        String employeeStr = "";

        for(Executive x:executiveAL){ 
            executiveStr+=String.format("%s\n", x.toString());
        }
        printwrter.print(String.format("Executives\n%s\t\tTotal Comp\n%s\n", template,executiveStr));
        for(Manager x:managerAL) managerStr+=String.format("%s\n", x.toString());
        printwrter.print(String.format("Managers\n%s\n%s\n", template,managerStr));
        for(Employee x:employeeAL) employeeStr+=String.format("%s\n", x.toString());
        printwrter.print(String.format("Employees\n%s\n%s\n\n", template,employeeStr));

        printwrter.println("Reporting Structure\n\nDirect Reports to Executive\n");
        for(Executive y:executiveAL){
            printwrter.println(String.format("Executive %s has the following direct reports", y.getName()));
            for(Manager m: y.getManagedManagers()){
                String manName = "";
                if(m.getName().length() < 8) manName += m.getName()+"\t\t";
                else manName += m.getName()+"\t";
                printwrter.println(String.format("%s%d", manName, m.getiD()));
            }
        }

        printwrter.println("\nDirect Reports to Managers\n");
        for(Manager y:managerAL){
            printwrter.println(String.format("Manager %s has the following direct reports", y.getName()));
            for(Employee m: y.getManagedEmployees()){
                String manName = "";
                if(m.getName().length() < 8) manName += m.getName()+"\t\t";
                else manName += m.getName()+"\t";
                printwrter.println(String.format("%s%d", manName, m.getiD()));
            }
        }

        for(Manager m: managerAL){
            printwrter.println(String.format("\nThe manager name and title are %s %s",m.getName(),m.getTitle()));
            printwrter.println(String.format("The manager ID is %d The manager age is %d",m.getiD(),m.getAge()));
            printwrter.println(String.format("the manager salary is $%,.2f",m.getSalary()));
        }   



        readFile.close();
        keyboard.close();
        printwrter.close();
    }

    //Method for checking if the files exist and asking for new input if it doesn't exist.
    public static File fileCheck(String inOutFile)throws FileNotFoundException{
        Scanner newKey = new Scanner(System.in);
        File newUserFile = new File("");
        do{
            System.out.println(String.format("Please enter the name of the %s file with employee name and data: ", inOutFile));
            String fileName = newKey.nextLine();
            File tempFile = new File(fileName);
            if(tempFile.exists()){
                newUserFile = tempFile;
                continue;
            }else{
                System.out.println("File does not exist. Please try again.");
            }
        }while(!newUserFile.exists());
        return newUserFile;
    }

}