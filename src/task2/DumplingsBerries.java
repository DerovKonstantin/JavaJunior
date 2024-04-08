package task2;

/**
 * Замороженные ягоды
 */
public class DumplingsBerries implements SemiFinishedFood{ // имплементируют интерфейс Полуфабрикаты(замороженные продукты)
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
        return "Замороженные ягоды";
    }
}
