package controller;

import model.Database;
import model.Operation;
import model.User;

import java.sql.SQLException;
import java.util.Scanner;

public class ChangePassword implements Operation {
    @Override
    public void operation(Database database, Scanner s, User user) throws SQLException {
        System.out.println("Enter Old Password: ");
        String o_pass = s.next();
        if(!o_pass.equals(user.getPassword()))
        {
            System.out.println("Invalid password");
            return;
        }
        System.out.println("Enter new ");
    }
}
