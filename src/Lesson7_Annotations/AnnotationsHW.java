package Lesson7_Annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AnnotationsHW {
    private static Method beforeMeth = null;
    private static List<Method> testMethods = new LinkedList<>();
    private static Method afterMeth = null;

    public static void main(String[] args) {
        start(TestClass.class);
    }

    static void start (Class<?> testClass){
        sortMet(testClass);
    }

    private static void sortMet(Class<?> test){
        Method[] methods = test.getDeclaredMethods();
        List <Method> beforeList = new ArrayList<>();
        List <Method> testList = new ArrayList<>();
        List <Method> afterList = new ArrayList<>();
        for(Method m: methods){
            m.setAccessible(true);
            if (m.isAnnotationPresent(BeforeSuite.class)) beforeList.add(m);
            if (m.isAnnotationPresent(Test.class)) testList.add(m);
            if (m.isAnnotationPresent(AfterSuite.class)) afterList.add(m);
        }
        if (beforeList.size() != 1 || afterList.size() != 1) throw new RuntimeException();
        beforeMeth = beforeList.get(0);
        beforeMeth.setAccessible(true);
        afterMeth = afterList.get(0);
        afterMeth.setAccessible(true);
        for (int prop = Priority.MAX; prop >= Priority.MIN ; prop--) {
            for (Method m : testList){
                if (m.getAnnotation(Test.class).priority() == prop){
                    m.setAccessible(true);
                    testMethods.add(m);
                }
            }
        }
    }
    private static void invokeMethods(Class<?> testClass){
        Method[] methods = testClass.getDeclaredMethods();
        for(Method m : methods){
            m.setAccessible(true);
            m.invoke(testClass,)
        }
    }
}
