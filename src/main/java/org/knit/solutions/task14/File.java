package org.knit.solutions.task14;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class File implements FileSystemElement {
    private String name;
    private String content;
    private double size;

    public File(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    @Override
    public double getSize() {
        return size;
    }

    public void setContent(String content) {
        this.content = content;
        size = content.getBytes(StandardCharsets.UTF_8).length;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
