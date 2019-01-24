package com.hulk.kafkaDemo.productor;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author ：lchao16
 * @ClassName ：${CLASS_NAME}
 * @date ：2019/1/21 14:08
 * @description：
 * @modified By：
 * @version: 1.0.0.1
 */
@Component
public class kafkaProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;
   // @Autowired
 //   private KafkaProducerConfig kafkaProducerConfig;

    public void send(String gapsMessage){
        try {
            /*
            //序列化
            byte[] message = ProtoStuffSerializeUtils.serialize(gapsMessage, GapsMessage.class);
            if (message == null) {
                return;
            }
            */
           // byte[] message = gapsMessage.getBytes();
           String message = gapsMessage;
            //发送消息
            ListenableFuture future = kafkaTemplate.send("test0811", message);

            //异步监听回调
            kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
                @Override
                public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata recordMetadata) {
                    //开发调试使用
                    System.out.println("11111111ttttttt");
                }

                @Override
                public void onError(String topic, Integer partition, String key,  String value, Exception exception) {
                    System.out.println("send error topic: {} ," + topic + " partition:" + partition + " key:" + key
                            + " exception:" + exception.getMessage());
                }

                @Override
                public boolean isInterestedInSuccess() {
                    return false;
                }
            });
        }catch (Exception e){
            //kafka生产者异常
            System.out.println("KafkaProducer send is error,exception is {}"+ e.toString());
            e.printStackTrace();
        }
    }
}
