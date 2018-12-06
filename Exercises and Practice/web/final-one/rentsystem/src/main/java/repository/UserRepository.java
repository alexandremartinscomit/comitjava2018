package repository;

import domain.User;
import exception.InfrastructureException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository extends BaseRepository<User> {

    private static final String LOG_ERROR_MSG = "Error during the user %s";

    @Override
    public void add(User user) {
        Connection connection = openConnection();

        try {
            String sql = "INSERT INTO users(id,email,password,type) VALUES(?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getType().name());

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

    @Override
    public void modify(User user) {
        Connection connection = openConnection();

        try{
            String sql = "UPDATE users SET email = ?,password = ?,type = ? WHERE id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getType().name());
            pstmt.setString(4, user.getId());


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

    @Override
    public void remove(User user) {
        Connection connection = openConnection();

        try{

            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getId());

            if(pstmt.executeUpdate() == 0){
                throw new InfrastructureException("The delete wasn't executed!");
            }
        } catch (SQLException e){
            logger.error(String.format(LOG_ERROR_MSG, "delete"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "delete"),e);
        }


    }

    @Override
    public Optional<User> findById(String id) {
        Connection connection = openConnection();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id=?");
            statement.setString(1,id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                User.UserType type  = User.UserType.valueOf(resultSet.getString("type"));

                return Optional.of(new User(id, email, password, type));
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
    public Optional<User> findByCriteria(String field, String criteria) {
        Connection connection = openConnection();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE "+field+"=?");
            statement.setString(1,criteria);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String id = resultSet.getString("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                User.UserType type  = User.UserType.valueOf(resultSet.getString("type"));

                return Optional.of(new User(id, email, password, type));
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
    public List<User> findAll() {
        List<User> users = new ArrayList<User>();

        Connection conn = openConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM users");


            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                User.UserType type  = User.UserType.valueOf(resultSet.getString("type"));

                users.add(new User(id, email, password, type));
            }

        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "findAll"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "findAll"),e);
        } finally {
            closeConnection(conn);
        }
        return users;
    }
}
