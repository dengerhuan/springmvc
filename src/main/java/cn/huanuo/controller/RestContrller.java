package cn.huanuo.controller;

import cn.huanuo.dao.User;
import cn.huanuo.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestContrller {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("kpi")
    public String index() {
        return "dengerhuan";
    }


    @GetMapping("user")
    public List<User> getUsers() {
        return userRepository.getUsers();
    }
}
