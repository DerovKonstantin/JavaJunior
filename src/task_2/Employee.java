package task_2;


import java.util.UUID;


@Table(name = "employees") // прикрепляем созданную нами аннотацию к классу, дополнительный метод Найм будет возвращять значение имени таблицы в базе данных
public class Employee { // фабрика класс сотрудник

    @Column(name = "id", primaryKey = true)  // прикрепили созданную аннотацию
    private UUID id; // Класс, представляющий неизменяемый универсальный уникальный идентификатор (UUID). UUID представляет собой 128-битное значение.
    // UUID pk = UUID.randomUUID(); // рондомный

    @Column(name = "username") // прикрепили созданную аннотацию, primaryKey = false
    private String username; // имя

    @Column(name = "email") // прикрепили созданную аннотацию, primaryKey = false
    private String email; // емайл

    public Employee(String username, String email) { // конструктор для того чтобы попроще было создавать объект
        this.id = UUID.randomUUID();
        this.username = username;
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
