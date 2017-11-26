package cn.huanuo.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;

@RestController
public class FileUpdateController {
    private static final Logger logger = LogManager.getLogger(ViewController.class.getName());


    @PostMapping("file")
    public int upload(MultipartFile file) {


        String name = file.getOriginalFilename();


        logger.info(name);

        return 12345;

    }
}
