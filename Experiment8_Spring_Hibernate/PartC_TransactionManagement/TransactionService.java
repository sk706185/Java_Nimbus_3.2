import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

public class TransactionService {
    private SessionFactory sessionFactory;

    public TransactionService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void transferMoney(int fromId, int toId, double amount) {
        Session session = sessionFactory.getCurrentSession();
        Account from = session.get(Account.class, fromId);
        Account to = session.get(Account.class, toId);

        if (from != null && to != null && from.getBalance() >= amount) {
            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);
            System.out.println("Transfer Successful!");
        } else {
            throw new RuntimeException("Transaction failed: insufficient funds or account missing.");
        }
    }
}
