package task_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Control.dataControl;


public class AppTask_1 {
    /* 
    Урок 3. Сериализация
    
    Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
    Обеспечьте поддержку сериализации для этого класса.
    Создайте объект класса Student и инициализируйте его данными.
    Сериализуйте этот объект в файл.
    Десериализуйте объект обратно в программу из файла.
    Выведите все поля объекта, включая GPA, и ответьте на вопрос,
    почему значение GPA не было сохранено/восстановлено.
    */
    
    public static void main(String[] args) throws Exception {

        // Создаем список студентов
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
        dataControl.save("student_group_data_1.bin", group);
        
        // Загружаем полученные данные
        StudentGroup loadedGroup = (StudentGroup) dataControl.load("student_group_data_1.bin");
        loadedGroup.getGroup().stream().forEach(System.out::println);

    }
}
