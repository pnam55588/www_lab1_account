package fit.iuh.test;

import fit.iuh.db.JdbcConnection;
import fit.iuh.models.Account;
import fit.iuh.models.Log;
import fit.iuh.models.Role;
import fit.iuh.repositories.AccountRepository;
import fit.iuh.repositories.GrantAccessRepository;
import fit.iuh.repositories.LogRepository;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        LogRepository logRepository = new LogRepository();
        GrantAccessRepository grantAccessRepository = new GrantAccessRepository();
        logRepository.updateLogoutTime("met",LocalDateTime.now());

        AccountRepository accountRepository = new AccountRepository();
        List<Account> accounts = accountRepository.getAll();
        accounts.forEach(account -> {
            System.out.println(account);
        });
    }
}
