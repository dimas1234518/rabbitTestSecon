package com.zhevakin.rabbitTest.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    AmqpTemplate template;

    @RequestMapping("/")
    String home() {
        return "Empty mapping";
    }

    @RequestMapping("/emit/error")
    String error() {
        System.out.println("Emit as error");
        template.convertAndSend("error", "Error");
        return "Emit as error";
    }

    @RequestMapping("/emit/info")
    String info() {
        System.out.println("Emit as info");
        template.convertAndSend("info", "Info");
        return "Emit as info";
    }

    @RequestMapping("/emit/warning")
    String warning() {
        System.out.println("Emit as warning");
        template.convertAndSend("warning", "Warning");
        return "Emit as warning";
    }

}
