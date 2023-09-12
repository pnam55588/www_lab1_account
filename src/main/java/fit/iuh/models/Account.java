package fit.iuh.models;

import java.util.Objects;

public class Account {
//    account_id, full_name, password, email,
//phone, status)
    private String id;
    private String fullName;
    private String password;
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Account(String id, String fullName, String password, int status) {
        this.id = id;
        this.fullName = fullName;
        this.password = password;
        this.status = status;
    }

    public Account() {
    }

    public Account(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
}
