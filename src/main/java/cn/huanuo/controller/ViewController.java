package cn.huanuo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("kpi")
    public String name() {
        System.out.println("kpi");
        return "kpi";
    }
}
