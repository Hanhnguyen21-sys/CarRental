package model;

import controller.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Client extends User {
    private Operation[] operations = new Operation[]{new ViewCars(),
            new RentCar(),
            new ReturnCar() ,
            new ShowUserRents(),
            new EditUserData(),
            new Quit()};
    public Client()
    {
        super();
    }
    @Override
    public void showList(Database database, Scanner s) throws SQLException {
        System.out.println("\n1. View Cars");
        System.out.println("2. Rent Car");
        System.out.println("3. Return Car");
        System.out.println("4. Show My Rents");
        System.out.println("5. Edit My Data");
        System.out.println("6. Change Password");
        System.out.println("7. Quit\n");

        int i = s.nextInt();
        operations[i-1].operation(database,s,this);
        showList(database,s);
    }
}
