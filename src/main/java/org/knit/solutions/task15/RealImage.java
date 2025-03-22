package org.knit.solutions.task15;

public class RealImage implements Image {
    private final ImageRepository imageRepository;

    public RealImage(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void request() {
        System.out.println("Выполнение запроса реального объекта");
        imageRepository.getImage();
    }
}
