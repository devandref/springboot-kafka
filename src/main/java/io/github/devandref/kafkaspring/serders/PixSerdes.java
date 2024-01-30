package io.github.devandref.kafkaspring.serders;

import io.github.devandref.kafkaspring.model.Pix;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class PixSerdes extends Serdes.WrapperSerde<Pix> {

    public PixSerdes() {
        super(new JsonSerializer<>(), new JsonDeserializer<>(Pix.class));
    }

    public static Serde<Pix> serdes() {
        JsonSerializer<Pix> serializer = new JsonSerializer<>();
        JsonDeserializer<Pix> deserializer = new JsonDeserializer<>(Pix.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

}
