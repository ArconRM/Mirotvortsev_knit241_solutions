package org.knit.solutions.task14;

public class FileLink implements FileSystemElement {
    private String name;
    private final File file;

    public FileLink(String name, File file) {
        this.name = name;
        this.file = file;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getSize() {
        return this.file.getSize();
    }

    public String getContent() {
        return this.file.getContent();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this.file);
    }
}
