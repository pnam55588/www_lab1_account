package fit.iuh.repositories;

import fit.iuh.db.JdbcConnection;
import fit.iuh.models.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private Connection connection;
    public AccountRepository(){
        this.connection = JdbcConnection.getInstance().getConnection();
    }
    public List<Account> getAll(){
        String sql = "select * from account where status = 1";
        List<Account> accounts = new ArrayList<Account>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                accounts.add(new Account(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6)
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return accounts;
    }
    public Account findByEmail(String email){
        Account account = new Account();
        String sql = "SELECT * FROM account WHERE email = '"+email+"' and status = 1 LIMIT 1";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet =  statement.executeQuery();
            while(resultSet.next()){
                account.setId(resultSet.getString(1));
                account.setFullName(resultSet.getString(2));
                account.setPassword(resultSet.getString(3));
                account.setEmail(resultSet.getString(4));
                account.setPhone(resultSet.getString(5));
                account.setStatus(resultSet.getInt(6));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return account;
    }
    public boolean insert(Account account){
        String sql = "insert into account values(?,?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,account.getId());
            statement.setString(2,account.getFullName());
            statement.setString(3,account.getPassword());
            statement.setString(4,account.getEmail());
            statement.setString(5,account.getPhone());
            statement.setInt(6,account.getStatus());
            int n = statement.executeUpdate();
            return n>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean update(Account account){
        String updateQuery = "UPDATE accounts SET full_name = ?, password = ?, email = ?, phone = ?, status = ? WHERE account_id = ?";

        try (
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery)
        ) {
            updateStatement.setString(1, account.getFullName());
            updateStatement.setString(2, account.getPassword());
            updateStatement.setString(3, account.getEmail());
            updateStatement.setString(4, account.getPhone());
            updateStatement.setInt(5, account.getStatus());
            updateStatement.setString(6, account.getId());

            int rowsAffected = updateStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean delete(String id) {
        String deleteQuery = "DELETE FROM accounts WHERE account_id = ?";

        try (
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)
        ) {
            deleteStatement.setString(1, id);
            int rowsAffected = deleteStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }
}
