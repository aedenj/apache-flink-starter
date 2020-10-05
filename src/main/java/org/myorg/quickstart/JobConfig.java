/**
 * Provides a wrapper around the configuration for the job.
 */
package org.myorg.quickstart;

import java.util.Properties;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;


final class JobConfig {
  private Config config;

  public JobConfig(Config config) {
      this.config = config;
      // This verifies that the Config is sane and has our
      // reference config. Importantly, we specify the "simple-lib"
      // path so we only validate settings that belong to this
      // library. Otherwise, we might throw mistaken errors about
      // settings we know nothing about.
      config.checkValid(ConfigFactory.defaultReference());
  }

  public JobConfig() {
      this(ConfigFactory.load());
  }

  public Properties kafka() {
      final Properties props = new Properties();

      props.setProperty("bootstrap.servers", config.getString("kafka.endpoints"));
      props.setProperty("group.id", config.getString("kafka.groupId"));

      return props;
  }
}
