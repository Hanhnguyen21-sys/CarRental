package controller;

import model.Admin;
import model.Client;
import model.Database;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) throws SQLException {
        Database database = new Database();
        Scanner s = new Scanner(System.in);
        new Login(database,s);
    }
    public Login(Database database, Scanner s) throws SQLException {
        String email = "";
        String password="";
        System.out.println("Welcome to Nguyen's Car Rental System");
        //Enter -1 to create a new account
        System.out.println("(-1) to create an account or (1) to continue log in");
        String option = s.next();
        if(option.equals(String.valueOf(-1)))
        {
            System.out.println("You want to create an account for admin (1) or client (2) ?");
            int choice =s.nextInt();
            if (choice==1)
            {
                new AddNewAccount(1).operation(database,s,null);
            }
            else{
                new AddNewAccount(0).operation(database,s,null);
            }
            return;
        }
        else {
            System.out.println("Enter email address: ");
            email  =s.next();
            System.out.println("Enter password: ");
            password = s.next();
        }
        ArrayList<User> users = new ArrayList<>();
        try {
            String select = "Select * From `Users`;";
            ResultSet result = database.getStatement().executeQuery(select);
            // take all information from users'table and create user object with
            // that information
            while(result.next())
            {
                /**PROBLEMS!!!!*/
                User user;
                int id = result.getInt("id");
                String firstname = result.getString("firstName");
                String lastname = result.getString("lastName");
                String mail = result.getString("email");
                String phone= result.getString("phone");
                String pass = result.getString("password");
                int type = result.getInt("type");
                if(type==0)
                {
                    user = new Client();
                    user.setId(id);
                    user.setFirstname(firstname);
                    user.setLastname(lastname);
                    user.setEmail(mail);
                    user.setPhone(phone);
                    user.setPassword(pass);
                    users.add(user);
                }
                else if(type==1)
                {
                    user = new Admin();
                    user.setId(id);
                    user.setFirstname(firstname);
                    user.setLastname(lastname);
                    user.setEmail(mail);
                    user.setPhone(phone);
                    user.setPassword(pass);
                    users.add(user);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        boolean login = false;
        for(User u: users)
        {
            if(u.getEmail().equals(email) && u.getPassword().equals(password))
            {
                login = true;
                System.out.println("Welcome back, " + u.getFirstname());
                u.showList(database,s);
            }
        }
        if (!login)
        {
            System.out.println("Invalid password!");
            s.close();
        }

    }
}
