package task_1.Model;

import task_1.Interface.*;

/** 
 * Класс - Кошка, наследник класса Animal
 * @author --
 * @version 1.0
*/
@aAnimal
public class Cat extends Animal implements iSound { //   

    /** поле - идентификатор кошки*/
    private int id;
    /** поле - статус - домашние или вьючные животные */
    private String status;

    /**
     * Конструктор - создание нового объекта (кошка)
     * @param name - имя кошки
     * @param age - возраст кошки
     * @param id - идентификатор кошки
     * @param status - статус - домашние или вьючные животные
     */
    public Cat(String name, int age, int id, String status) {
        super(name, age);
        this.id = id;
        this.status = status;
    }

    /**
     * Получение значения поля - идентификатор кошки
     */
    public int getId() {
        return id;
    }

    /**
     * Определения значения поля - идентификатор кошки
     * @param id - идентификатор кошки
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Получение значения поля - статус - домашние или вьючные животные
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Определения значения поля - статус - домашние или вьючные животные
     * @param status - статус - домашние или вьючные животные
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    @aAnimal
    public String makeSound() {
        return "Meow!";
    }

    @Override
    public String toString() {
        return String.format("Кошка [имя = %s, возраст = %d, идентификатор = %d, статус = %s]", super.getName(), super.getAge(), id, status);
    }

}
