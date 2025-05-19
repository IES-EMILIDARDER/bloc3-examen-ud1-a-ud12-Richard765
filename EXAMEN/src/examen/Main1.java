package examen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main1 {
    
    public static void main(String[] args) {
        try (Stream<String> linies = Files.lines(Paths.get("c:\\temp\\vehicles.csv"))) {

            List<Vehicle> vehicles = 
                   linies.filter(linia -> !linia.isBlank() && !linia.startsWith("#"))
                  .map(linia -> linia.split(","))
                  .sorted((parts1, parts2) -> parts1[1].compareTo(parts2[1])) 
                  .map(parts -> new Vehicle( String.parseString(parts[0].trim()),
                                            parts[1].trim(),
                                            parts[2].trim(),
                                            parts[3].trim(),
                                            parts[4].trim()
                                          )
                      )
                  .collect(Collectors.toList());
            
            // Imprimeix la llista
            vehicles.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

