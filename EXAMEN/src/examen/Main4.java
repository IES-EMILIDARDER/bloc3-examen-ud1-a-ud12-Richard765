package examen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.stream.Stream;

public class Main4 {

    public static void main(String[] args) {
        final String MYSQL_CON = "c:\\temp\\mysql.con";
        GestorBBDD gestorBBDD = new GestorBBDD(MYSQL_CON);
        
        try (Stream<String> linies = Files.lines(Paths.get("c:\\temp\\vehicles.csv"));
             Connection conn = gestorBBDD.getConnectionFromFile()) {

            linies.filter(linia -> !linia.isBlank() && !linia.startsWith("#"))
                  .map(linia -> linia.split(","))
                  .forEach(parts -> {
                      try {
                          gestorBBDD.executaSQL(conn,
                                  "SELECT * FROM vehicles WHERE any >= 2020",
                                  Integer.valueOf(parts[0].trim()), parts[1].trim(),parts[2].trim(),parts[3].trim(),parts[4].trim());

                      } catch (SQLException e) {
               
                      }
                  });

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    
}