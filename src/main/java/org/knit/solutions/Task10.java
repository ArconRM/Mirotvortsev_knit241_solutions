package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task10.Runner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;


/*
📌 Описание

Группа спортсменов участвует в забеге. Однако перед началом гонки все должны собраться на старте. Как только все участники будут готовы, забег начнётся одновременно. Используйте CyclicBarrier, чтобы синхронизировать запуск гонки.

Каждый бегун стартует одновременно, затем бежит разное время (симулируется Thread.sleep), после чего финиширует. Как только все бегуны завершат дистанцию, программа выводит сообщение о завершении гонки.

🎯 Требования к задаче:

Создать CyclicBarrier для синхронизации начала забега.
Реализовать класс Runner, который будет выполнять следующую логику в потоке:
Ожидание старта (использование barrier.await()).
Симуляция бега (Thread.sleep(randomTime)).
Вывод сообщения о финише.
После финиша всех участников программа должна сообщить, что гонка завершена.
Количество бегунов передаётся в аргументах командной строки или задаётся константой.
🔧 Подсказка

Используйте Executors.newFixedThreadPool() для управления потоками.
Для генерации случайного времени забега можно использовать ThreadLocalRandom.current().nextInt(500, 3000).
🔹 Дополнительное задание (по желанию):

Добавить ещё одну CyclicBarrier, чтобы дождаться всех бегунов на финише перед выводом финального сообщения.
Добавить возможность прерывания гонки (например, если один из бегунов "травмируется" и не может продолжить).
 */

@TaskDescription(taskNumber = 10, taskDescription = "Гонка бегунов с использованием CyclicBarrier")
public class Task10 implements Solution {
    @Override
    public void execute() {
        final int COUNT_OF_RUNNERS = 10;
        CyclicBarrier barrier = new CyclicBarrier(COUNT_OF_RUNNERS);
        AtomicBoolean allAlive = new AtomicBoolean(true);
        ExecutorService executor = Executors.newFixedThreadPool(COUNT_OF_RUNNERS);

        List<Runner> runners = new ArrayList<>(COUNT_OF_RUNNERS);
        for (int i = 0; i < COUNT_OF_RUNNERS; i++) {
            runners.add(new Runner(allAlive, barrier, i));
        }

        for (int i = 0; i < COUNT_OF_RUNNERS; i++) {
            executor.submit(runners.get(i)::prepareForStart);
        }
        try {
            barrier.await();
            System.out.println("Все приготовились, гонка началась");
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < COUNT_OF_RUNNERS; i++) {
            executor.submit(runners.get(i)::run);
        }
        try {
            barrier.await();
            if (allAlive.get()) {
                System.out.println("Все добежали, гонка закончилась");
            } else {
                System.out.println("Гонка закончилась из-за травмы бегуна");
                executor.shutdown();
            }
        } catch (InterruptedException | BrokenBarrierException e) {
//            System.out.println("Гонка закончилась из-за травмы бегуна");
//            executor.shutdownNow();
        }

        executor.shutdownNow();
    }
}
