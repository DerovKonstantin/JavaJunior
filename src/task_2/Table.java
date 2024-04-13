package task_2;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // служебные аннотации
@Target(ElementType.TYPE) // служебные аннотации
public @interface Table { // класс аннотация
// Когда мы будем вешать аннотацию на какой либо тип, сможем указать некоторое значение которе этот метод будет возвращять
    String name();

}
