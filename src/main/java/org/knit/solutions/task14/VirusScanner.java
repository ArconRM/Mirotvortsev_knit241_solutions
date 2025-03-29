package org.knit.solutions.task14;

public class VirusScanner implements Visitor {
    @Override
    public void visit(File file) {
        if (file.getContent().contains("вирус")) {
            System.out.println(file.getName() + " содержит вирус!!!!");
        } else {
            System.out.println(file.getName() + " нормальный");
        }
    }

    @Override
    public void visit(Folder folder) {
        for (FileSystemElement fileSystemElement : folder.getFiles()) {
            if (fileSystemElement instanceof Folder) {
                Folder folderToCheck = (Folder) fileSystemElement;
                folderToCheck.accept(this);
            } else if (fileSystemElement instanceof File) {
                File file = (File) fileSystemElement;
                this.visit(file);
            } else if (fileSystemElement instanceof FileLink) {
                FileLink fileLink = (FileLink) fileSystemElement;
                this.visit(fileLink);
            }
        }
    }

    @Override
    public void visit(FileLink fileLink) {
        if (fileLink.getContent().contains("вирус")) {
            System.out.println("Файл по ярлыку " + fileLink.getName() + " содержит вирус!!!!");
        } else {
            System.out.println("Файл по ярлыку " + fileLink.getName() +  " нормальный");
        }
    }
}
