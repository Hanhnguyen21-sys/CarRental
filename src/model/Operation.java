package model;

import java.sql.SQLException;
import java.util.Scanner;

public interface Operation {

    public void operation(Database database, Scanner s, User user) throws SQLException;
}
