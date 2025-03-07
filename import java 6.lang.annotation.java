import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Invoke {
}

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Default {
    Class<?> value();
}

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ToString {
    enum Option { YES, NO }
    Option value() default Option.YES;
}

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {
    Class<?>[] value();
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Two {
    String first();
    int second();
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    String[] value() default {};
}

public class CacheUtil {

    public static <T> T cache(T obj) {
        return (T) Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new CachingInvocationHandler(obj)
        );
    }

    private static class CachingInvocationHandler implements InvocationHandler {
        private final Object target;
        private final Map<Method, Object> cache = new HashMap<>();

        public CachingInvocationHandler(Object target) {
            this.target = target;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getParameterCount() != 0) {
                return method.invoke(target, args);
            }
            if (cache.containsKey(method)) {
                System.out.println("Возвращаем закэшированное значение для метода: " + method.getName());
                return cache.get(method);
            }
            System.out.println("Вычисляем новое значение для метода: " + method.getName());
            Object result = method.invoke(target, args);
            cache.put(method, result);
            return result;
        }
    }

    public static void cacheObjects(Object... objects) {
        for (Object obj : objects) {
            if (obj.getClass().isAnnotationPresent(Cache.class)) {
                Cache cacheAnnotation = obj.getClass().getAnnotation(Cache.class);
                if (cacheAnnotation.value().length == 0) {
                    System.out.println("Кэшируем все методы для объекта: " + obj.getClass().getSimpleName());
                    cache(obj);
                } else {
                    System.out.println("Кэшируем указанные методы для объекта: " + obj.getClass().getSimpleName());
                    System.out.println("Методы для кэширования: " + Arrays.toString(cacheAnnotation.value()));
                }
            }
        }
    }
}


class CacheTest {

    @Test
    void testCache() {
        TestClass testObj = new TestClass();
        
        TestClass cachedObj = CacheUtil.cache(testObj);
        
        int result1 = cachedObj.getValue();
        assertEquals(42, result1);
        
        int result2 = cachedObj.getValue();
        assertEquals(42, result2);
    }
}

class TestClass {
    private int value;

    public TestClass(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Демонстрация работы аннотаций:");
        @Default(String.class)
        class ExampleClass {
            @Invoke
            public void exampleMethod() {
                System.out.println("Метод вызван!");
            }
        }

        ExampleClass example = new ExampleClass();
        example.exampleMethod();
        System.out.println("\nДемонстрация работы кэширования:");
        System.out.print("Введите число для тестирования кэширования: ");
        int inputValue = scanner.nextInt();
        TestClass testObj = new TestClass(inputValue);
        TestClass cachedObj = CacheUtil.cache(testObj);
        System.out.println("Первый вызов: " + cachedObj.getValue());
        System.out.println("Второй вызов: " + cachedObj.getValue());
        System.out.println("\nДемонстрация работы с аннотацией @Cache:");
        @Cache({"getValue"})
        class CachedClass {
            private int value;
            public CachedClass(int value) {
                this.value = value;
            }
            public int getValue() {
                return value;
            }
        }
        System.out.print("Введите число для тестирования аннотации @Cache: ");
        int cacheValue = scanner.nextInt();
        CachedClass cachedClass = new CachedClass(cacheValue);
        CacheUtil.cacheObjects(cachedClass);
        scanner.close();
    }
}
//src/
//├── main/
//│   ├── java/
//│   │   ├── annotations/
//│   │   │   ├── Invoke.java
//│   │   │   ├── Default.java
//│   │   │   ├── ToString.java
//│   │   │   ├── Validate.java
//│   │   │   ├── Two.java
//│   │   │   └── Cache.java
//│   │   ├── util/
//│   │   │   └── CacheUtil.java
//│   │   └── Main.java
//│   └── resources/
//└── test/
//    ├── java/
//    │   └── CacheTest.java
//    └── resources/