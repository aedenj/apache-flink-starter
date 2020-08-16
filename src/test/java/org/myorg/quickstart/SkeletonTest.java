package org.myorg.quickstart.testing;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SkeletonTest {
  @Test
  public void TheAnswerToLife() {
    assertThat(42).isEqualTo(Integer.sum(19,23));
  }

  @Test
  public void AlwaysRight() {
    assertThat(true).isEqualTo(true);
  }
}
