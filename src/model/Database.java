package model;
import java.sql.*;

public class Database {
    private String user = "root";
    private String password = "02012002";
    private String url = "jdbc:mysql://127.0.0.1:3306/car";
    private Statement statement;

    public Database()
    {
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Statement getStatement() {
        return statement;
    }
}
