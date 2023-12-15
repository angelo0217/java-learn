package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunByName {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class clazz = Test.class;
        Object bObj = clazz.newInstance();

        Method sumInstanceMethod  = clazz.getDeclaredMethod("test");
        sumInstanceMethod.invoke(bObj);
    }
}
