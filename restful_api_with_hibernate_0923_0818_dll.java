// 代码生成时间: 2025-09-23 08:18:06
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

// 实体类
@Entity
class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    
    // 省略构造函数、Getter和Setter方法
}

// JPA仓库接口
interface UserRepository extends JpaRepository<User, Long> {
    // 可以添加自定义查询方法
}

// RESTful API控制器
@RestController
@RequestMapping("/api/users")
class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            // 错误处理
            throw new RuntimeException("Error fetching users", e);
        }
    }

    // 其他API方法（如POST, PUT, DELETE）可以根据需要添加
}
