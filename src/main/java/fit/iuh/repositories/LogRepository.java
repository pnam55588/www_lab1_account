package fit.iuh.repositories;

import fit.iuh.db.JdbcConnection;
import fit.iuh.models.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class LogRepository {

    private Connection connection;
    public LogRepository(){
        connection = JdbcConnection.getInstance().getConnection();
    }
    public boolean insert(Log log) {
        String insertQuery = "INSERT INTO log (account_id, notes) VALUES (?,  ?)";

        try (
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery)
        ) {
            insertStatement.setString(1, log.getAccountId());
            insertStatement.setString(2, log.getNotes());

            int rowsAffected = insertStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String deleteQuery = "DELETE FROM log WHERE id = ?";

        try (
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)
        ) {
            deleteStatement.setInt(1, id);

            int rowsAffected = deleteStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Log log) {
        String updateQuery = "UPDATE log SET login_time = ?, logout_time = ?, notes = ? WHERE id = ?";

        try (
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery)
        ) {
            updateStatement.setObject(1, log.getLoginTime());
            updateStatement.setObject(2, log.getLogoutTime());
            updateStatement.setString(3, log.getNotes());
            updateStatement.setInt(4, log.getId());

            int rowsAffected = updateStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateLogoutTime(String accountId, LocalDateTime newLogoutTime) {
        String updateQuery = "UPDATE log SET logout_time = ? WHERE account_id = ? " +
                "AND id = (SELECT id FROM log WHERE account_id = ? ORDER BY login_time DESC LIMIT 1)";

        try (

                PreparedStatement updateStatement = connection.prepareStatement(updateQuery)
        ) {
            updateStatement.setObject(1, newLogoutTime);
            updateStatement.setString(2, accountId);
            updateStatement.setString(3, accountId);

            int rowsAffected = updateStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
