package task_1.Model;

import task_1.Interface.aAnimal;
import task_1.Interface.iSound;

/** 
 * Класс - собака, наследник класса Animal
 * @author --
 * @version 1.0
*/
@aAnimal
public class Dog extends Animal implements iSound{

    /** поле - идентификатор собаки */
    private int id;
    /** поле - статус - домашние или вьючные животные */
    private String status;

    /**
     * Конструктор - создание нового объекта (собака)
     * @param name - имя собаки
     * @param age - возраст собаки
     * @param id - идентификатор собаки
     * @param status - статус - домашние или вьючные животные
     */
    public Dog(String name, int age, int id, String status) {
        super(name, age);
        this.id = id;
        this.status = status;
    }

    /**
     * Получение значения поля - идентификатор собаки
     */
    public int getId() {
        return id;
    }

    /**
     * Определения значения поля - идентификатор собаки
     * @param id - идентификатор собаки
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Получение значения поля - статус - домашние или вьючные животные
     */
    public String getStatus() {
        return status;
    }

    /**
     * Определения значения поля - статус - домашние или вьючные животные
     * @param status - статус - домашние или вьючные животные
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @aAnimal
    @Override
    public String makeSound() {
        return "Woof!";
    }
  
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return String.format("Собака [имя = %s, возраст = %d, идентификатор = %d, статус = %s]", super.getName(), super.getAge(), id, status);
    }
    
}
