import Lesson6_Log.MethodsHW;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class MethodsHWTest {
    private static MethodsHW methods = null;
    int[] a;
    int[] b;

    public MethodsHWTest (int[] a, int[] b) {
        this.a = a;
        this.b = b;
    }

    @Parameters
    public static Collection collTest(){
        int[] c = {5,6};
        int[] d = {1,2,3,4,5,6};
        int[] f = {5,7};
        int[] g = {2,3,4,5,6};
        int[] s = {6};
        int[] k = {2,3,4,6};
        return Arrays.asList(new Object[][]{
                {c,d}, //
                {f,g},
                {s,k}
                }
        );
    }

    @Test
    public void testArraysAfterFor (){
        Assert.assertArrayEquals(a, methods.arraysFor(b));
    }

    @Test
    public void testArraysBoolOneFour (){
        Assert.assertTrue(methods.arryaChekNumber(b));
    }

    @Test
    public void testArraysBoolOneFourFalse (){
        Assert.assertFalse(methods.arryaChekNumber(b));
    }

    @Before
    public void init () {
        System.out.println("init methods");
        methods = new MethodsHW();
    }

    @After
    public void tearDown () throws Exception {
        methods = null;
    }
}