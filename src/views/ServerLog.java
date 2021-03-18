package views;

import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;
import models.threads.process.Proccess;

public class ServerLog {

    public static void main(String[] args) {
        var semaphore = new Semaphore(1);
        IntStream
            .range(1, 22)
            .forEach(id -> (new Proccess(id, semaphore)).start());
    }
}
