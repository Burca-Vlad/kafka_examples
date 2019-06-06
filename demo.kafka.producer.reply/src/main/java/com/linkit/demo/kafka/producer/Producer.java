package com.linkit.demo.kafka.producer;


import com.generated.avro.demo.JsonRequest;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class Producer {

    private static final Logger logger = Logger.getLogger(Producer.class);

    @Value("${kafka.topic.name}")
    private String TOPIC;

    private ReplyingKafkaTemplate<String, JsonRequest, JsonRequest> kafkaTemplate;

    @Autowired
    public Producer(ReplyingKafkaTemplate<String, JsonRequest, JsonRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(JsonRequest message) throws ExecutionException, InterruptedException {
        logger.info(String.format("#### -> Producing message -> %s", message));
        ProducerRecord<String, JsonRequest> record = new ProducerRecord<>(TOPIC, message);
        RequestReplyFuture<String, JsonRequest, JsonRequest> requestReplyFuture = this.kafkaTemplate.sendAndReceive(record);
        logger.info("Received reply: " + requestReplyFuture.get().value().toString());
    }
}
