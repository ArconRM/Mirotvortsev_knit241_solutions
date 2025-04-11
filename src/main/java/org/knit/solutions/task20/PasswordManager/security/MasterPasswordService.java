package org.knit.solutions.task20.PasswordManager.security;

public interface MasterPasswordService {
    char[] getMasterPassword();

    void saveMasterPassword(String masterPassword);

    void clearMasterPasswordFromMemory();
}
