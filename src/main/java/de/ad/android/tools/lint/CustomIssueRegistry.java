package de.ad.android.tools.lint;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import java.util.Arrays;
import java.util.List;

public class CustomIssueRegistry extends IssueRegistry {
  public CustomIssueRegistry() {
  }

  @Override
  public List<Issue> getIssues() {
    return Arrays.asList(HelloWorldDetector.ISSUE);
  }

}