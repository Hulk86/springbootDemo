package com.hulk.kafkaDemo.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：lchao16
 * @ClassName ：${CLASS_NAME}
 * @date ：2019/1/21 14:39
 * @description：
 * @modified By：
 * @version: 1.0.0.1
 */
//@Component
public class KafkaConsumer {

    // @KafkaListener(topics = "${kafka.consumer.topic}", containerFactory = "kafkaListenerContainerFactory")
    //@KafkaListener(topics = "${kafka.consumer.topic}", containerFactory = "kafkaListenerContainerFactory")
// containerFactory = "kafkaListenerContainerFactory"
    public void listen(List<ConsumerRecord<?, ?>> records) {// Acknowledgment ack
        try {
            for (ConsumerRecord<?, ?> record : records) {
               /* byte[] msg = (byte[])record.value();
                ByteBuffer theBBuffer = ByteBuffer.wrap(msg);

                String msgStr = theBBuffer.toString();*/
                String msgStr = (String) record.value();
                System.out.println("收到数据: " + msgStr);
            }
        } catch (Exception e) {
            //kafka消费者执行异常
            System.out.println("KafkaConsumer listen is error,exception is {}" + e.toString());
        } finally {
            //ack.acknowledge();//手动提交偏移量
        }
    }
}
