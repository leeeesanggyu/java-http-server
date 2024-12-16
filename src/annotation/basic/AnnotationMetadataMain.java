package annotation.basic;

@AnnotationMetadata // Target: ElementType.TYPE
public class AnnotationMetadataMain {

//    @AnnotationMetadata
    private String id;

    @AnnotationMetadata // Target: ElementType.METHOD
    public void call() {

    }

    public static void main(String[] args) throws NoSuchMethodException {
        AnnotationMetadata classAnnotation = AnnotationMetadataMain.class.getAnnotation(AnnotationMetadata.class);
        System.out.println(classAnnotation);

        AnnotationMetadata methodAnnotation = AnnotationMetadataMain.class
                .getMethod("call")
                .getAnnotation(AnnotationMetadata.class);
        System.out.println(methodAnnotation);
    }
}
