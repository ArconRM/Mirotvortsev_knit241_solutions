package org.knit.solutions.task20.PasswordManager;

import org.knit.solutions.task20.PasswordManager.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PasswordManagerApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
    }
}
