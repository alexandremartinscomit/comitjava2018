package repository;

import domain.RentOrder;
import exception.InfrastructureException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static repository.ResultSetUtil.buildRentOrder;

public class RentOrderRepository extends BaseRepository<RentOrder> {

    private static final String LOG_ERROR_MSG = "Error during the rent order %s";

    @Override
    public void add(RentOrder rentOrder) {
        Connection connection = openConnection();
        try {
            String sql = "INSERT INTO rent_orders(rent_order_id,car_id,user_id,expected_delivery_date,rent_date,price,observations,free_upgrade) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, rentOrder.getId());
            pstmt.setString(2, rentOrder.getCar().getId());
            pstmt.setString(3, rentOrder.getUser().getId());
            pstmt.setLong(4, rentOrder.getExpectedDeliveryDate().toEpochMilli());
            pstmt.setLong(5, rentOrder.getRentDate().toEpochMilli());
            pstmt.setFloat(6, rentOrder.getPrice());
            pstmt.setString(7, rentOrder.getObservations());
            pstmt.setBoolean(8, rentOrder.getFreeUpgrade());

            if (pstmt.executeUpdate() == 0) {
                throw new InfrastructureException("The insert wasn't executed!");
            }

        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "insert"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "insert"), e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void modify(RentOrder rentOrder) {
        Connection connection = openConnection();

        try {
            String sql = "UPDATE rent_orders set car_id = ?,user_id = ?,expected_delivery_date = ?,rent_date = ?,price = ?, observations = ?, free_upgrade = ? WHERE rent_order_id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, rentOrder.getCar().getId());
            pstmt.setString(2, rentOrder.getUser().getId());
            pstmt.setLong(3, rentOrder.getExpectedDeliveryDate().toEpochMilli());
            pstmt.setLong(4, rentOrder.getRentDate().toEpochMilli());
            pstmt.setFloat(5, rentOrder.getPrice());
            pstmt.setString(6, rentOrder.getObservations());
            pstmt.setBoolean(7, rentOrder.getFreeUpgrade());
            pstmt.setString(8, rentOrder.getId());


            if (pstmt.executeUpdate() == 0) {
                throw new InfrastructureException("The Update wasn't executed!");
            }
        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "update"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "update"), e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void remove(RentOrder rentOrder) {
        Connection connection = openConnection();

        try {

            String sql = "DELETE FROM rent_orders WHERE rent_order_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, rentOrder.getId());

            if (pstmt.executeUpdate() == 0) {
                throw new InfrastructureException("The delete wasn't executed!");
            }
        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "delete"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "delete"), e);
        }
    }

    @Override
    public Optional<RentOrder> findById(String id) {
        Connection connection = openConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    " SELECT * " +
                            "  FROM " +
                            "     rent_orders ro, cars c, users u " +
                            "  WHERE " +
                            "     ro.car_id = c.car_id " +
                            "     AND ro.user_id = u.user_id " +
                            "     AND rent_order_id=?");
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(buildRentOrder(resultSet));
            }
        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "findById"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "findById"), e);
        } finally {
            closeConnection(connection);
        }

        return Optional.empty();
    }

    @Override
    public Optional<RentOrder> findByCriteria(String field, String criteria) {
        Connection connection = openConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * " +
                            "  FROM " +
                            "     rent_orders ro, cars c, users u " +
                            "  WHERE " +
                            "     ro.car_id = c.car_id " +
                            "     AND ro.user_id = u.user_id " +
                            "     AND " + field + "=?");
            statement.setString(1, criteria);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(buildRentOrder(resultSet));
            }
        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "findById"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "findById"), e);
        } finally {
            closeConnection(connection);
        }

        return Optional.empty();
    }

    @Override
    public List<RentOrder> findAll() {
        List<RentOrder> rentOrders = new ArrayList<RentOrder>();

        Connection conn = openConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement
                    .executeQuery(
                            " SELECT * " +
                                    "  FROM " +
                                    "     rent_orders ro, cars c, users u " +
                                    "  WHERE " +
                                    "     ro.car_id = c.car_id " +
                                    "     AND ro.user_id = u.user_id ");


            while (resultSet.next()) {
                rentOrders.add(buildRentOrder(resultSet));
            }

        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "findAll"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "findAll"), e);
        } finally {
            closeConnection(conn);
        }
        return rentOrders;
    }
}
