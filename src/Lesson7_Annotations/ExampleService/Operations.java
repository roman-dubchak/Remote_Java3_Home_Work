package Lesson7_Annotations.ExampleService;

import com.sun.corba.se.spi.activation.Server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Operations {
    private static final Map <String, Object> serviceMap = new HashMap<>();


    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException {
//        inspectService(SimpleService.class);
//        inspectService(LazyService.class);
////        inspectService(String.class);
        laodSrevice("Lesson7_Annotations.ExampleService.SimpleService");
        laodSrevice("Lesson7_Annotations.ExampleService.LazyService");
        laodSrevice("java.lang.String");
        System.out.println(serviceMap.get("Lesson7_Annotations.ExampleService.SimpleService").getClass());
        System.out.println(serviceMap.get("Lesson7_Annotations.ExampleService.LazyService").getClass());
        System.out.println(serviceMap.get("java.lang.String"));
    }

    static void laodSrevice (String className) throws ClassNotFoundException, InstantiationException,
                                                IllegalAccessException, NoSuchMethodException
    {
        Class<?> clazz = Class.forName(className);
        if (clazz.isAnnotationPresent(Service.class)) {
            Object serviceObj = clazz.newInstance();
            serviceMap.put(className, serviceObj);

            Method[] met = clazz.getDeclaredMethods();
            Service s = clazz.getAnnotation(Service.class); // достаем метод лейзи лоад
            if (!s.lazyLoad()) { // не вызываем если лейзи лоад
                for (Method method : met) {
                    method.setAccessible(true); // вызваем приватные методы
                    if (method.isAnnotationPresent(Init.class)) {
                        try {
                            method.invoke(serviceObj);
                        } catch (Exception e) {
                            Init ann = method.getDeclaredAnnotation(Init.class);
                            if (ann.suppressException()) {
                                System.err.println(e.getMessage());
                            } else
                                throw new RuntimeException();
                        }
                    }
                }
            }
        }
    }


    static void inspectService (Class<?> service){
        if (service.isAnnotationPresent(Service.class)){
            Service ann = service.getAnnotation(Service.class);
            System.out.println("Service with annotations is " + ann.name());
        } else System.out.println("NO Service annotations " + service.getSimpleName());

//        Method[] met = service.getDeclaredMethods(); // Массив с методами
        for (Method o : service.getDeclaredMethods()) {
//            Init in = o.getAnnotation(Init.class); // забираем аннотацию
            if (o.isAnnotationPresent(Init.class)){
                System.out.println("Init is methods " + o.getName() );
            } else System.out.println("NO Init in " + o.getName());
        }
    }

}

