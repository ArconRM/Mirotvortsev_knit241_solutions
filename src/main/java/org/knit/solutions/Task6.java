package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task6.Railway;
/*Задача «Железнодорожный переезд» 🚆🚗

📌 Описание:
Есть автомобили и поезд.

Если поезд приближается, машины останавливаются и ждут.
После того, как поезд проедет, машины продолжают движение.
🔹 Что нужно реализовать?
✔ Поток "Поезд" останавливает автомобили (wait()).
✔ Поток "Поезд" сообщает о завершении (notifyAll()).
✔ Машины ждут, если поезд едет, и продолжают движение после notifyAll().
 */

@TaskDescription(taskNumber = 6, taskDescription = "Задача «Железнодорожный переезд»")
public class Task6 implements Solution {
    @Override
    public void execute() {
        Railway railway = new Railway();

        Thread threadCars = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                railway.passCar(i + " машина");
            }
        });

        Thread threadTrain = new Thread(() -> {
            try {
                Thread.sleep(2000);
                railway.trainIsPassing();
                Thread.sleep(2000);
                railway.trainPassed();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        threadCars.start();
        threadTrain.start();
    }
}
