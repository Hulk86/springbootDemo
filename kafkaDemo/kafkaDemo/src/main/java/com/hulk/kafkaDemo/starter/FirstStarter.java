package com.hulk.kafkaDemo.starter;

import com.hulk.kafkaDemo.productor.kafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

/**
 * @author ：lchao16
 * @ClassName ：${CLASS_NAME}
 * @date ：2019/1/21 14:01
 * @description：模拟发送方，producer
 * @modified By：
 * @version: 1.0.0.1
 */
@Component
@Order(1)
public class FirstStarter implements CommandLineRunner {
    @Autowired
    kafkaProducer kafkaBoss;
    @Override
    public void run(String ...args){
        kafkaBoss.send("11111111111111111111");
        try {
            kafkaBoss.send("11111111111111111111");
            sleep(150L);
            kafkaBoss.send("55555555555555555555");
            sleep(150L);
            kafkaBoss.send("99999999999999999999");
            sleep(150L);
        }
        catch (Exception e)
        {
            ;
        }
    }
}
