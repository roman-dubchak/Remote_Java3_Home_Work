package Lesson7_Annotations.ExampleService;

@Service(name = "ShmazyLazyService")
public class LazyService {

    @Init
    private void lazyInit() throws Exception{
        System.out.println("Vizov lazyInit");
    }
}