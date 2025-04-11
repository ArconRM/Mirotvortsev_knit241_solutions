package org.knit.solutions.task20.PasswordManager.service;

import org.knit.solutions.task20.PasswordManager.model.PasswordEntry;

import java.util.List;
import java.util.UUID;

public interface PasswordService {
    void savePassword(String site, String login, String notEncryptedPassword);

    List<PasswordEntry> getPasswordsBySite(String site);

    List<PasswordEntry> getPasswordsBySiteAndLogin(String site, String login);

    List<PasswordEntry> getAll();

    void copyToClipboard(PasswordEntry passwordEntry);

    void updatePassword(PasswordEntry oldPasswordEntry, String newNotEncryptedPassword);

    void deletePassword(UUID id);
}
