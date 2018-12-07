package repository;

import domain.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class ResultSetUtil {

    public static Car buildCar(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("car_id");
        int year = resultSet.getInt("year");
        String color = resultSet.getString("color");
        String brand = resultSet.getString("brand");
        String model = resultSet.getString("model");
        String plate = resultSet.getString("plate");
        CarType type  = CarType.valueOf(resultSet.getString("car_type"));

        return new Car(id, plate, color, year, brand, model, type);
    }

    public static User buildUser(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("user_id");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        UserType type  = UserType.valueOf(resultSet.getString("user_type"));

        return new User(id, email, password, type);
    }
    
    public static RentOrder buildRentOrder(ResultSet resultSet) throws SQLException {
        Car car = buildCar(resultSet);
        User user = buildUser(resultSet);
        String id = resultSet.getString("rent_order_id");
        Instant rentDate = Instant.ofEpochMilli(resultSet.getLong("rent_date"));
        Instant expectedDeliveryDate = Instant.ofEpochMilli(resultSet.getLong("expected_delivery_date"));;
        float price = resultSet.getFloat("price");
        boolean isFreeUpgrade = resultSet.getBoolean("free_upgrade");
        String observations = resultSet.getString("observations");

        return new RentOrder(id, car, user, rentDate, expectedDeliveryDate, price, isFreeUpgrade, observations);
        
        
    }
}
