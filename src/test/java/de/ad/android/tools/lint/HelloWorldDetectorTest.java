package de.ad.android.tools.lint;

import com.ad.android.tools.lint.Lint;
import com.android.tools.lint.Warning;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldDetectorTest {
  @Rule public Lint lint = new Lint();

  @Test
  public void shouldFindNoIssues() throws Exception {
    lint.setFiles("helloWorld/valid/AndroidManifest.xml");
    lint.setIssues(HelloWorldDetector.ISSUE);

    lint.analyze();

    List<Warning> warnings = lint.getWarnings();

    assertThat(warnings).isEmpty();
  }

  @Test
  public void shouldDetectIssue() throws Exception {
    lint.setFiles("helloWorld/invalid/AndroidManifest.xml");
    lint.setIssues(HelloWorldDetector.ISSUE);

    lint.analyze();

    List<Warning> warnings = lint.getWarnings();

    assertThat(warnings).hasSize(1);

    assertThat(warnings.get(0).message).isEqualTo(
        "Unexpected title \"@string/app_name\". Should be \"Hello world.\".");
    assertThat(warnings.get(0).file.getName()).isEqualTo("AndroidManifest.xml");
    assertThat(warnings.get(0).line).isEqualTo(7);
  }
}
