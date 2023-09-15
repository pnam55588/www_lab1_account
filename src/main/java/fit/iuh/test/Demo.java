package fit.iuh.test;

import fit.iuh.db.JdbcConnection;
import fit.iuh.models.Account;
import fit.iuh.models.GrantAccess;
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
        AccountRepository accountRepository = new AccountRepository();
        logRepository.updateLogoutTime("met",LocalDateTime.now());
//        accountRepository.insert(new Account("t1","t1","t1","t1","t1",1));
        grantAccessRepository.insert(new GrantAccess("t1", "user", true, ""));
    }
}
