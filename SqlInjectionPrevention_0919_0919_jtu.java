// 代码生成时间: 2025-09-19 09:19:33
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

// 防止SQL注入的基本类
public class SqlInjectionPrevention {

    // 创建SessionFactory对象
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // 配置SessionFactory
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // 错误处理
            System.err.println("Initial SessionFactory creation failed." + " Cause: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void main(String[] args) {
        // 使用try-with-resources语句确保资源自动关闭
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                // 开始事务
                transaction = session.beginTransaction();

                // 模拟防止SQL注入的操作
                preventSqlInjection(session);

                // 提交事务
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    // 回滚事务
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 演示防止SQL注入的方法
    private static void preventSqlInjection(Session session) {
        // 使用参数化查询防止SQL注入
        String sqlInjectionUnsafe = "SELECT * FROM users WHERE username = '" + "admin" + "'"; // 不安全
        String sqlInjectionSafe = "SELECT * FROM users WHERE username = :username"; // 安全

        // 假设username是通过用户输入获得
        String username = "admin'; DROP TABLE users; --"; // 恶意输入尝试SQL注入

        // 使用参数化查询
        Query query = session.createQuery(sqlInjectionSafe).setParameter("username", username);
        List result = query.list();

        // 输出结果
        result.forEach(System.out::println);
    }
}
