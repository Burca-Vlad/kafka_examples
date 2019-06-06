package com.linkit.datarest.kafka.configurations;

import com.generated.avro.demo.JsonRequest;
import com.linkit.datarest.kafka.deserializer.AvroDeserializer;
import com.linkit.datarest.serializer.AvroSerializer;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import io.confluent.kafka.serializers.subject.TopicRecordNameStrategy;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfiguration {

    private Map<String, Object>  getProperties(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, AvroDeserializer.class.getName());
        props.put(AbstractKafkaAvroSerDeConfig.AUTO_REGISTER_SCHEMAS, true);
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id_1");
        props.put(KafkaAvroSerializerConfig.VALUE_SUBJECT_NAME_STRATEGY, TopicRecordNameStrategy.class.getName());

        return props;
    }

    private ConsumerFactory<String, JsonRequest> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(getProperties(), new StringDeserializer(),
                new AvroDeserializer<>(JsonRequest.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, JsonRequest> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, JsonRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setReplyTemplate(getTemplate());
        return factory;
    }

    private KafkaTemplate<String, JsonRequest> getTemplate(){
        return new KafkaTemplate<>(getProducer());
    }

    private ProducerFactory<String, JsonRequest> getProducer(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AvroSerializer.class.getName());
        props.put(AbstractKafkaAvroSerDeConfig.AUTO_REGISTER_SCHEMAS, true);
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        props.put(KafkaAvroSerializerConfig.VALUE_SUBJECT_NAME_STRATEGY, TopicRecordNameStrategy.class.getName());
        return new DefaultKafkaProducerFactory<>(props);
    }
}
