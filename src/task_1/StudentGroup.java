package task_1;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/** 
 * Класс группа студентов, использует интерфейс перечесления
 * @author --
 * @version 1.0
*/
public class StudentGroup implements Iterable<Student>, Comparable<StudentGroup>, Serializable {
    /** поле список студентов в группе */
    private List<Student> group;
    /** поле идентификатор группы */
    private Integer idGroup;

    /**
     * Конструктор - создание нового объекта (группа студентов)
     * @param group - группа (список студентов)
     * @param idGroup - идентификатор группы
     */
    public StudentGroup(List<Student> group, Integer idGroup) {
        this.group = group;
        this.idGroup = idGroup;
    }

    /**
     * Получения значения поля группа
     */
    public List<Student> getGroup() {
        return group;
    }

    /**
     * Определение значения поля група
     * @param group - поле идентификатор группы
     */
    public void setGroup(List<Student> group) {
        this.group = group;
    }

    /**
     * Получения значения поля идентификатор группы
     */
    public Integer getIdGroup() {
        return idGroup;
    }

    /**
     * Определение значения поля идентификатор
     * @param id - поле идентификатор
     */
    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

     @Override
    public String toString() {
        return  String.format("StudentGroup [ group - %s, idGroup = %d ]", group, idGroup);
    }

    @Override
    public Iterator<Student> iterator() {
        return new Iterator<Student>() {

            private int counter;

            @Override
            public boolean hasNext() {
                if(counter<group.size()) { return true; } 
                else { return false; }            
            }

            @Override
            public Student next() {            
                return group.get(counter++);
            }
        };
    }

    @Override
    public int compareTo(StudentGroup o) {
        System.out.println(String.format("StudentGroup [ idGroup = %d | idGroup = %d ]", idGroup, o.idGroup));
        if(this.group.size() == o.group.size()) {
            if(this.idGroup==o.idGroup)return 0;
            if(this.idGroup>o.idGroup)return 1;
            else return -1;
        }

        if(this.group.size()>o.group.size()) return 1;
        else return -1;        
    }
}
