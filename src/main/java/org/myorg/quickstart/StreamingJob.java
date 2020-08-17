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
        properties.setProperty("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
        properties.setProperty("group.id", "test");

        final FlinkKafkaConsumer<String> myConsumer = new FlinkKafkaConsumer<>(
            "messages",
            new SimpleStringSchema(),
            properties
        );

        final DataStream<String> msgs = env.addSource(myConsumer).name("Raw Messages Topic");

        final FlinkKafkaProducer<String> enriched = new FlinkKafkaProducer<>(
            "enriched-messages",
            new SimpleStringSchema(),
            properties
        );

        msgs.addSink(enriched).name("Enriched Messages Topic");

        env.execute("Kafka Experiment");
    }
}
