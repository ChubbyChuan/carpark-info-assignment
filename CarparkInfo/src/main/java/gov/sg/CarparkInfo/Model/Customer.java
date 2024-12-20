package gov.sg.CarparkInfo.Model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Customer {
    private String username;
    private String password;
    private String favourites;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFavourites() {
        return this.favourites;
    }

    public void setFavourites(String favourites) {
        this.favourites = favourites;
    }

    public JsonObject toJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder()
                .add("username", getUsername() != null ? getUsername() : "")
                .add("password", getPassword() != null ? getPassword() : "") // Consider not including password in JSON
                                                                             // for security
                .add("favourites", getFavourites() != null ? getFavourites() : "");
        return builder.build();
    }

    public static Customer create(SqlRowSet rs) {
        Customer customer = new Customer();
        customer.setUsername(rs.getString("username"));
        customer.setPassword(rs.getString("password")); // Ensure password is stored securely
        customer.setFavourites(rs.getString("favourites"));
        return customer;
    }

}
