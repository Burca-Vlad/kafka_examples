package com.linkit.datarest.kafka.listeners;

import com.generated.avro.demo.JsonRequest;
import com.linkit.datarest.models.MainEntity;
import com.linkit.datarest.repository.MainEntityRepository;
import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class Listener {

    private static final Logger logger = Logger.getLogger(Listener.class);

    private MainEntityRepository mainEntityRepository;

    public Listener(MainEntityRepository mainEntityRepository) {
        this.mainEntityRepository = mainEntityRepository;
    }

    @KafkaListener(topics = "${kafka.topic.name}")
    @SendTo
    public JsonRequest consume(JsonRequest message) {
        logger.info(String.format("#### -> Consumed message -> %s", message));
        MainEntity mainEntity = new MainEntity();
        mainEntity.setName(message.getName().toString());
        mainEntity.setDescription(message.getDescription().toString());
        try {
            mainEntityRepository.save(mainEntity);
        }catch (Exception e){
            logger.info("in error");
            message.setError(e.getMessage());
        }
        return message;
    }
}
