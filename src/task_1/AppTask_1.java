package task_1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

import task_1.Interface.*;
import task_1.Model.*;

/* 
Задача 1:
Создайте абстрактный класс "Animal" с полями "name" и "age".
Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
Выведите на экран информацию о каждом объекте.
Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
*/

public class AppTask_1 {
    public static void main(String[] args) throws Exception {

        // Создаем список животных
        Animal[] animals = {
            new Cat("Сергей",  21, 101, "домашний"), 
            new Dog("Андрей",  22, 111, "домашний"), 
            new Cat("Даша",  25, 171, "домашний"),
            new Dog("Лена",  23, 104, "домашний")
        };

        // Создаем стрим
        Stream<Animal> streamAnimals = Arrays.stream(animals);

        // Выполнем действия из задания
        streamAnimals
            .takeWhile(animal -> animal.getClass().isAnnotationPresent(aAnimal.class))
            .peek(System.out::println)
            .forEach (animal -> {
                try {
                    animalMakeSound(animal);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
        
    }

    // метод запускает методы классов помеченные аннотацией
    public static void animalMakeSound(Animal animal) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        for (Method method : animal.getClass().getDeclaredMethods()) {
			if (method.isAnnotationPresent(aAnimal.class)) {
                System.out.println(method.invoke(animal));
            }
        }
	}
    
}
