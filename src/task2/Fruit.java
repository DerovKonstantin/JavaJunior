package task2;

/**
 * Фрукт
 */
public class Fruit implements HealthyFood{ // имплементируют интерфейс Здоровая еда
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
        return "Фрукт";
    }
}
