package Lesson7_Annotations;

public class TestClass {
    private static TestClass instance;

    private TestClass(){
    }

    @BeforeSuite
    public static TestClass begin(){
        if (instance == null){
            instance = new TestClass();
            System.out.println("BeforeSuite");
        }
        return instance;
    }

    @Test(nameTest = "Test1", priority = Priority.MIN)
    private void test1(){
        System.out.println("Start Test1");
    }

    @Test(nameTest = "Test2")
    private void test2(){
        System.out.println("Start Test2");
    }

    @Test(nameTest = "Test3", priority = Priority.MAX)
    private void test3(){
        System.out.println("Start Test3");
    }

    @AfterSuite
    private static void end (){
        System.out.println("AfterSuite");
        instance = null;
    }

}
