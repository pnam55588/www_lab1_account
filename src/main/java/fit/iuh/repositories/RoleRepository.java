package fit.iuh.repositories;

import fit.iuh.db.JdbcConnection;
import fit.iuh.models.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoleRepository {
    private  Connection connection;
    public RoleRepository(){
        connection = JdbcConnection.getInstance().getConnection();
    }
    public boolean insert(Role role) {
        String insertQuery = "INSERT INTO role (id, name, description, status) VALUES (?, ?, ?, ?)";

        try (

                PreparedStatement insertStatement = connection.prepareStatement(insertQuery)
        ) {
            insertStatement.setString(1, role.getId());
            insertStatement.setString(2, role.getName());
            insertStatement.setString(3, role.getDescription());
            insertStatement.setInt(4, role.getStatus());

            int rowsAffected = insertStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(String id) {
        String deleteQuery = "DELETE FROM role WHERE id = ?";

        try (

                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)
        ) {
            deleteStatement.setString(1, id);

            int rowsAffected = deleteStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Role role) {
        String updateQuery = "UPDATE role SET name = ?, description = ?, status = ? WHERE id = ?";

        try (
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery)
        ) {
            updateStatement.setString(1, role.getName());
            updateStatement.setString(2, role.getDescription());
            updateStatement.setInt(3, role.getStatus());
            updateStatement.setString(4, role.getId());

            int rowsAffected = updateStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
