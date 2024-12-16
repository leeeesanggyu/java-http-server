package annotation.basic.inherited;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited  // 클래스 상속 시 자식도 어노테이션 적용, 인터페이스는 안됨
@Retention(RetentionPolicy.RUNTIME)
public @interface InheritedAnnotation {
}
