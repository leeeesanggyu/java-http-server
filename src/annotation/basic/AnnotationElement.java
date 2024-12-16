package annotation.basic;

import util.Logger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationElement {
    String value();
    int count() default 0;
    String[] tags() default {};

    Class<? extends Logger>[] annotationData() default Logger.class;
}
