package task2;

/**
 * Шоколадный батончик
 */
public class ChocolateBar implements Snack{ // имплементирует интерфейс Легкая закуска
    @Override
    public boolean getProteins() {
        return false;
    }

    @Override
    public boolean getFats() {
        return false;
    }

    @Override
    public boolean getCarbohydrates() {
        return true;
    }

    @Override
    public String getName() {
        return "Шоколадный батончик";
    }
}
