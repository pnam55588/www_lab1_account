package fit.iuh.test;

import fit.iuh.db.JdbcConnection;
import fit.iuh.models.Log;
import fit.iuh.models.Role;
import fit.iuh.repositories.AccountRepository;
import fit.iuh.repositories.GrantAccessRepository;
import fit.iuh.repositories.LogRepository;
import java.time.LocalDateTime;
import java.sql.Connection;

public class Demo {
    public static void main(String[] args) {
        LogRepository logRepository = new LogRepository();
        GrantAccessRepository grantAccessRepository = new GrantAccessRepository();
        logRepository.updateLogoutTime("met",LocalDateTime.now());
    }
}
