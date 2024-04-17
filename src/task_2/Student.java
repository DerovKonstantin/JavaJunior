package task_2;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.text.DecimalFormat;
import java.util.Random;

/** 
 * Класс студент, наследник класса человек, использует интерфейс сравнения
 * @author --
 * @version 1.0
*/
public class Student extends Person implements Comparable<Student>, Externalizable {
    /** поле идентификатор */
    private int id;
    /** поле средний балл */
    transient double gpa; // слово transient в Java  означает, что поле не должно быть сериализовано.

    public Student() {
    }

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
    public Double getGPA() {
        return gpa;
    }

    /**
     * Определение значения поля средний балл
     * @param id - поле средний балл
     */
    public void setGPA(Double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return String.format("Students [ name - %s, age = %d, id = %d, GPA = %s ]", super.getName(), super.getAge(), id, new DecimalFormat( "#.#" ).format(getGPA()));
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        // name, age, id, GPA
        out.writeUTF(this.getName());
        out.writeInt(this.getAge());
        out.writeInt(this.getId());
        out.writeDouble(this.getGPA());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setName(in.readUTF());
        this.setAge(in.readInt());
        this.setId(in.readInt());
        this.setGPA(in.readDouble());
    }
    
}
