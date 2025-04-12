package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task20.PasswordManager.config.AppConfig;
import org.knit.solutions.task20.PasswordManager.model.PasswordEntry;
import org.knit.solutions.task20.PasswordManager.security.MasterPasswordService;
import org.knit.solutions.task20.PasswordManager.service.PasswordService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

/*
### Задача 20

- использование **Spring Context**,
- **AES-шифрование** паролей с мастер-паролем (`char[]`),
- команда для **копирования пароля в буфер обмена** (без вывода в консоль),
- возможное расширение на **JavaFX UI**.

---

## 🧪 Лабораторная работа: Password Manager с Spring и шифрованием

### 🎯 Цель работы:
Познакомиться с использованием **Spring Framework** для создания приложений со слоистой архитектурой, управлением зависимостями, конфигурацией контекста и безопасной работой с данными.

---

### 📌 Задание:

Реализуйте приложение **Password Manager**, которое:
- позволяет сохранять логины и пароли для различных сайтов,
- использует **Spring Context** для управления зависимостями,
- **шифрует пароли с помощью мастер-пароля**,
- не показывает пароль на экран при просмотре, а копирует его в **буфер обмена по команде**,
- Реализовать **сохранение данных в файл** (`JSON`, `XML` или `Serialized`) между сессиями.
- (дополнительно) предоставляет графический интерфейс с JavaFX.

---

## ⚙️ Технические требования

### ✅ Основной функционал:

1. Приложение работает через консоль.
2. При запуске пользователь вводит **мастер-пароль** (не отображается на экране).
3. Все пароли шифруются с использованием алгоритма **AES** и введённого мастер-пароля.
4. Реализованы команды:
   - `add` — добавить запись (сайт, логин, пароль),
   - `list` — отобразить список сайтов и логинов (без паролей),
   - `copy <site>` — расшифровать пароль и **скопировать в буфер обмена**,
   - `delete <site>` — удалить запись по названию сайта,
   - `exit` — завершить программу.

---

### 🧱 Архитектура:

- **model/PasswordEntry.java** — класс с полями: `site`, `login`, `encryptedPassword`.
- **repository/PasswordRepository** — интерфейс + `InMemoryPasswordRepository` (использует `HashMap`).
- **service/PasswordService** — бизнес-логика: добавление, удаление, копирование.
- **crypto/EncryptionService** — интерфейс + `AesEncryptionService`.
- **security/MasterPasswordHolder** — хранит мастер-пароль в `char[]`.
- **clipboard/ClipboardService** — интерфейс + `SystemClipboardService` (реализация копирования в буфер).
- **config/AppConfig.java** — конфигурация Spring Context.
- **App.java** — точка входа, CLI-обработчик.

---

### 🔒 Безопасность:

- Мастер-пароль хранится в `char[]` и может быть **обнулён вручную** (в `shutdown hook`).
- Пароли не отображаются на экране при вводе и расшифровке.
- Шифрование происходит через `AES/CBC/PKCS5Padding`, ключ генерируется из мастер-пароля через `PBKDF2WithHmacSHA256`.

---

### 🧠 Дополнительно (по желанию):

- Добавить **графический интерфейс JavaFX**, в котором:
   - пользователь может добавить и удалить записи;
   - таблица отображает логины и сайты;
   - кнопка "Показать пароль" копирует его в буфер (не отображает);
   - мастер-пароль запрашивается при запуске.

---

## 🔧 Подсказки

### 📥 Чтение мастер-пароля:

```java
System.out.print("Введите мастер-пароль: ");
char[] masterPassword = System.console() != null
        ? System.console().readPassword()
        : scanner.nextLine().toCharArray();
```

---

### 📋 Копирование в буфер:

```java
Toolkit.getDefaultToolkit()
       .getSystemClipboard()
       .setContents(new StringSelection(password), null);
```

---

### 🧼 Очистка мастер-пароля в конце:

```java
Runtime.getRuntime().addShutdownHook(new Thread(() -> {
    MasterPasswordHolder holder = context.getBean(MasterPasswordHolder.class);
    holder.clear(); // обнулить char[] в памяти
}));
```

---

## 📁 Пример структуры проекта:

```
PasswordManager/
├── App.java
├── config/
│   └── AppConfig.java
├── model/
│   └── PasswordEntry.java
├── service/
│   └── PasswordService.java
├── crypto/
│   ├── EncryptionService.java
│   └── AesEncryptionService.java
├── security/
│   └── MasterPasswordHolder.java
├── repository/
│   ├── PasswordRepository.java
│   └── InMemoryPasswordRepository.java
├── clipboard/
│   ├── ClipboardService.java
│   └── SystemClipboardService.java
```

---

## 🧠 Что оценивается:
- корреткное разделение логики на слои;
- грамотное использование Spring для внедрения зависимостей;
- безопасная работа с данными (`char[]`, шифрование);
- умение работать с консольным вводом, буфером обмена;
- (дополнительно) использование JavaFX или сериализации.

 */

@TaskDescription(taskNumber = 20, taskDescription = "Password Manager с Spring и шифрованием")
public class Task20 implements Solution {
    private static final Logger logger = LoggerFactory.getLogger(Solution.class);

    @Override
    public void execute() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("Менеджер паролей\n");

        MasterPasswordService masterPasswordService = context.getBean(MasterPasswordService.class);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            masterPasswordService.clearMasterPasswordFromMemory();
            logger.info("Мастер-пароль очищен из памяти (из shutdownHook).");
            System.out.println("Мастер-пароль очищен из памяти.");
        }));

        PasswordService passwordService = context.getBean(PasswordService.class);

        Scanner scanner = new Scanner(System.in);
        String currentInput = "";
        while (!currentInput.equals("выход")) {
            List<PasswordEntry> savedPasswords = passwordService.getAll();
            System.out.println("У вас сохранено паролей: " + savedPasswords.size());
            System.out.println("Выберите, что вы хотите сделать:\nНайти пароли для сайта (1)\nДобавить новый (2)");

            currentInput = scanner.nextLine().trim();

            if (currentInput.equals("1")) {
                logger.info("Пользователь выбрал добавить пароль.");
                System.out.println("Введите адрес сайта: ");
                String site = scanner.nextLine().trim();
                List<PasswordEntry> foundedPasswords = passwordService.getPasswordsBySite(site);
                if (foundedPasswords.isEmpty()) {
                    System.out.println("Паролей не найдено.");
                } else {
                    System.out.println("Найдены следующие пароли: ");
                    for (PasswordEntry password : foundedPasswords) {
                        System.out.println(password);
                        System.out.println("Скопировать в буфер (1)\nУдалить (2)");
                        currentInput = scanner.nextLine().trim();
                        if (currentInput.equals("1")) {
                            logger.info("Пользователь выбрал скопировать пароль в буфер.");
                            passwordService.copyToClipboard(password);
                        } else if (currentInput.equals("2")) {
                            System.out.println("Вы уверены что хотите удалить пароль? (да/нет)");
                            currentInput = scanner.nextLine().trim();
                            if (currentInput.equals("да")) {
                                logger.info("Пользователь выбрал избавиться от пароля навсегда :(.");
                                passwordService.deletePassword(password.getId());
                            }
                        }
                    }
                }
            } else if (currentInput.equals("2")) {
                logger.info("Пользователь выбрал добавить новый пароль.");
                System.out.println("Введите название сайта: ");
                String site = scanner.nextLine().trim();
                System.out.println("Введите логин: ");
                String login = scanner.nextLine().trim();

                List<PasswordEntry> existingPasswords = passwordService.getPasswordsBySiteAndLogin(site, login);
                if (!existingPasswords.isEmpty()) {
                    System.out.println("Пароль для этого сайта и логина уже существует, вы хотите обновить его? (да/нет)");
                    currentInput = scanner.nextLine().trim();
                    if (currentInput.equals("нет")) {
                        continue;
                    }

                    logger.info("Пользователь выбрал обновить существующий пароль.");
                    System.out.println("Введите новый пароль: ");
                    String notEncryptedPassword = scanner.nextLine().trim();
                    passwordService.updatePassword(existingPasswords.get(0), notEncryptedPassword);
                    System.out.println("Пароль успешно обновлен.");
                    logger.info("Пользователь обновил существующий пароль.");
                } else {
                    System.out.println("Введите пароль: ");
                    String notEncryptedPassword = scanner.nextLine().trim();
                    passwordService.savePassword(site, login, notEncryptedPassword);
                    System.out.println("Пароль успешно сохранен.");
                    logger.info("Пользователь добавил новый пароль.");
                }
            }
        }
    }
}
