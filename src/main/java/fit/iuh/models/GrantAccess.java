package fit.iuh.models;

import java.util.Objects;

public class GrantAccess {
//    (account_id, role_id, is_grant, note
    private  String accountId;
    private String roleId;
    private boolean isGrant;
    private String note;

    public GrantAccess(String accountId) {
        this.accountId = accountId;
    }

    public GrantAccess() {
    }

    public GrantAccess(String accountId, String roleId, boolean isGrant, String note) {
        this.accountId = accountId;
        this.roleId = roleId;
        this.isGrant = isGrant;
        this.note = note;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public boolean isGrant() {
        return isGrant;
    }

    public void setGrant(boolean grant) {
        isGrant = grant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrantAccess that = (GrantAccess) o;
        return Objects.equals(accountId, that.accountId) && Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, roleId);
    }

    @Override
    public String toString() {
        return "GrantAccess{" +
                "accountId='" + accountId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", isGrant=" + isGrant +
                ", note='" + note + '\'' +
                '}';
    }
}
