package mysqlconnecttable.task_2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AppTask_2 {

    /* 
        Настройте Hibernate, связав его с вашей базой данных.
    */

    public static void main( String[] args ) {

        /*
            - Добавляем пакеты драйверов

                <!--  Hibernate  -->
                <dependency>
                 <groupId>org.hibernate</groupId>
                 <artifactId>hibernate-core</artifactId>
                 <version>5.6.0.Final</version>
                </dependency>

                <!--  C3P0 Connection Pooling  -->
                <dependency>
                 <groupId>org.hibernate</groupId>
                 <artifactId>hibernate-c3p0</artifactId>
                 <version>5.6.0.Final</version>
                </dependency>

            - Добавить анотации в обхекты с которыми предстоит работать
                @Entity
                @Table(name = " ")
        
                @Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)

            - Обязательно добавить конструктор по умолчанию

            - Добавим отдельный конфигурационный файл resources hibernate.cfg.xml
                    Файл для первичного подключения и работой с базой данных
        */

        // 4. Создание фабрики сессий
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();


        // Создание сессии
        try (Session session = sessionFactory.getCurrentSession()){


            // Начало транзакции
            session.beginTransaction();

            // Создание объекта
            Course course = Course.create();

            // Сохранение объекта в базе данных
            session.save(course);
            System.out.println("Object course save successfully");


            // Чтение объекта из базы данных
            Course retrievedCourse = session.get(Course.class, course.getId());
            System.out.println("Object course retrieved successfully");
            System.out.println("Retrieved course object: " + retrievedCourse);

            // Обновление объекта
            retrievedCourse.updateTitle();
            retrievedCourse.updateDuration();
            session.update(retrievedCourse);
            System.out.println("Object course update successfully");


            Course retrievedCourse2 = session.get(Course.class, 23);
            // Удаление объекта
            session.delete(retrievedCourse2);
            System.out.println("Object course delete successfully");

            // Коммит транзакции
            session.getTransaction().commit();
            System.out.println("Transaction commit successfully");
        }
    }
}
