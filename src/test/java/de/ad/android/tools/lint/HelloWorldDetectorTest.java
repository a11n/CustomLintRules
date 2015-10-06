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
    /*
    The following code demonstrates assertion of inline resources.
    Use lintProject() along with xml() or java().
    
    Note: The name must correspond to a valid Android project structure!
    
    Example: If you are testing a layout xml file it must be placed in
             something like src/main/res/layout.
     */
    assertEquals("No warnings.", lintProject(
        xml("AndroidManifest.xml", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
            + "<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"\n"
            + "    package=\"de.ad.test\" >\n"
            + "\n"
            + "    <application\n"
            + "        android:allowBackup=\"true\"\n"
            + "        android:icon=\"@mipmap/ic_launcher\"\n"
            + "        android:label=\"Hello world\"\n"
            + "        android:theme=\"@style/AppTheme\" >\n"
            + "        <activity\n"
            + "            android:name=\".MainActivity\"\n"
            + "            android:label=\"@string/app_name\" >\n"
            + "            <intent-filter>\n"
            + "                <action android:name=\"android.intent.action.MAIN\" />\n"
            + "\n"
            + "                <category android:name=\"android.intent.category.LAUNCHER\" />\n"
            + "            </intent-filter>\n"
            + "        </activity>\n"
            + "    </application>\n"
            + "\n"
            + "</manifest>")));
  }

  public void testShouldDetectWarning() throws Exception {
    /*
    The following code demonstrates assertion of external resources.
    Use lintFiles() with a list of files from your test's resources.
    
    The '=>' operator could be utilized to copy files to a certain
    destination during analysis.
    
    Note: The name must correspond to a valid Android project structure!
    
    Example: If you are testing a layout xml file it must be placed in
             something like src/main/res/layout.
    */
    assertEquals(
        "AndroidManifest.xml:8: Information: Unexpected title \"@string/app_name\". Should be \"Hello world.\". [HelloWorld]\n"
            + "        android:label=\"@string/app_name\"\n"
            + "        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            + "0 errors, 1 warnings\n",
        lintFiles("InvalidAndroidManifest.xml=>AndroidManifest.xml"));
  }
}
