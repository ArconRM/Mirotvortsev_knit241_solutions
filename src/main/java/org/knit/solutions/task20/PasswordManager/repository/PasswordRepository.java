package org.knit.solutions.task20.PasswordManager.repository;

import org.knit.solutions.task20.PasswordManager.model.PasswordEntry;

import java.util.List;
import java.util.UUID;

public interface PasswordRepository {
    void savePassword(PasswordEntry newPasswordEntry);

    List<PasswordEntry> getPasswordsBySite(String site);

    List<PasswordEntry> getPasswordsBySiteAndLogin(String site, String login);

    List<PasswordEntry> getAllPasswords();

    void updatePassword(PasswordEntry updatedPasswordEntry);

    void deletePassword(UUID id);
}
