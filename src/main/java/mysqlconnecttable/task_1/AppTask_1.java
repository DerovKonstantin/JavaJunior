package mysqlconnecttable.task_1;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class AppTask_1 {

    /* 
        Урок 4. Базы данных и инструменты взаимодействия с ними
        Создайте базу данных (например, SchoolDB).
        В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
        Настройте Hibernate для работы с вашей базой данных.
        Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
        Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
        Убедитесь, что каждая операция выполняется в отдельной транзакции.
    */

    private final static Random random = new Random();

    public static void main( String[] args ) {

        /*
            - Установили субд MySQL
            - Установили Docker
            - Скачали образ MySQL для Docker
            - Запускаем контейнер в фоновом режиме используя образ mysql и мапинг томов
                C:\Users\DiorO>docker run --name mysql-container -v F:\Учеба\.container\database:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=password -p 3306:3306 -d mysql
                    F:\Учеба\.container\database - созданная директория(вне контейнера) для хранения файлов mysql 
                    :/var/lib/musql - директория хранения файлов в mysql

                C:\Users\DiorO>docker ps
                CONTAINER ID   IMAGE     COMMAND                  CREATED          STATUS          PORTS                               NAMES
                fe607eaf3d76   mysql     "docker-entrypoint.s…"   28 seconds ago   Up 27 seconds   0.0.0.0:3306->3306/tcp, 33060/tcp   mysql-container

                sh-4.4# pwd
                /var/lib/mysql
                sh-4.4# ls
                '#ib_16384_0.dblwr'   auto.cnf        ca-key.pem        ib_buffer_pool   mysql.ibd            public_key.pem    undo_001
                '#ib_16384_1.dblwr'   binlog.000001   ca.pem            ibdata1          mysql.sock           server-cert.pem   undo_002
                '#innodb_redo'        binlog.000002   client-cert.pem   ibtmp1           performance_schema   server-key.pem
                '#innodb_temp'        binlog.index    client-key.pem    mysql            private_key.pem      sys
                sh-4.4# exit
                - все файлы сохраняются в указанной папке вне контейнера  

            - Подключаем пакет содержащий драйвер
                <dependency>
                    <groupId>junit</groupId>
                    <artifactId>junit</artifactId>
                    <version>4.11</version>
                    <scope>test</scope>
                </dependency>
        */

        // - Подключимся к базе данных
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "password";

        // Подключение к (субд) базе данных
        try (Connection connection = DriverManager.getConnection(url, user, password))
        {
            // Создание базы данных
            createDatabase(connection);
            System.out.println("Database created successfully");

            // Использование базы данных
            useDatabase(connection);
            System.out.println("Use database successfully");

            // Создание таблицы
            createTable(connection);
            System.out.println("Create table successfully");

            // Вставка данных
            int count = random.nextInt(5, 11);
            for (int i = 0; i < count; i++){
                insertData(connection, Course.create());
            }
            System.out.println("Insert data successfully");

            // Чтение данных
            Collection<Course> courses = readData(connection);
            for (Course course: courses)
                System.out.println(course);
            System.out.println("Read data successfully");

            // Обновление данных
            for (Course course: courses) {
                course.updateTitle();
                course.updateDuration();
                updateData(connection, course);
            }
            System.out.println("Update data successfully");

            // Удаление данных (всех данных таблицы)
            /*for (var student: students)
                deleteData(connection, student.getId());
            System.out.println("Delete data successfully");*/

            // Закрытие соединения
            //connection.close();
            //System.out.println("Database connection close successfully");

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    // если базы данных не существует мы ее создаем
    private static void createDatabase(Connection connection) throws SQLException{
        String createDatabaseSQL =  "CREATE DATABASE IF NOT EXISTS coursesDB;";
        PreparedStatement statement = connection.prepareStatement(createDatabaseSQL);
        statement.execute();
    }

    // указываем какую базу данных мы используем
    private static void useDatabase(Connection connection) throws SQLException {
        String useDatabaseSQL =  "USE coursesDB;";
        try (PreparedStatement statement = connection.prepareStatement(useDatabaseSQL)) {
            statement.execute();
        }
    }

    // создание таблицы
    private static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS courses (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255), duration INT);";
        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
            statement.execute();
        }
    }

    /**
     * Добавление данных в таблицу students
     * @param connection Соединение с БД
     * @param student Студент
     * @throws SQLException Исключение при выполнении запроса
     */
    private static void insertData(Connection connection, Course course) throws SQLException {
        String insertDataSQL = "INSERT INTO courses (title, duration) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(insertDataSQL)) {
            statement.setString(1, course.getTitle());
            statement.setInt(2, course.getDuration());
            statement.executeUpdate();
        }
    }

    /**
     * Чтение данных из таблицы courses
     * @param connection Соединение с БД
     * @return Коллекция курсов
     * @throws SQLException Исключение при выполнении запроса
     */
    private static Collection<Course> readData(Connection connection) throws SQLException {
        ArrayList<Course> coursesList = new ArrayList<>();
        String readDataSQL = "SELECT * FROM courses;";
        try (PreparedStatement statement = connection.prepareStatement(readDataSQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int duration = resultSet.getInt("duration");
                coursesList.add(new Course(id, title, duration));
            }
            return coursesList;
        }
    }

    /**
     * Обновление данных в таблице students по идентификатору
     * @param connection Соединение с БД
     * @param student Студент
     * @throws SQLException Исключение при выполнении запроса
     */
    private static void updateData(Connection connection, Course course) throws SQLException {
        String updateDataSQL = "UPDATE courses SET title=?, duration=? WHERE id=?;";
        try (PreparedStatement statement = connection.prepareStatement(updateDataSQL)) {
            statement.setString(1, course.getTitle());
            statement.setInt(2, course.getDuration());
            statement.setInt(3, course.getId());
            statement.executeUpdate();
        }
    }

    /**
     * Удаление записи из таблицы students по идентификатору
     * @param connection Соединение с БД
     * @param id Идентификатор записи
     * @throws SQLException Исключение при выполнении запроса
     */
    private static void deleteData(Connection connection, int id) throws SQLException {
        String deleteDataSQL = "DELETE FROM courses WHERE id=?;"; // если не указать where мы отчистим всю таблицу
        try (PreparedStatement statement = connection.prepareStatement(deleteDataSQL)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
