package repository;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseRepository<T> implements Repository<T>{

    final static Logger logger = Logger.getLogger(BaseRepository.class);

    protected void closeConnection(Connection conn){
        try {
            conn.close();
        }catch (SQLException e){
            logger.error("Problems during closeConnection connection", e);
        }
    }

    protected Connection openConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentsystemclass?serverTimezone=UTC", "root", "root");
            if (connection == null) {
                logger.error("Failed to make connection!");
            }
        } catch (SQLException e) {
            logger.error("MySQL Connection Failed!", e);
            return null;
        }
        return connection;
    }



}
