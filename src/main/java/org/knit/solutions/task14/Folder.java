package org.knit.solutions.task14;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemElement {
    private String name;
    private final List<FileSystemElement> fileSystemElements = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addElement(FileSystemElement fileSystemElement) {
        fileSystemElements.add(fileSystemElement);
    }

    public void removeElement(FileSystemElement fileSystemElement) {
        fileSystemElements.remove(fileSystemElement);
    }

    public List<FileSystemElement> getFiles() {
        return fileSystemElements;
    }

    @Override
    public double getSize() {
        double result = 0;
        for (FileSystemElement element : fileSystemElements) {
            result += element.getSize();
        }
        return result;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
