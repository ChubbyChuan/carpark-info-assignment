package gov.sg.CarparkInfo.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import gov.sg.CarparkInfo.Model.CarPark;
import gov.sg.CarparkInfo.Model.Customer;

@Repository
public class CarParkRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CarPark> findCarParks(String sql) {

        List<CarPark> carParkList = new ArrayList<>();

        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(sql);
        while (resultSet.next())
            carParkList.add(CarPark.create(resultSet));
        return carParkList;
    }

    public String findFavourites(String username) {
        String sql = "SELECT * FROM customers WHERE username = ?";
    
        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(sql, username);
        if (resultSet.next()) {
            Customer customer = Customer.create(resultSet);
            return customer.getFavourites();
        }
        
        return null;
    }
    
}
