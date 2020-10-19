package Lesson7_Annotations.ExampleService;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)

public @interface Service {
    String name(); // Св-во аннотации, без дефолта обязательные
    boolean lazyLoad() default false;
}
