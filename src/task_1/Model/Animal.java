package task_1.Model;

import java.util.HashMap;

/** 
 * Абстрактный класс - животное
 * @author --
 * @version 1.0
*/
public abstract class Animal {

    /** поле - имя */
    private String name;
    /** поле - возраст */
    private int age;
    
    /**
     * Конструктор - создание нового объекта (животное)
     * @param name - имя животного
     * @param age - возраст животного
     */
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        
    }

    /**
     * Получение значения поля - имя животного
     */
    public String getName() {
        return name;
    }

    /**
     * Определения значения поля - имя животного
     * @param name - поле имя животного
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получение значения поля - возраст животного
     */
    public int getAge() {
        return age;
    }

    /**
     * Определения значения поля - возраст животного
     * @param age - поле - возраст животного
     */
    public void setAge(int age) {
        this.age = age;
    }
    
    @Override
    public String toString() {
        return String.format("Животное [имя = %s, возраст = %d]", name, age);
    }

}