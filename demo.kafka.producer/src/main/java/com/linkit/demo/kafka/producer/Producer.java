package com.linkit.demo.kafka.producer;


import com.generated.avro.demo.JsonRequest;
import org.apache.avro.generic.GenericRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger logger = Logger.getLogger(Producer.class);

    @Value("${kafka.topic.name}")
    private String TOPIC;

    private KafkaTemplate<String, GenericRecord> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, GenericRecord> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(JsonRequest message) {
        logger.info(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }
}
