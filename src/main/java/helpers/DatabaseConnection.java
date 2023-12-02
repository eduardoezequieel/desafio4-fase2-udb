package helpers;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private final String user = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/library";
    private Connection connection;

    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: controlador JDBC no encontrado - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al conectarse a la base de datos: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}

