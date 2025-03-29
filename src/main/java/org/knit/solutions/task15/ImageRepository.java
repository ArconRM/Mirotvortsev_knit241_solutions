package org.knit.solutions.task15;

public class ImageRepository {
    public void getImage() {
        try {
            System.out.println("Изображение получается с сервера...");
            Thread.sleep(2000);
            System.out.println("Изображение было получено" );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
