package de.ad.android.tools.lint;

import com.android.tools.lint.detector.api.Issue;
import java.util.List;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomIssueRegistryTest {
  @Test
  public void testGetIssues() throws Exception {
    CustomIssueRegistry customIssueRegistry = new CustomIssueRegistry();

    List<Issue> actual = customIssueRegistry.getIssues();

    assertThat(actual).containsExactly(HelloWorldDetector.ISSUE);
  }
}