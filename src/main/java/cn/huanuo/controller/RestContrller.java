package cn.huanuo.controller;

import cn.huanuo.pojo.User;
import cn.huanuo.dao.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class RestContrller {

    private static final Logger logger = LogManager.getLogger(ViewController.class.getName());

    @Autowired
    private UserRepository userRepository;

    @GetMapping("kpi")
    public String index(HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        return "dengerhuan";
    }


    @GetMapping("user")
    public List<User> getUsers(@RequestParam Long limit) {
        logger.info(limit);
        return userRepository.getUsers();
    }


    @GetMapping(value = "user/{id}")
    public List<User> getUser(@PathVariable Long id) {
        logger.info(id);

        return userRepository.getUsers();
    }


    @PostMapping(value = "user")
    public User addUser(@RequestBody User user) {
        logger.info(user.getAge());
        logger.info(user.getName());
        return user;
    }


    @PutMapping(value = "user/{id}")
    public User putUser(@PathVariable Long id, @RequestBody User user) {
        logger.info(id);
        logger.info("put method");
        logger.info(user.getAge());
        logger.info(user.getName());
        return user;
    }

    @DeleteMapping(value = "user/{id}")
    public Long deleteUser(@PathVariable Long id) {
        logger.info("delete method");
        return id;
    }
}
