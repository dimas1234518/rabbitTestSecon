package com.zhevakin.rabbitTest.listener;

import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;


@EnableRabbit
@Component
public class RabbitMqListener {

  //  Logger logger = Logger.getLogger(RabbitMqListener.class);

    @Autowired
    AmqpTemplate template;

    Random random = new Random();

    @RabbitListener(queues = "query-example-4-1")
    public void workerQueue1(String message) throws InterruptedException {

        String[] messages = message.split(":");
        int firstNumber = Integer.parseInt(messages[0]);
        int secondNumber = Integer.parseInt(messages[1]);
        firstNumber += secondNumber;
        secondNumber++;
        if (secondNumber > 10) return;
        template.convertAndSend("info", firstNumber +":" + secondNumber + ":from right " + message);
        System.out.println("worker 2 " + message);

    }

//    @RabbitListener(queues = "query-example-4-1")
//    public void workerQueue1(String message) throws InterruptedException {
//        System.out.println("worker 1 " + message);
//
//    }
//    @RabbitListener(queues = "query-example-4-2")
//    public void workerQueue2(String message) throws InterruptedException {
//        System.out.println("worker 2 " + message);
//    }
}
