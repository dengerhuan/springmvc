package cn.huanuo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    private static final Logger logger = LogManager.getLogger(ViewController.class.getName());


    @RequestMapping("kpi")
    public String name() {
        logger.info("kpi");
        System.out.println("kpi");
        return "kpi";
    }
}
