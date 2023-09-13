package fit.iuh.repositories;

import fit.iuh.db.JdbcConnection;
import fit.iuh.models.GrantAccess;
import fit.iuh.models.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GrantAccessRepository {
    private Connection connection;
    public GrantAccessRepository(){
        connection = JdbcConnection.getInstance().getConnection();
    }
    public boolean insert(GrantAccess grantAccess) {
        String insertQuery = "INSERT INTO grant_access (account_id, role_id, is_grant, note) VALUES (?, ?, ?, ?)";

        try (
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery)
        ) {
            insertStatement.setString(1, grantAccess.getAccountId());
            insertStatement.setString(2, grantAccess.getRoleId());
            insertStatement.setBoolean(3, grantAccess.isGrant());
            insertStatement.setString(4, grantAccess.getNote());

            int rowsAffected = insertStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(String accountId, String roleId) {
        String deleteQuery = "DELETE FROM grant_access WHERE account_id = ? AND role_id = ?";

        try (

                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)
        ) {
            deleteStatement.setString(1, accountId);
            deleteStatement.setString(2, roleId);

            int rowsAffected = deleteStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(GrantAccess grantAccess) {
        String updateQuery = "UPDATE grant_access SET is_grant = ?, note = ? WHERE account_id = ? AND role_id = ?";

        try (

                PreparedStatement updateStatement = connection.prepareStatement(updateQuery)
        ) {
            updateStatement.setBoolean(1, grantAccess.isGrant());
            updateStatement.setString(2, grantAccess.getNote());
            updateStatement.setString(3, grantAccess.getAccountId());
            updateStatement.setString(4, grantAccess.getRoleId());

            int rowsAffected = updateStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Role getRoleAccount(String accountId) {
        String updateQuery = "SELECT r.role_id, r.role_name, r.description, r.`status` \n" +
                "FROM grant_access ga JOIN role r ON ga.role_id = r.role_id \n" +
                "WHERE ga.account_id = '"+accountId+"'";

        try (
                PreparedStatement selectStatement  = connection.prepareStatement(updateQuery)
        ) {
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getString("role_id"));
                role.setName(resultSet.getString("role_name"));
                role.setDescription(resultSet.getString("description"));
                role.setStatus(resultSet.getInt("status"));
                return role;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
