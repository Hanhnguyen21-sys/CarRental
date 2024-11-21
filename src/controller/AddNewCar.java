package controller;

import model.Database;
import model.Operation;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AddNewCar implements Operation {
    @Override
    public void operation(Database database, Scanner s, User user) {
        System.out.println("Enter car's brand: ");
        String brand = s.next();
        System.out.println("Enter model: ");
        String model = s.next();
        System.out.println("Enter color: ");
        String color = s.next();
        System.out.println("Enter year: ");
        int year = s.nextInt();
        System.out.println("Enter price per hour: ");
        double price = s.nextDouble();
        int available = 0;
        try {
            ResultSet result = database.getStatement().executeQuery("SELECT COUNT(*) FROM Cars");
            result.next();
            int id = result.getInt("COUNT(*)")-1;
            String insert = "INSERT INTO `Cars` (`id`,`brand`,`model`,`color`,`year`,`price`, `available`) VALUES ('" + id + "','" + brand +"','" +model +"','"+color + "','" + year + "','" + price +"','" + available +"');";
            database.getStatement().execute(insert);
            System.out.println("Car added successfully!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
