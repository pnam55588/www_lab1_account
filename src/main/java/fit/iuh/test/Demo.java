package fit.iuh.test;

import fit.iuh.db.JdbcConnection;
import fit.iuh.repositories.AccountRepository;

import java.sql.Connection;

public class Demo {
    public static void main(String[] args) {
        AccountRepository accountRepository = new AccountRepository();
        System.out.println(accountRepository.findByEmail("met@gmail.com"));
    }
}
