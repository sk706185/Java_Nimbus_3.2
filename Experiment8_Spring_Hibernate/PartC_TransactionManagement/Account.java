import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    private int id;
    private String name;
    private double balance;

    public Account() {}

    public Account(int id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getBalance() { return balance; }

    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + balance;
    }
}
