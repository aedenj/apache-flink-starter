/**
 * Empty package comment.
 */
package org.myorg.quickstart;

import java.util.Properties;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

/**
 * Empty class comment.
 */
public final class StreamingJob {
    private StreamingJob() {
        // prevents calls from subclass
        throw new UnsupportedOperationException();
    }

    public static void main(final String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        final Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092, broker-1:19092");
        properties.setProperty("group.id", "test");

        final FlinkKafkaConsumer<String> myConsumer = new FlinkKafkaConsumer<>(
            "source",
            new SimpleStringSchema(),
            properties
        );

        final DataStream<String> msgs = env.addSource(myConsumer).name("Raw Messages Again");

        final FlinkKafkaProducer<String> enriched = new FlinkKafkaProducer<>(
            "destination",
            new SimpleStringSchema(),
            properties
        );

        System.out.println("HELLO JREBEL 2");
        msgs.addSink(enriched).name("Destination Topics");
        env.execute("Kafka Experiment");
    }
}
