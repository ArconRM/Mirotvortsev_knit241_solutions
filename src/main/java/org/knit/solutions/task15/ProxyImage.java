package org.knit.solutions.task15;

public class ProxyImage implements Image {
    private RealImage imageCached;
    private final ImageRepository imageRepository;

    public ProxyImage(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public RealImage getImageCached() {
        return imageCached;
    }

    @Override
    public void request() {
        System.out.println("Прокси передает запрос");

        if (imageCached == null) {
            imageCached = new RealImage(imageRepository);
        }

        imageCached.request();
    }
}
