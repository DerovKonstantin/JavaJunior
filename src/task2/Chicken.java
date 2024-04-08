package task2;

/**
 * Курица
 */
public class Chicken implements HealthyFood{ // Курица начледует интерфейс(переопределили методы) здоровой еды
    @Override
    public boolean getProteins() {
        return true;
    }

    @Override
    public boolean getFats() {
        return false;
    }

    @Override
    public boolean getCarbohydrates() {
        return false;
    }

    @Override
    public String getName() {
        return "Курица";
    }
}

