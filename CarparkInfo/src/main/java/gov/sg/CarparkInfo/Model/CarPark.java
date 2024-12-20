package gov.sg.CarparkInfo.Model;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.JsonObject;

public class CarPark {
    private String carParkNo;
    private String address;
    private float xCoord;
    private float yCoord;
    private String carParkType;
    private String typeOfParkingSystem;
    private String shortTermParking;
    private String freeParking;
    private String nightParking;
    private Integer carParkDecks;
    private float gantryHeight;
    private String carParkBasement;

    public CarPark() {
    }

    public CarPark(String carParkNo, String address, float xCoord, float yCoord, String carParkType,
            String typeOfParkingSystem, String shortTermParking, String freeParking, String nightParking,
            Integer carParkDecks, float gantryHeight, String carParkBasement) {
        this.carParkNo = carParkNo;
        this.address = address;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.carParkType = carParkType;
        this.typeOfParkingSystem = typeOfParkingSystem;
        this.shortTermParking = shortTermParking;
        this.freeParking = freeParking;
        this.nightParking = nightParking;
        this.carParkDecks = carParkDecks;
        this.gantryHeight = gantryHeight;
        this.carParkBasement = carParkBasement;
    }

    public String getCarParkNo() {
        return this.carParkNo;
    }

    public void setCarParkNo(String carParkNo) {
        this.carParkNo = carParkNo;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getXCoord() {
        return this.xCoord;
    }

    public void setXCoord(float xCoord) {
        this.xCoord = xCoord;
    }

    public float getYCoord() {
        return this.yCoord;
    }

    public void setYCoord(float yCoord) {
        this.yCoord = yCoord;
    }

    public String getCarParkType() {
        return this.carParkType;
    }

    public void setCarParkType(String carParkType) {
        this.carParkType = carParkType;
    }

    public String getTypeOfParkingSystem() {
        return this.typeOfParkingSystem;
    }

    public void setTypeOfParkingSystem(String typeOfParkingSystem) {
        this.typeOfParkingSystem = typeOfParkingSystem;
    }

    public String getShortTermParking() {
        return this.shortTermParking;
    }

    public void setShortTermParking(String shortTermParking) {
        this.shortTermParking = shortTermParking;
    }

    public String getFreeParking() {
        return this.freeParking;
    }

    public void setFreeParking(String freeParking) {
        this.freeParking = freeParking;
    }

    public String getNightParking() {
        return this.nightParking;
    }

    public void setNightParking(String nightParking) {
        this.nightParking = nightParking;
    }

    public Integer getCarParkDecks() {
        return this.carParkDecks;
    }

    public void setCarParkDecks(Integer carParkDecks) {
        this.carParkDecks = carParkDecks;
    }

    public float getGantryHeight() {
        return this.gantryHeight;
    }

    public void setGantryHeight(float gantryHeight) {
        this.gantryHeight = gantryHeight;
    }

    public String getCarParkBasement() {
        return this.carParkBasement;
    }

    public void setCarParkBasement(String carParkBasement) {
        this.carParkBasement = carParkBasement;
    }

    public JsonObject toJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder()
                .add("carParkNo", getCarParkNo() != null ? getCarParkNo() : "")
                .add("address", getAddress() != null ? getAddress() : "")
                .add("xCoord", getXCoord())
                .add("yCoord", getYCoord())
                .add("carParkType", getCarParkType() != null ? getCarParkType() : "")
                .add("typeOfParkingSystem", getTypeOfParkingSystem() != null ? getTypeOfParkingSystem() : "")
                .add("shortTermParking", getShortTermParking() != null ? getShortTermParking() : "")
                .add("freeParking", getFreeParking() != null ? getFreeParking() : "")
                .add("nightParking", getNightParking() != null ? getNightParking() : "")
                .add("carParkDecks", getCarParkDecks() != null ? getCarParkDecks() : 0)
                .add("gantryHeight", getGantryHeight())
                .add("carParkBasement", getCarParkBasement() != null ? getCarParkBasement() : "");

        return builder.build();
    }

    public static CarPark create(SqlRowSet rs) {
        CarPark cp = new CarPark();
        cp.setCarParkNo(rs.getString("car_park_no"));
        cp.setAddress(rs.getString("address"));
        cp.setXCoord(rs.getFloat("x_coord"));
        cp.setYCoord(rs.getFloat("y_coord"));
        cp.setCarParkType(rs.getString("car_park_type"));
        cp.setTypeOfParkingSystem(rs.getString("type_of_parking_system"));
        cp.setShortTermParking(rs.getString("short_term_parking"));
        cp.setFreeParking(rs.getString("free_parking"));
        cp.setNightParking(rs.getString("night_parking"));
        cp.setCarParkDecks(rs.getInt("car_park_deck"));
        cp.setGantryHeight(rs.getFloat("gantry_height"));
        cp.setCarParkBasement(rs.getString("car_park_basement"));
        return cp;
    }

}
