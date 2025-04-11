package org.knit.solutions.task20.PasswordManager.repository;

import java.io.IOException;

public interface MasterPasswordRepository {
    char[] saveMasterPassword(char[] password);

    char[] getMasterPassword();
}
