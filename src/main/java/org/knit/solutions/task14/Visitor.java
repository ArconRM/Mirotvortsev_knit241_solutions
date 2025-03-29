package org.knit.solutions.task14;

public interface Visitor {
    void visit(File file);
    void visit(Folder folder);
    void visit(FileLink fileLink);
}
