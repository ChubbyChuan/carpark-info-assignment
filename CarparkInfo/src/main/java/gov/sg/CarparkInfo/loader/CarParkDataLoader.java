package gov.sg.CarparkInfo.loader;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class CarParkDataLoader {

    private final JdbcTemplate jdbcTemplate;

    public CarParkDataLoader(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void loadData(String filePath) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            reader.readLine(); // Skip the header line
    
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
    
                // Parse and validate data
                String carParkNo = data[0];
                String address = data[1];
                Float xCoord = parseFloat(data[2]);
                Float yCoord = parseFloat(data[3]);
                String carParkType = data[4];
                String typeOfParkingSystem = data[5];
                String shortTermParking = data[6];
                String freeParking = data[7];
                String nightParking = data[8];
                Integer carParkDecks = parseInt(data[9]);
                Float gantryHeight = parseFloat(data[10]);
                String carParkBasement = data[11];
    
                if (carParkNo == null || carParkNo.isBlank() || address == null || address.isBlank()) {
                    throw new IllegalArgumentException("Invalid data: carParkNo and address are required fields.");
                }
    
                String sql = "INSERT INTO car_park (car_park_no, address, x_coord, y_coord, car_park_type, " +
                             "type_of_parking_system, short_term_parking, free_parking, night_parking, car_park_deck, " +
                             "gantry_height, car_park_basement) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
                jdbcTemplate.update(sql, carParkNo, address, xCoord, yCoord, carParkType, typeOfParkingSystem,
                        shortTermParking, freeParking, nightParking, carParkDecks, gantryHeight, carParkBasement);
            }
            System.out.println("Data loaded successfully!");
        } catch (Exception e) {
            // Log the error and proceed
            System.err.println("Error occurred during data loading: " + e.getMessage());
        }
    }

    private Float parseFloat(String value) {
        try {
            return value != null && !value.isBlank() ? Float.parseFloat(value) : null;
        } catch (NumberFormatException e) {
            System.err.println("Invalid Float value: " + value); // To check
            throw new IllegalArgumentException("Invalid Float value: " + value, e); // For trigger
        }
    }

    private Integer parseInt(String value) {
        try {
            return value != null && !value.isBlank() ? Integer.parseInt(value) : null;
        } catch (NumberFormatException e) {
            System.err.println("Invalid Integer value: " + value); // To check
            throw new IllegalArgumentException("Invalid Integer value: " + value, e); // For trigger
        }
    }
}
