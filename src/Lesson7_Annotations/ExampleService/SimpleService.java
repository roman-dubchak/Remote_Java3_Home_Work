package Lesson7_Annotations.ExampleService;

@Service(name = "SuperSimpleService")
public class SimpleService {

    @Init
    private void initService(){
        System.out.println("Vizov initService");
    }

    public void nonAn(){
        System.out.println("nonInit method");
    }

}
