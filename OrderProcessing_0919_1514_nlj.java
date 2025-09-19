// 代码生成时间: 2025-09-19 15:14:01
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.ArrayList;
import org.hibernate.query.Query;

/**
 * OrderProcessing is a class that handles order processing using Hibernate.
 * It demonstrates a simple order management system, including adding and retrieving orders.
 */
public class OrderProcessing {

    // Hibernate session factory
    private static org.hibernate.SessionFactory sessionFactory;

    // Initialize the session factory
    static {
        try {
            // Create the session factory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Method to add a new order to the database.
     * @param order The order object to be added.
     * @return The saved order object.
     */
    public Order addOrder(Order order) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(order);
            tx.commit();
            return order;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    /**
     * Method to retrieve all orders from the database.
     * @return A list of all order objects.
     */
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Order");
            orders = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return orders;
    }

    // Example usage of the OrderProcessing class
    public static void main(String[] args) {
        OrderProcessing orderProcessing = new OrderProcessing();
        Order order = new Order();
        order.setOrderDate("2023-04-01");
        order.setOrderValue(1000.00);

        // Adding an order
        orderProcessing.addOrder(order);

        // Retrieving all orders
        List<Order> allOrders = orderProcessing.getAllOrders();
        for (Order o : allOrders) {
            System.out.println("Order ID: " + o.getId() + ", Order Date: " + o.getOrderDate() + ", Order Value: " + o.getOrderValue());
        }
    }
}