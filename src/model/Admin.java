package model;

import controller.AddNewAccount;
import controller.AddNewCar;
import controller.UpdateCar;
import controller.ViewCars;

import java.sql.SQLException;
import java.util.Scanner;

public class Admin extends User {

    private Operation[] operations = new Operation[]{new AddNewCar(), new ViewCars(), new UpdateCar(),new AddNewAccount(1)};
    public Admin()
    {
        super();
    }
    @Override

    public void showList(Database database, Scanner s) throws SQLException {

        System.out.println("\n1. Add New Car");
        System.out.println("2. View Cars");
        System.out.println("3. Update Car");
        System.out.println("4. Delete Car");
        System.out.println("5. Add New Admin");
        System.out.println("6. Show Rents");
        System.out.println("7. Show User's Rents");
        System.out.println("8. Edit My Data");
        System.out.println("9. Change Password");
        System.out.println("10. Quit\n");

        int i = s.nextInt();
        operations[i-1].operation(database,s,this);
        showList(database,s);
    }
}
