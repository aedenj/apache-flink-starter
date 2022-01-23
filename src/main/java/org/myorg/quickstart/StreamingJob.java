/**
 * Something profound.
 */
package org.myorg.quickstart;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.base.DeliveryGuarantee;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Starting point for a Flink streaming job using the DataStream API.
 */
public final class StreamingJob {
    private StreamingJob() {
        // prevents calls from subclass
        throw new UnsupportedOperationException();
    }

    public static void main(final String[] args) throws Exception {
        final JobConfig config = JobConfig.create();
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        final KafkaSource<String> source = KafkaSource
                .<String>builder()
                .setBootstrapServers(config.brokers())
                .setTopics("source")
                .setValueOnlyDeserializer(new SimpleStringSchema())
                .setProperties(config.consumer())
                .build();

        final KafkaSink<String> sink = KafkaSink
                .<String>builder()
                .setBootstrapServers(config.brokers())
                .setRecordSerializer(
                    KafkaRecordSerializationSchema.builder()
                        .setTopic("destination")
                        .setValueSerializationSchema(new SimpleStringSchema())
                        .build()
                )
                .setDeliverGuarantee(DeliveryGuarantee.NONE)
                .setKafkaProducerConfig(config.producer())
                .build();

        env.fromSource(source, WatermarkStrategy.noWatermarks(), "Source Topic")
            .sinkTo(sink)
            .name("Destination Topic");

        env.execute("Kafka Experiment");
    }
}
