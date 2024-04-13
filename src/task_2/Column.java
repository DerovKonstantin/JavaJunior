package task_2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) // эту аннотацию можно применять толко к полям в рамках моего типа
public @interface Column { // еще одна аннотация
// в классе определим два метода

    String name(); // имя
    boolean primaryKey() default false; // уникальность

}

