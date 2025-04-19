package org.knit.solutions.task20.PasswordManager;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.knit.solutions.task20.PasswordManager.config.AppConfig;
import org.knit.solutions.task20.PasswordManager.model.PasswordEntry;
import org.knit.solutions.task20.PasswordManager.security.MasterPasswordService;
import org.knit.solutions.task20.PasswordManager.service.PasswordService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Slf4j
@Component
public class PasswordManagerApplicationListener {
    @EventListener
    public void startEvent(ContextRefreshedEvent event) {
        log.info("Application started {}", event);

        ApplicationContext context = event.getApplicationContext();

        System.out.println("Менеджер паролей\n");

        MasterPasswordService masterPasswordService = context.getBean(MasterPasswordService.class);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            masterPasswordService.clearMasterPasswordFromMemory();
            log.info("Мастер-пароль очищен из памяти (из shutdownHook).");
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
                log.info("Пользователь выбрал добавить пароль.");
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
                            log.info("Пользователь выбрал скопировать пароль в буфер.");
                            passwordService.copyToClipboard(password);
                        } else if (currentInput.equals("2")) {
                            System.out.println("Вы уверены что хотите удалить пароль? (да/нет)");
                            currentInput = scanner.nextLine().trim();
                            if (currentInput.equals("да")) {
                                log.info("Пользователь выбрал избавиться от пароля навсегда :(.");
                                passwordService.deletePassword(password.getId());
                            }
                        }
                    }
                }
            } else if (currentInput.equals("2")) {
                log.info("Пользователь выбрал добавить новый пароль.");
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

                    log.info("Пользователь выбрал обновить существующий пароль.");
                    System.out.println("Введите новый пароль: ");
                    String notEncryptedPassword = scanner.nextLine().trim();
                    passwordService.updatePassword(existingPasswords.get(0), notEncryptedPassword);
                    System.out.println("Пароль успешно обновлен.");
                    log.info("Пользователь обновил существующий пароль.");
                } else {
                    System.out.println("Введите пароль: ");
                    String notEncryptedPassword = scanner.nextLine().trim();
                    passwordService.savePassword(site, login, notEncryptedPassword);
                    System.out.println("Пароль успешно сохранен.");
                    log.info("Пользователь добавил новый пароль.");
                }
            }
        }
    }

    @EventListener
    @SneakyThrows
    public void anyEvent(ContextClosedEvent event) {
        log.info("close context {}", event);
    }
}
