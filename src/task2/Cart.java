package task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Корзина
 * @param <T> Еда
 */
public class Cart<T extends Food> {

    //region Поля

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    //endregion

    //region Конструкторы

    /**
     * Создание нового экземпляра корзины
     * @param market принадлежность к магазину
     */
    public Cart(Class<T> clazz, UMarket market)
    {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    //endregion

    /**
     * Балансировка корзины // добавляет товары при отсутствии белков жиров углеводов
     */
    public void cardBalancing(){
        boolean[] PFC = {false, false, false}; // proteins fats carbohydrates
        // boolean proteins = false;
        // boolean fats = false;
        // boolean carbohydrates = false;

        // for (var food : foodstuffs) // если все три флага заполнены, товары больше не перебираем (корзина сбалансирована)
        // {
        //     if (!proteins && food.getProteins()) // если флаг протеинов не заполнен
        //         proteins = true;
        //     else
        //     if (!fats && food.getFats())
        //         fats = true;
        //     else
        //     if (!carbohydrates && food.getCarbohydrates())
        //         carbohydrates = true;
        //     if (proteins && fats && carbohydrates)
        //         break;
        // }

        // if (proteins && fats && carbohydrates)
        // {
        //     System.out.println("Корзина уже сбалансирована по БЖУ.");
        //     return;
        // }

        // for (var thing : market.getThings(clazz)) // подбираем недостающие товары по БЖУ
        // {
        //     if (!proteins && thing.getProteins())
        //     {
        //         proteins = true;
        //         foodstuffs.add(thing);
        //     }
        //     else if (!fats && thing.getFats())
        //     {
        //         fats = true;
        //         foodstuffs.add(thing);
        //     }
        //     else if (!carbohydrates && thing.getCarbohydrates())
        //     {
        //         carbohydrates = true;
        //         foodstuffs.add(thing);
        //     }
        //     if (proteins && fats && carbohydrates)
        //         break;
        // }

        // if (proteins && fats && carbohydrates)
        //     System.out.println("Корзина сбалансирована по БЖУ.");
        // else
        //     System.out.println("Невозможно сбалансировать корзину по БЖУ.");
        
        // С применением StreamAPI
        // foodstuffs.stream().forEach(food -> checkPFC(PFC, food));
        // foodstuffs.stream().anyMatch(food -> (!food.getProteins()||!food.getFats()||!food.getCarbohydrates()));

        foodstuffs.stream().takeWhile(food -> (!PFC[0] || !PFC[1] || !PFC[2])).forEach(food -> checkPFC(PFC, food));

        if (PFC[0] && PFC[1] && PFC[2]) { System.out.println("Корзина уже сбалансирована по БЖУ."); return; }

        market.getThings(clazz).stream().takeWhile(thing -> (!PFC[0] || !PFC[1] || !PFC[2])).forEach(thing -> addPFC(PFC, thing));

        if (PFC[0] && PFC[1] && PFC[2]) System.out.println("Корзина сбалансирована по БЖУ.");
        else System.out.println("Невозможно сбалансировать корзину по БЖУ.");

    }

    private void checkPFC(boolean[] PFC, T food) {
        if (!PFC[0] && food.getProteins()) {
            PFC[0] = true;
        } else if (!PFC[1] && food.getFats()) {
            PFC[1] = true;
        } else if (!PFC[2] && food.getCarbohydrates()) {
            PFC[2] = true;
        }
    }

    private void addPFC(boolean[] PFC, T thing) {
            if (!PFC[0] && thing.getProteins()) {
                PFC[0] = true;
                foodstuffs.add(thing);
            } else if (!PFC[1] && thing.getFats()) {
                PFC[1] = true;
                foodstuffs.add(thing);
            } else if (!PFC[0] && thing.getCarbohydrates()) {
                PFC[0] = true;
                foodstuffs.add(thing);
            }
    }

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    /**
     * Распечатать список продуктов в корзине
     */
    public void printFoodstuffs()
    {
        /*int index = 1;
        for (var food : foodstuffs)
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n", index++, food.getName(), food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет", food.getCarbohydrates() ? "Да" : "Нет");*/
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                index.getAndIncrement(), food.getName(),
                food.getProteins() ? "Да" : "Нет",
                food.getFats() ? "Да" : "Нет",
                food.getCarbohydrates() ? "Да" : "Нет"));

    }


}
