// 代码生成时间: 2025-09-19 01:21:07
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Random;

// TestDataGenerator class generates sample data using Hibernate
public class TestDataGenerator {

    private static final int RECORDS_TO_CREATE = 100; // Number of records to generate
    private static final Random random = new Random();

    // Entity class for demonstration purposes
    public static class User {
        private Long id;
        private String name;
        private int age;
# 改进用户体验

        public User() {}

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
    }

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            for (int i = 0; i < RECORDS_TO_CREATE; i++) {
                User user = new User("User_" + (i + 1), 18 + random.nextInt(80)); // Random age between 18 and 98
                session.save(user);
            }

            session.getTransaction().commit();
            System.out.println("Test data generated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
