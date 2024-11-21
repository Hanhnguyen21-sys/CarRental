package controller;

import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddNewAccount implements Operation {
    private int type;
    public AddNewAccount(int type)
    {
        this.type=type;
    }
    @Override
    public void operation(Database database, Scanner s, User user) {
        //Taking new admin's information from users' input
        System.out.println("Enter first name: ");
        String firstName = s.next();
        System.out.println("Enter last name: ");
        String lastName = s.next();
        System.out.println("Enter email: ");
        String email = s.next();
        System.out.println("Enter phone number: ");
        String phone = s.next();
        System.out.println("Enter password: ");
        String password = s.next();
        System.out.println("Confirm password: ");
        String c_password = s.next();
        while(!c_password.equals(password))
        {
            System.out.println("Password does not match! Try again");
            System.out.println("Enter password: ");
            password = s.next();
            System.out.println("Confirm password: ");
            c_password = s.next();
        }

        //adding it to database
        try{
            //getting the current size of the table
            // checking whether email existed or not
            ArrayList<String> emails = new ArrayList<>();
            ResultSet r = database.getStatement().executeQuery("SELECT `email` FROM Users");
            while(r.next())
            {
                emails.add(r.getString("email"));
            }
            // if the email user just input already existed in database
            if (emails.contains(email))
            {
                System.out.println("Email already existed!");
                return;
            }
            // getting statement and execute it, store the result in ResultSet
            ResultSet result = database.getStatement().executeQuery("SELECT COUNT(*);");
            result.next();
            int ID= result.getInt("COUNT(*)")-1;
            String insert = "INSERT INTO `Users` (`id`, `firstName`, `lastName`, `email`, `phone`, `password`,`type`) VALUES  (' " + ID + "', '" + firstName + " ','" +lastName + "','"+ email+ "','"+ phone + "','" + password + "','"+ type +"');";
            database.getStatement().execute(insert);
            System.out.println("An account is successfully created");
            if(type==0)
            {
                user = new Client();
                user.setId(ID);
                user.setFirstname(firstName);
                user.setLastname(lastName);
                user.setEmail(email);
                user.setPassword(password);
                user.showList(database,s);
            }
            else if(type==1)
            {
                user = new Admin();
                user.setId(ID);
                user.setFirstname(firstName);
                user.setLastname(lastName);
                user.setEmail(email);
                user.setPassword(password);
                user.showList(database,s);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
