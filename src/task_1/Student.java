package task_1;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Random;

/** 
 * Класс студент, наследник класса человек, использует интерфейс сравнения
 * @author --
 * @version 1.0
*/
public class Student extends Person implements Comparable<Student> {
    /** поле идентификатор */
    private int id;
    /** поле средний балл */
    transient double gpa; // слово transient в Java  означает, что поле не должно быть сериализовано.

    /**
     * Конструктор - создание нового объекта (человек)
     * @param name - имя студента
     * @param age - возраст студента
     * @param id - идентификатор
     */
    public Student(String name, int age, int id) {
        super(name, age);
        this.id = id;
        this.gpa = new Random().nextDouble(3.0, 5.0);
    }

    /**
     * Получения значения поля идентификатор
     */
    public int getId() {
        return id;
    }

    /**
     * Определение значения поля идентификатор
     * @param id - поле идентификатор
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Получения значения поля средний балл
     */
    public String getGPA() {
        return new DecimalFormat( "#.#" ).format(gpa);
    }

    @Override
    public String toString() {
        return String.format("Students [ name - %s, age = %d, id = %d, GPA = %s ]", super.getName(), super.getAge(), id, getGPA() );
    }

    @Override
    public int compareTo(Student o) {

        System.out.println(String.format("Compare students [ %s - %s ]", super.getName(), o.getName()));
        if (super.getAge() == o.getAge()) {
            if (id == o.id) return 0;
            if (id > o.id) return 1;
            else return -1;
        }

        if(super.getAge() > o.getAge()) return 1;
        else return -1;        
    }
    
}
