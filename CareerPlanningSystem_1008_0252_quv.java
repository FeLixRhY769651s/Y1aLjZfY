// 代码生成时间: 2025-10-08 02:52:27
package com.careerplanning;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class CareerPlanningSystem {
    // Establishing a connection to the database
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Adding a career plan
    public void addCareerPlan(CareerPlan careerPlan) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(careerPlan);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Querying career plans
    public List<CareerPlan> queryCareerPlans() {
        List<CareerPlan> careerPlans = null;
        Session session = sessionFactory.openSession();
        try {
            careerPlans = session.createQuery("from CareerPlan").list();
        } finally {
            session.close();
        }
        return careerPlans;
    }

    // Updating a career plan
    public void updateCareerPlan(CareerPlan careerPlan) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(careerPlan);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Deleting a career plan
    public void deleteCareerPlan(CareerPlan careerPlan) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(careerPlan);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void main(String[] args) {
        CareerPlanningSystem system = new CareerPlanningSystem();
        // Create a new CareerPlan object
        CareerPlan careerPlan = new CareerPlan();
        careerPlan.setName("Software Engineer");
        careerPlan.setDescription("Develops software applications.");

        // Add career plan
        system.addCareerPlan(careerPlan);

        // Query career plans
        List<CareerPlan> careerPlans = system.queryCareerPlans();
        for (CareerPlan plan : careerPlans) {
            System.out.println(plan.getName() + ": " + plan.getDescription());
        }
    }
}

/*
 * CareerPlan.java
 * 
 * Represents a career plan in the system.
 */
package com.careerplanning;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CareerPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
