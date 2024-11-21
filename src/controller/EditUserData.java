package controller;

import model.Database;
import model.Operation;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EditUserData implements Operation {
    @Override
    public void operation(Database database, Scanner s, User user) throws SQLException {
        System.out.println("Enter new information if you want or -1 to keep old information");
        System.out.println("Enter First Name: ");
        String fname = s.next();
        if(fname.equals("-1"))
        {
            fname = user.getFirstname();
        }
        System.out.println("Enter Last Name: ");
        String lname = s.next();
        if(lname.equals("-1"))
        {
            lname = user.getLastname();
        }
        System.out.println("Enter Email: ");
        String email = s.next();
        if(email.equals("-1"))
        {
            email = user.getEmail();
        }
        System.out.println("Enter Phone Number: ");
        String phone = s.next();
        if(phone.equals("-1"))
        {
            phone = user.getPhone();
        }
        String update = "UPDATE `Users` SET `firstname` = '" + fname + "',`lastname`= '" + lname +"', `email` = '" + email + "',`phone` = '" + phone + "' WHERE `id` = '" + user.getId() + "';";
        database.getStatement().execute(update);
        System.out.println("Data updated successfully!");
    }
}
