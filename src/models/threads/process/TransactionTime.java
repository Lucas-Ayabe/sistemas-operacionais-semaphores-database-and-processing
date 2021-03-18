package models.threads.process;

public class TransactionTime implements ITime {

    private long time = 0;
    private static final int SECOND = 1000;

    public TransactionTime(int forId) {
        int criteria = forId % 3;
        if (criteria == 0 || criteria == 2) time = (long) (SECOND * 1.5);
        if (criteria == 1) time = SECOND;
    }

    public long asMilliseconds() {
        return time;
    }

    public double asSeconds() {
        return (time + 0.0) / SECOND;
    }
}
