package task_2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Control.dataControl;

public class AppTask_2 {
    /* 
    Урок 3. Сериализация
    
    2. * Выполнить задачу 1 используя другие типы сериализаторов (в xml и json документы).
    */
    public static void main(String[] args) throws Exception {


        List<Student> studList = new ArrayList<Student>(Arrays.asList(
            new Student("Иван", 25, 121),
            new Student("Игорь", 23, 301),
            new Student("Иван", 22, 121),
            new Student("Игорь",  23, 444),
            new Student("Даша",  23, 171),
            new Student("Лена",  23, 104)
        ));

        // Создаем группу
        StudentGroup group = new StudentGroup(studList, 1);
        studList.stream().forEach(System.out::println);
        // Сохраняем полученные данные
        dataControl.save("student_group_data_2.bin", group);
        
        // Загружаем полученные данные
        StudentGroup loadedGroup = (StudentGroup) dataControl.load("student_group_data_2.bin");
        loadedGroup.getGroup().stream().forEach(System.out::println);
    }
}
