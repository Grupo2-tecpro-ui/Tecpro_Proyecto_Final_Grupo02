package repositorio;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public static Connection getConexion() {
        Connection cnx = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver correcto");

            cnx = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/prosegur",
                "root",
                "03458969"
            );
            System.out.println("Conexión correcta");

        } catch (Exception e) {
            System.out.println("Error en conexión: " + e.getMessage());
        }
        return cnx;
    }

    public static void main(String[] args) {
        getConexion();
    }
}
