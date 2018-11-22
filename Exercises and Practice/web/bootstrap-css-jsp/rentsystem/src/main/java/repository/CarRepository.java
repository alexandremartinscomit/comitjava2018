package repository;

import domain.Car;
import domain.CarType;
import exception.InfrastructureException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarRepository extends BaseRepository<Car>{

    private static final String LOG_ERROR_MSG = "Error during the car %s";

    public void add(Car car){
        Connection connection = openConnection();

        try {
            String sql = "INSERT INTO cars(id,year,plate,model,brand,color, type) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, car.getId());
            pstmt.setInt(2, car.getYear());
            pstmt.setString(3, car.getPlate());
            pstmt.setString(4, car.getModel());
            pstmt.setString(5, car.getBrand());
            pstmt.setString(6, car.getColor());
            pstmt.setString(7, car.getType().name());

            if(pstmt.executeUpdate() == 0){
                throw new InfrastructureException("The insert wasn't executed!");
            }

        } catch (SQLException e){
            logger.error(String.format(LOG_ERROR_MSG, "insert"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "insert"),e);
        } finally {
            closeConnection(connection);
        }
    }

    public void modify(Car car){
        Connection connection = openConnection();

        try{
            String sql = "UPDATE cars set year = ?,plate = ?,model = ?,brand = ?,color = ?, type = ? WHERE id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, car.getYear());
            pstmt.setString(2, car.getPlate());
            pstmt.setString(3, car.getModel());
            pstmt.setString(4, car.getBrand());
            pstmt.setString(5, car.getColor());
            pstmt.setString(6,car.getType().name());
            pstmt.setString(7, car.getId());


            if(pstmt.executeUpdate() == 0){
                throw new InfrastructureException("The Update wasn't executed!");
            }
        } catch (SQLException e){
            logger.error(String.format(LOG_ERROR_MSG, "update"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "update"),e);
        } finally {
            closeConnection(connection);
        }

    }

    public void remove(Car car) {
        Connection connection = openConnection();

        try{

            String sql = "DELETE FROM cars WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, car.getId());

            if(pstmt.executeUpdate() == 0){
                throw new InfrastructureException("The delete wasn't executed!");
            }
        } catch (SQLException e){
            logger.error(String.format(LOG_ERROR_MSG, "delete"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "delete"),e);
        }

    }

    public Optional<Car> findById(String id){
        Connection connection = openConnection();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cars WHERE id=?");
            statement.setString(1,id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int year = resultSet.getInt("year");
                String color = resultSet.getString("color");
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                String plate = resultSet.getString("plate");
                CarType type  = CarType.valueOf(resultSet.getString("type"));

                return Optional.of(new Car(id, plate, color, year, brand, model, type));
            }
        } catch(SQLException e){
            logger.error(String.format(LOG_ERROR_MSG, "findById"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "findById"),e);
        } finally {
            closeConnection(connection);
        }

        return Optional.empty();

    }

    @Override
    public Optional<Car> findByCriteria(String field, String criteria) {
        Connection connection = openConnection();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cars WHERE "+field+"=?");
            statement.setString(1,criteria);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String id = resultSet.getString("id");
                int year = resultSet.getInt("year");
                String color = resultSet.getString("color");
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                String plate = resultSet.getString("plate");
                CarType type  = CarType.valueOf(resultSet.getString("type"));

                return Optional.of(new Car(id, plate, color, year, brand, model, type));
            }
        } catch(SQLException e){
            logger.error(String.format(LOG_ERROR_MSG, "findById"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "findById"),e);
        } finally {
            closeConnection(connection);
        }

        return Optional.empty();
    }

    public List<Car> findAll() {

        List<Car> cars = new ArrayList<Car>();

        Connection conn = openConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM cars");


            while (resultSet.next()) {
                String id = resultSet.getString("id");
                int year = resultSet.getInt("year");
                String color = resultSet.getString("color");
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                String plate = resultSet.getString("plate");
                CarType type  = CarType.valueOf(resultSet.getString("type"));

                cars.add(new Car(id, plate, color, year, brand, model, type));
            }

        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "findAll"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "findAll"),e);
        } finally {
            closeConnection(conn);
        }
        return cars;
    }
}
