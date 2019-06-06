package com.linkit.demo.kafka.producer.controller;

import com.generated.avro.demo.JsonRequest;
import com.linkit.demo.kafka.producer.Producer;
import com.linkit.demo.kafka.producer.constants.UrlConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private static final Logger log = Logger.getLogger(KafkaController.class);

    private Producer producer;

    @Autowired
    public KafkaController(Producer producer) {
        this.producer = producer;
    }

    @RequestMapping(method = RequestMethod.POST, path = UrlConstants.kafkaBase, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void sendMessage(@RequestBody JsonRequest jsonRequest){
        producer.sendMessage(jsonRequest);
    }
}
