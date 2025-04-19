package org.knit.solutions.task20.PasswordManager.security;

import java.util.Arrays;
import java.util.Scanner;

import org.knit.solutions.task20.PasswordManager.repository.MasterPasswordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MasterPasswordServiceImpl implements MasterPasswordService {
    private static final Logger logger = LoggerFactory.getLogger(MasterPasswordServiceImpl.class);
    private final MasterPasswordRepository repository;
    private char[] masterPassword;

    public MasterPasswordServiceImpl(MasterPasswordRepository repository) {
        this.repository = repository;
        masterPassword = repository.getMasterPassword();
        if (masterPassword == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите мастер-пароль: ");
            String masterPassword = scanner.nextLine();
            saveMasterPassword(masterPassword);
        }
    }

    @Override
    public char[] getMasterPassword() throws IllegalStateException {
        if (masterPassword != null) {
            return masterPassword;
        }
        logger.error("Не задан мастер пароль");
        throw new IllegalStateException();
    }

    @Override
    public void saveMasterPassword(String masterPassword) {
        this.masterPassword = repository.saveMasterPassword(masterPassword.toCharArray());
    }

    @Override
    public void clearMasterPasswordFromMemory() {
        if (masterPassword != null) {
            Arrays.fill(masterPassword, '\0');
        }
    }
}
