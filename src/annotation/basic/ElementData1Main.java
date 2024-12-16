package annotation.basic;

public class ElementData1Main {

    public static void main(String[] args) {
        Class<ElementData1> elementData1Class = ElementData1.class;
        AnnotationElement annotation = elementData1Class.getAnnotation(AnnotationElement.class);

        System.out.println("annotation = " + annotation);
    }
}
