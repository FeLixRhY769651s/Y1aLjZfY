// 代码生成时间: 2025-09-22 07:29:32
package com.example.monitor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadMXBean;
import java.util.List;

/**
 * 系统性能监控工具
 */
public class SystemPerformanceMonitor {

    /**
     * 用于获取Hibernate的SessionFactory
     */
    private static SessionFactory sessionFactory;

    /**
     * 静态代码块，用于初始化SessionFactory
     */
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * 获取内存使用情况
     *
     * @return 内存使用情况
     */
    public static MemoryUsage getMemoryUsage() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        return heapMemoryUsage;
    }

    /**
     * 获取线程使用情况
     *
     * @return 线程使用情况
     */
    public static ThreadMXBean getThreadUsage() {
        return ManagementFactory.getThreadMXBean();
    }

    /**
     * 执行系统性能监控查询
     *
     * @return 包含系统性能数据的列表
     */
    public static List<Object> performSystemPerformanceMonitoring() {
        Session session = null;
        Transaction transaction = null;
        List<Object> performanceData = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // 执行监控查询，此处以获取内存使用情况为例
            MemoryUsage memoryUsage = getMemoryUsage();
            // 可以添加更多监控数据

            performanceData = List.of(memoryUsage);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return performanceData;
    }

    /**
     * 主方法，用于测试系统性能监控工具
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        List<Object> performanceData = performSystemPerformanceMonitoring();
        for (Object data : performanceData) {
            if (data instanceof MemoryUsage) {
                MemoryUsage memoryUsage = (MemoryUsage) data;
                System.out.println("Used Memory: " + memoryUsage.getUsed());
                System.out.println("Max Memory: " + memoryUsage.getMax());
                // 打印更多内存使用详情
            }
            // 处理其他类型的性能数据
        }
    }
}
