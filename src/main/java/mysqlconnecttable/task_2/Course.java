package mysqlconnecttable.task_2;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "Courses")
public class Course {

    private static final Random random = new Random();
    private static final String[] titles = new String[] { "Общеобразовательный", "Подготовительный", "Повышения квалификации", "Профессиональной переподготовки" };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private int duration;

    public Course(){
    }

    public Course(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public Course(int id, String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public void updateDuration(){
        duration = random.nextInt(20, 26);
    }

    public void updateTitle(){
        title = titles[random.nextInt(titles.length)];
    }

    public static Course create(){
        return new Course(titles[random.nextInt(titles.length)], random.nextInt(30, 60));
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("Course [ id = %d, title - %s, duration = %d ]", id, title, duration);
    }
    
}
