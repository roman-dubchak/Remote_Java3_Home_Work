package Lesson7_Annotations.ExampleService;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Init {
    boolean suppressException() default false;
}
