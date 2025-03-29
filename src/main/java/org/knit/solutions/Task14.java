package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task14.File;
import org.knit.solutions.task14.FileLink;
import org.knit.solutions.task14.Folder;
import org.knit.solutions.task14.VirusScanner;
/*
    Задача:
    Создайте систему управления файловыми объектами.
    Есть три типа файловых объектов:

    Файлы,
    Папки,
    Ссылки (ярлыки).
    Требуется реализовать два посетителя:

    Сканер вирусов, который проверяет каждый файл на наличие вирусов.
    Анализатор размера, который подсчитывает общий размер файлов (ссылки не учитываются).
    📌 Подсказка:
    Каждый файл, папка и ссылка должны реализовывать интерфейс FileSystemElement. В accept() методе вызывается соответствующий метод визитера.
 */

@TaskDescription(taskNumber = 14, taskDescription = "Задача 14 на паттерн Визитер (Visitor)")
public class Task14 implements Solution {

    @Override
    public void execute() {
        File file1 = new File("file1");
        file1.setContent("норм файл");
        File file2 = new File("file3");
        file2.setContent("вирус");
        File file3 = new File("file2");
        file3.setContent("неочскрытыйвирус");

        Folder folder1 = new Folder("folder1");
        Folder folder2 = new Folder("folder1");
        Folder folder3 = new Folder("folder1");

        FileLink fileLink = new FileLink("file1_link", file1);

        folder1.addElement(fileLink);
        folder1.addElement(file1);
        folder1.addElement(folder2);
        folder2.addElement(folder3);
        folder3.addElement(file2);
        folder3.addElement(file3);

        VirusScanner virusScanner = new VirusScanner();
        virusScanner.visit(folder1);
    }
}
