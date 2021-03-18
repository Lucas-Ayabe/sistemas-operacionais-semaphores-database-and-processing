package models.threads.process;

import java.util.concurrent.Semaphore;

public class Proccess extends Thread {

    private int id;
    private Semaphore semaphore;

    public Proccess(int id, Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }

    public void doTransactionFor(ITime time) throws InterruptedException {
        semaphore.acquire();
        System.out.println(
            "A " +
            this +
            " estará fazendo transação no banco de dados por " +
            time.asSeconds() +
            " segundos\n"
        );
        sleep(time.asMilliseconds());
        semaphore.release();
    }

    public void doCalculationFor(ITime time) throws InterruptedException {
        System.out.println(
            "A " +
            this +
            " estará fazendo calculos por " +
            time.asSeconds() +
            " segundos\n"
        );
        sleep(time.asMilliseconds());
    }

    @Override
    public void run() {
        int criteria = id % 3;
        ITime calculationTime = new CalculationTime(id);
        ITime transactionTime = new TransactionTime(id);

        try {
            doCalculationFor(calculationTime);
            doTransactionFor(transactionTime);
            doCalculationFor(calculationTime);
            doTransactionFor(transactionTime);

            if (criteria == 0 || criteria == 2) {
                doCalculationFor(calculationTime);
                doTransactionFor(transactionTime);
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
            interrupt();
        }
    }

    @Override
    public String toString() {
        return "Thread " + id;
    }
}
