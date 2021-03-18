package models.threads.process;

import models.RandomInt;

public class CalculationTime implements ITime {

    private RandomInt generator;
    private static final int SECOND = 1000;

    public CalculationTime(int forId) {
        int min = 0;
        int max = 0;

        switch (forId % 3) {
            case 0:
                min = SECOND;
                max = 2 * SECOND;
                break;
            case 1:
                min = (int) (0.2 * SECOND);
                max = SECOND;
                break;
            case 2:
                min = (int) (0.5 * SECOND);
                max = (int) (1.5 * SECOND);
                break;
            default:
                break;
        }

        generator = new RandomInt(min, max);
    }

    public long asMilliseconds() {
        return (long) generator.generate();
    }

    public double asSeconds() {
        return (generator.generate() + 0.0) / SECOND;
    }
}
