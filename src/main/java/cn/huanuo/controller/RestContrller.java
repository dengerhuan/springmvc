package cn.huanuo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestContrller {

    @GetMapping("kpi")
    public String index() {
        return "dengerhuan";
    }
}
