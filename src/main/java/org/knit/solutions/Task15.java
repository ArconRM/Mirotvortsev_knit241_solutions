package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task15.Image;
import org.knit.solutions.task15.ImageRepository;
import org.knit.solutions.task15.ProxyImage;

@TaskDescription(taskNumber = 15, taskDescription = "Задача 15 на паттерн Прокси (Proxy)")
public class Task15 implements Solution {
    @Override
    public void execute() {
        ImageRepository imageRepository = new ImageRepository();
        Image proxyImage = new ProxyImage(imageRepository);
        proxyImage.request();
    }
}
