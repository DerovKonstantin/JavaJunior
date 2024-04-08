package task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class UMarket { // Магазин

    private void initializeThings() // инициализация товаров в магазине
    {
        things.add(new Pen());
        things.add(new Notebook());

        things.add(new Chicken());
        things.add(new Fruit());
        things.add(new OliveOil());

        things.add(new BalykCheese());
        things.add(new Crisps());
        things.add(new ChocolateBar());

        things.add(new DumplingsBerries());
        things.add(new DumplingsMeat());
        things.add(new Cheburek());
    }

    public <T extends Thing> void printThings(Class<T> clazz){ // печатает список товаров  вмагазине
        // Передается объект типа Class(обобщенный тип) но нужно указать обобщенный паметр<T>
        // В ООП можно переделать метод в обобщенный даже если класс не является обобщенным
        // для этого перед ключевым словом void нужно указать обобщенный параметр <T>
        // так же можно накладывать ограничения, наследники определенного типа <T extends Thing>

        /*Integer index = 1;
        for (var thing : things)
        {
            if (clazz.isInstance(thing)){ 
            // Метод isInstance класса Class эквивалентен оператору instanceof

                if (Food.class.isAssignableFrom(thing.getClass())) // Все унаследованное от еды вернет true
                // Метод венет истину если класс в левой части инструкции совпадет с суперклассом
                // или супер интерфейсом предоставленного параметром класса или является его супер классом

                {
                    System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n", index++, thing.getName(), ((Food)thing).getProteins() ? "Да" : "Нет",
                            ((Food)thing).getFats() ? "Да" : "Нет", ((Food)thing).getCarbohydrates() ? "Да" : "Нет");
                }
                else{
                    System.out.printf("[%d] %s\n", index++, thing.getName());
                }
            }
        }*/

        int[] index = {1};

        things.stream() // в данном случае применение Stream не сокращяет код и не улушает его работу
                // .filter(thing -> clazz.isInstance(thing)) // для каждого элемента выщвать метод
                // ниже скращенная версия строки(потому что к каждому объекту применяется метод)
                .filter(clazz::isInstance)
                .forEach(thing -> {
                            if (Food.class.isAssignableFrom(thing.getClass())) {
                                Food foodThing = (Food) thing;
                                System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                                        index[0]++, thing.getName(),
                                        foodThing.getProteins() ? "Да" : "Нет",
                                        foodThing.getFats() ? "Да" : "Нет",
                                        foodThing.getCarbohydrates() ? "Да" : "Нет");
                            }
                            else {
                                System.out.printf("[%d] %s\n", index[0]++, thing.getName());
                            }
                }); // для нумерации списка index++ в потоке работать не будет(вне области видимости) 
                    // мы изначально создали массив нулевой длинны int[] index = {1}; и в последствии увеличивали его на еденицу index[0]++

    }

    public <T extends Thing> T getThingByIndex(Class<T> clazz, int index) // возвращяет нужный тавар
    {
        /*int counter = 1;
        for (var thing : things)
        {
            if (clazz.isAssignableFrom(thing.getClass())){
                if (index == counter++)
                    return (T)thing; // возвращяем приводя к изначальному типу
            }
        }
        return null;*/
        AtomicInteger counter = new AtomicInteger(1);
        return things.stream()
                .filter(clazz::isInstance)
                .filter(thing -> index == counter.getAndIncrement()) // counter.getAndIncrement() увеличивает счетчик на единицу
                .map(clazz::cast) // метод каст позваолит преобразовать объект к тому типу который мы ожидаем увидеть на вжоде
                .findFirst().orElse(null); // вернуть первый объект или вернуть null(если объекта не существует)

    }

    public <T extends Thing> Collection<T> getThings(Class<T> clazz) // возвращяе все товары
    {
        return things.stream()
                .filter(clazz::isInstance) // отфильтровываем все товары
                .map(clazz::cast) // Преобразуем каждый объект к определенному типу
                .collect(Collectors.toList()); // и возвращяем коллекцию
    }

    //region Конструкторы

    public UMarket()
    {
        things = new ArrayList<>();
        initializeThings();
    }

    //endregion

    //region Поля

    /**
     * Товары в магазине
     */
    private final Collection<Thing> things;

    //endregion
}

