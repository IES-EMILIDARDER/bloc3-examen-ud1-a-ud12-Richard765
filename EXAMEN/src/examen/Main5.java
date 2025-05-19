package examen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Stream;

public class Main5 {
    
    
    private static final String SERVER = "jdbc:mysql://192.168.126.245:3306/";
    private static final String DBASE = "gbd";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static void main(String[] args) {
        String marca = "Ford";
        String model = "Focus";
        int any = 2019;
        int preu = 17000;
        
        try {
            Connection conn = DriverManager.getConnection(SERVER+DBASE, USER, PASSWORD);

            if (vehicleExisteix(conn, model)) {
                insertarVehicle(conn, marca, model, any, preu);
                System.out.println("Vehiculo insertado correctamente.");
            } else {
                System.out.println("El vehiculo con marca " + marca + " no existe.");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean vehicleExisteix(Connection conn, String model) throws SQLException {
        String sql = "SELECT id FROM departamentos WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, model);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Retorna true si hay al menos una fila
            }
        }
    }    
        
        
    private static void insertarVehicle(Connection conn, String marca, String model, int any, int preu) throws SQLException {
        String sql = "INSERT INTO empleados (marca, model, any, preu) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, marca);
            pstmt.setString(2, model);
            pstmt.setInt(3, any);
            pstmt.setInt(3, preu);
            pstmt.executeUpdate();
        }
    }
}