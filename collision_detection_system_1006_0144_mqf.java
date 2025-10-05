// 代码生成时间: 2025-10-06 01:44:26
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Objects;

// 定义一个简单的实体类，代表物体
@Entity
@Table(name = "objects")
public class ObjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double x;
    private double y;
    private double radius;

    public ObjectEntity() {}

    public ObjectEntity(String name, double x, double y, double radius) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    // Check if the object intersects with another object
    public boolean intersectsWith(ObjectEntity other) {
        double distance = Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
        return distance < this.radius + other.radius;
    }
}

public class CollisionDetectionSystem {

    private SessionFactory factory;

    public CollisionDetectionSystem() {
        // Create the session factory from hibernate.cfg.xml
        factory = new Configuration().configure().buildSessionFactory();
    }

    // Method to check for collisions
    public List<ObjectEntity> checkCollisions() {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // Query to retrieve all objects from the database
            Query<ObjectEntity> query = session.createQuery("FROM ObjectEntity", ObjectEntity.class);
            List<ObjectEntity> objects = query.getResultList();

            // Check for collisions
            for (int i = 0; i < objects.size(); i++) {
                for (int j = i + 1; j < objects.size(); j++) {
                    if (objects.get(i).intersectsWith(objects.get(j))) {
                        // Handle collision logic here
                        System.out.println("Collision detected between " + objects.get(i).getName() + " and " + objects.get(j).getName());
                    }
                }
            }

            transaction.commit();
            return objects;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    // Close the session factory
    public void close() {
        factory.close();
    }
}
