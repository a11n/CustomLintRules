package de.ad.android.tools.lint;

import com.android.tools.lint.checks.infrastructure.LintDetectorTest;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class HelloWorldDetectorTest extends LintDetectorTest {

  @Override protected Detector getDetector() {
    return new HelloWorldDetector();
  }

  @Override protected List<Issue> getIssues() {
    return Arrays.asList(HelloWorldDetector.ISSUE);
  }

  @Override protected InputStream getTestResource(String relativePath, boolean expectExists) {
    String path = relativePath; //$NON-NLS-1$
    InputStream stream = this.getClass().getClassLoader().getResourceAsStream(path);
    if (!expectExists && stream == null) {
      return null;
    }
    return stream;
  }

  public void testShouldDetectNoWarning() throws Exception {
    assertEquals("No warnings.", lintFiles("ValidAndroidManifest.xml=>AndroidManifest.xml"));
  }

  public void testShouldDetectWarning() throws Exception {
    assertEquals("AndroidManifest.xml:8: Information: Unexpected title \"@string/app_name\". Should be \"Hello world.\". [HelloWorld]\n"
        + "        android:label=\"@string/app_name\"\n"
        + "        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
        + "0 errors, 1 warnings\n", lintFiles("InvalidAndroidManifest.xml=>AndroidManifest.xml"));
  }
}
