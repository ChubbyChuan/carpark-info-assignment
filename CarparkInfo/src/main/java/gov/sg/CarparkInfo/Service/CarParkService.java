package gov.sg.CarparkInfo.Service;

import gov.sg.CarparkInfo.Model.CarPark;
import gov.sg.CarparkInfo.Repository.CarParkRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarParkService {

    @Autowired
    CarParkRepository carParkRepo;

    public List<CarPark> filterCarParks(String freeParking, String nightParking, Float height, int page, int size) {
        System.out.println("Svc FilterCar Triggered");
            
        String sqlString = buildQueryString(freeParking, nightParking, height, page, size);
        System.out.println("SQL ->  "  + sqlString);

        List<CarPark> allCarParks = carParkRepo.findCarParks(sqlString);
        return allCarParks;
    }

    public List<CarPark> filterCarParksWithGeo(String freeParking, String nightParking, Float height, Float xCoord,
            Float yCoord, int page, int size) {
        String sqlString = buildQueryString(freeParking, nightParking, height, xCoord, yCoord, page, size);
        return carParkRepo.findCarParks(sqlString);
    }

    public List<CarPark> filterFavourites(String user, int page, int size) {
        System.out.println("Svc ->" + user + page + size);

        String favStr = carParkRepo.findFavourites(user);
        String [] favArray = favStr.split(",");
        System.out.print("Retrieve String ->" + favStr);
        String sqlString = buildQueryString(favArray, page, size);

        List<CarPark> allCarParks = carParkRepo.findCarParks(sqlString);

        return allCarParks;
    }

    private String buildQueryString(String freeParking, String nightParking, Float height, int page, int size) {

        StringBuilder sb = new StringBuilder("SELECT * FROM CAR_PARK WHERE 1=1"); // the 1+1 is for me to append
        if (freeParking != null && !freeParking.isBlank()) { // add freeParking if specified
            sb.append(" AND free_parking = '").append(freeParking).append("'");
        }
        if (nightParking != null && !nightParking.isBlank()) { // add night parking
            sb.append(" AND night_parking = '").append(nightParking).append("'");
        }
        if (height != null) { // add height
            sb.append(" AND gantry_height >= ").append(height);
        }

        int offset = page * size;
        sb.append(" LIMIT ").append(size).append(" OFFSET ").append(offset);

        return sb.toString();
    }

    private String buildQueryString(String freeParking, String nightParking, Float height, Float xCoord, Float yCoord,
            int page, int size) {
        StringBuilder sb = new StringBuilder("SELECT * FROM car_park WHERE 1=1;");

        if (freeParking != null && !freeParking.isBlank()) {
            sb.append(" AND free_parking = '").append(freeParking).append("'");
        }
        if (nightParking != null && !nightParking.isBlank()) {
            sb.append(" AND night_parking = '").append(nightParking).append("'");
        }
        if (height != null) {
            sb.append(" AND gantry_height >= ").append(height);
        }
        if (xCoord != null) {
            float xMin = xCoord - 10;
            float xMax = xCoord + 10;
            sb.append(" AND x_coord BETWEEN ").append(xMin).append(" AND ").append(xMax);
        }
        if (yCoord != null) {
            float yMin = yCoord - 10;
            float yMax = yCoord + 10;
            sb.append(" AND y_coord BETWEEN ").append(yMin).append(" AND ").append(yMax);
        }
        int offset = page * size;
        sb.append(" LIMIT ").append(size).append(" OFFSET ").append(offset);

        return sb.toString();
    }

    public String buildQueryString(String[] favArray, int page, int size) {
        StringBuilder sb = new StringBuilder("SELECT * FROM car_park WHERE ");

        for (int i = 0; i < favArray.length; i++) {
            sb.append("car_park_no = '").append(favArray[i]).append("'");
            if (i < favArray.length - 1) {
                sb.append(" OR ");
            }
        }
        int offset = page * size;
        sb.append(" LIMIT ").append(size).append(" OFFSET ").append(offset);
    
        return sb.toString();
    }

    public void addFavourite(String user, String favourites) {
        carParkRepo.addFavourite(user, favourites);
    }
 
}