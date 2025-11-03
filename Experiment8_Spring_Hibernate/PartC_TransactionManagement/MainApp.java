import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SessionFactory sessionFactory = context.getBean(SessionFactory.class);
        TransactionService service = context.getBean(TransactionService.class);

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(new Account(1, "Alice", 5000));
            session.saveOrUpdate(new Account(2, "Bob", 3000));
            tx.commit();
        }

        try {
            service.transferMoney(1, 2, 1500);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
