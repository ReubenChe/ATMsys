import java.io.Serializable;
/*@desc
@author Bings
@date 2024/7/31 21:44*/

public class User implements Serializable {
    private String account;
    private String password;
    private double balance;

    public User(String account, String password, double balance) {
        this.account = account;
        this.password = password;
        this.balance = balance;
    }

    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}

