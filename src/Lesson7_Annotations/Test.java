package Lesson7_Annotations;
import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {

    String nameTest();
    int priority() default Priority.NORMAL;
}
