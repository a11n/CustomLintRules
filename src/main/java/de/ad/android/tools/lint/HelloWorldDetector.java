package de.ad.android.tools.lint;

import com.android.annotations.NonNull;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.XmlContext;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import static com.android.SdkConstants.ANDROID_MANIFEST_XML;
import static com.android.SdkConstants.ATTR_LABEL;
import static com.android.SdkConstants.TAG_APPLICATION;

/**
 * Check which determines if application title equals "Hello world"
 */
public class HelloWorldDetector extends Detector
    implements Detector.XmlScanner {

  public static final Issue ISSUE = Issue.create(
      "HelloWorld",                                        //ID
      "Unexpected application title",                      //brief description
      "The application title should state 'Hello world'",  //explanation
      Category.CORRECTNESS,                                //category
      5,                                                   //priority
      Severity.INFORMATIONAL,                              //severity
      new Implementation(                                  //implementation
          HelloWorldDetector.class,                        //detector
          Scope.MANIFEST_SCOPE                             //scope
      )
  );

  private static final String TITLE = "Hello world";

  @Override
  public boolean appliesTo(@NonNull Context context, @NonNull File file) {
    return file.getName().equals(ANDROID_MANIFEST_XML);
  }

  @Override public Collection<String> getApplicableAttributes() {
    return Arrays.asList(ATTR_LABEL);
  }

  @Override public void visitAttribute(@NonNull XmlContext context,
      @NonNull Attr attribute) {
    //only check 'label' attribute of <application>-tag
    String tagName = attribute.getOwnerElement().getTagName();
    if (!TAG_APPLICATION.equals(tagName)) {
      return;
    }

    String value = attribute.getValue();

    if (!TITLE.equals(value)) {
      context.report(ISSUE, attribute, context.getLocation(attribute),
          String.format(
              "Unexpected title \"%1$s\". Should be \"Hello world.\".", value));
    }
  }
}
