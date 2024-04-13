package task_2;


import java.lang.reflect.Field;
import java.util.UUID;

public class QueryBuilder { // метод который позволит создать запос добавления данных в таблицу(производит работу с анотациями)


    public String buildInsertQuery(Object obj) throws IllegalAccessException { // запрос ссоздается в виде строки
        Class<?> clazz = obj.getClass(); // описатель типа
        // insert into table_name (column1, column2, column3) values ('value1', 'value2', 'value3') // запрос в виде строки

        if (clazz.isAnnotationPresent(Table.class)) { // иетод в раках механизма рефлексии получиит ответ на вопрос, содержится ли текущяя анотация таЙбл у описателя данного типа

            StringBuilder query = new StringBuilder("INSERT INTO ");
            Table tableAnnotation = clazz.getAnnotation(Table.class); // вернет ссылку на объект описания анотации тайбл
            query
		    .append(tableAnnotation.name()) // .name() наш метод в тайбл
                    .append(" (");
	    // return querty.toString(); // для примера посмотреть получившеюся строку
	    // Insert Query: INSERT INTO employees ( 

            Field[] fields = clazz.getDeclaredFields(); // получим описание всех полей, пройдемся по каждому полю, и рассмотрим только те поля, что помечены анотацией column
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class); // получим описание антации column
                    query.append(columnAnnotation.name()).append(", "); // добавляем данные через заптую
		    // на печати получим следующий результат - Insert Query: INSERT INTO employees (id, username, email, 
                }
            }

            if (query.charAt(query.length() - 2) == ',') { // удаляем последнюю запятую из строки
                query.delete(query.length() - 2, query.length() - 1);
            }

            query.append(") VALUES ("); // добавляем слово VALUES

            for (Field field : fields) { // получаем значение полей
                if (field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true);
                    query.append("'").append(field.get(obj)).append("', "); // значение обертываем в ковычьк и доьавляем в строку
                }
            }

            if (query.charAt(query.length() - 2) == ',') { // удаляем последнюю запятую из строки
                query.delete(query.length() - 2, query.length());
            }

            query.append(")");

            return query.toString();
	    // на печати получим следующий результат - Insert Query: INSERT INTO employees (id, username, email ) VALUES ('8792c025-ef5c-463e-a51b-e8f66c5f9551','Stanislav','sample@gmail.com')

        }
        else{
            return "";
        }
    }

    // дополним наш механизм методом получения данных из базы
    // метод который позволит получить информацию о конкретном объекте в базе данных
    public String buildSelectQuery(Class<?> clazz, UUID primaryKey){
        if (clazz.isAnnotationPresent(Table.class)) {
            // select * from table_name where iddddd = 'primaryKey'
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            StringBuilder query = new StringBuilder("SELECT * FROM "); // создаем динамическую строку
            query.append(tableAnnotation.name()) // название таблицы
                    .append(" WHERE ");

            Field[] fields = clazz.getDeclaredFields(); // пройдем по полям получим аннотацию
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) { // интересуют только те поля что помечены аннотацией colum
                    Column columnAnnotation = field.getAnnotation(Column.class); // берем анотацию поля
                    if (columnAnnotation.primaryKey()) { // проверяем тру или фалс .primaryKey()
                        query.append(columnAnnotation.name()).append(" = '").append(primaryKey).append("'"); // добавляем
                        break;
                    }
                }
            }

            return query.toString();
	    // получаем строку Select Querty: SEKECT * FROM employees WHERE id = 'dea3e4c9-f55e-46bf- 8328-af6b8d561520'

        }
        else {
            return "";
        }
    }

    // метод обновления данных
    public String buildUpdateQuery(Object obj) throws IllegalAccessException {

        // update table_name set column1 = 'value1', column2 = 'value2' where id = 'primaryKey' (классический скрипт обновления данных)
        Class<?> clazz = obj.getClass();
        if (clazz.isAnnotationPresent(Table.class)) {
            StringBuilder query = new StringBuilder("UPDATE ");

            Table tableAnnotation = clazz.getAnnotation(Table.class);
            query.append(tableAnnotation.name()).append(" SET ");

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true);
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if (columnAnnotation.primaryKey()){
                        continue;
                    }
                    query.append(columnAnnotation.name()).append(" = '").append(field.get(obj)).append("', ");
                }
            }

            if (query.charAt(query.length() - 2) == ',') {
                query.delete(query.length() - 2, query.length());
            }

            query.append(" WHERE ");
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if (columnAnnotation.primaryKey()) {
                        query.append(columnAnnotation.name()).append(" = '").append(field.get(obj)).append("'");
                        break;
                    }
                }
            }

            return query.toString();

        }
        else {
            return "";
        }

    }

    /**
     * Дополнительная задача:
     * Доработайте метод генерации запроса на удаление объекта из таблицы БД (DELETE FROM <Table> WHERE ID = '<id>')
     * В классе QueryBuilder который мы разработали на семинаре.
     * @return
     */
    public String buildDeleteQuery(Class<?> clazz, UUID primaryKey){
        
        if (clazz.isAnnotationPresent(Table.class)) {
            // delete from `table_name` where iddddd = 'primaryKey';
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            StringBuilder query = new StringBuilder("DELETE FROM ");
            query.append(tableAnnotation.name())
                 .append(" WHERE ");

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if (columnAnnotation.primaryKey()) {
                        query.append(columnAnnotation.name()).append(" = '").append(primaryKey).append("'");
                        break;
                    }
                }
            }
            return query.toString();
        } else {
            return "";
        }
    }

}
