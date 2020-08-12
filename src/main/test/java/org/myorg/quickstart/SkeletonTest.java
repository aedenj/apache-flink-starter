package org.myorg.quickstart.testing;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import org.apache.flink.runtime.metrics.DescriptiveStatisticsHistogram;
import org.apache.flink.util.Collector;
import org.junit.Before;
import org.junit.Test;

public class SkeletonTest {
  @Test
  public void TrueIsTrue() {
    assertThat(true).isEqualTo(true);
  }
}
