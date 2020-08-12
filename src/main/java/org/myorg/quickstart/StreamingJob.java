package org.myorg.quickstart;

import java.util.Properties;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

public class StreamingJob {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "broker-1:19092,broker-2:19093,broker-3:19094");
        FlinkKafkaConsumer<String> myConsumer = new FlinkKafkaConsumer<>(
          "messages",
          new SimpleStringSchema(),
          properties
        );

        DataStream<String> raw = env.addSource(myConsumer).name("Raw Messages Topic");

        FlinkKafkaProducer<String> enriched = new FlinkKafkaProducer<>(
          "enriched-messages",
          new SimpleStringSchema(),
          properties
        );

        raw.addSink(enriched).name("Enriched Messages Topic");

        env.execute("Kafka Experiment");
    }
}
