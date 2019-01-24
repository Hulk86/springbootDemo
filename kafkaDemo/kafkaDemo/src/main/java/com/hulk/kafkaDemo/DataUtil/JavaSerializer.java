package com.hulk.kafkaDemo.DataUtil;

import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

/**
 * @author ：lchao16
 * @ClassName ：${CLASS_NAME}
 * @date ：2019/1/21 15:50
 * @description：
 * @modified By：
 * @version: 1.0.0.1
 */
public class JavaSerializer implements Serializer<Object> {

        @Override
        public byte[] serialize(String topic, Object data) {
            try {
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
                objectStream.writeObject(data);
                objectStream.flush();
                objectStream.close();
                return byteStream.toByteArray();
            }
            catch (IOException e) {
                throw new IllegalStateException("Can't serialize object: " + data, e);
            }
        }

        @Override
        public void configure(Map<String, ?> configs, boolean isKey) {

        }

        @Override
        public void close() {

        }


}
