package task2;

/**
 * Оливковое масло
 */
public class OliveOil implements HealthyFood{ // имплементируют интерфейс Здоровая еда
    @Override
    public boolean getProteins() {
        return false;
    }

    @Override
    public boolean getFats() {
        return true;
    }

    @Override
    public boolean getCarbohydrates() {
        return false;
    }

    @Override
    public String getName() {
        return "Оливковое масло";
    }
}
