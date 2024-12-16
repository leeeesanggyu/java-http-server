package annotation.validator;

import java.lang.reflect.Field;

public class Validator {

    public static void validate(Object object) throws Exception {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(NotEmpty.class)) {
                String value = (String) field.get(object);
                NotEmpty annotation = field.getAnnotation(NotEmpty.class);

                if (value == null || value.isEmpty()) {
                    throw new RuntimeException(annotation.message());
                }
            }

            if (field.isAnnotationPresent(Range.class)) {
                long value = field.getLong(object);
                Range annotation = field.getAnnotation(Range.class);

                if (value < annotation.min() || value > annotation.max()) {
                    throw new RuntimeException(annotation.message());
                }
            }
        }
    }
}
