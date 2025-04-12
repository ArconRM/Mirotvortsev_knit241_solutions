package org.knit.solutions.task20.PasswordManager.service;

import org.knit.solutions.task20.PasswordManager.clipboard.ClipboardManager;
import org.knit.solutions.task20.PasswordManager.crypto.EncryptionService;
import org.knit.solutions.task20.PasswordManager.model.PasswordEntry;
import org.knit.solutions.task20.PasswordManager.repository.PasswordRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class PasswordServiceImpl implements PasswordService {
    private final PasswordRepository repository;
    private final EncryptionService encryptionService;
    private final ClipboardManager clipboardManager;

    public PasswordServiceImpl(
            PasswordRepository repository,
            EncryptionService encryptionService,
            ClipboardManager clipboardManager
    ) {
        this.repository = repository;
        this.encryptionService = encryptionService;
        this.clipboardManager = clipboardManager;
    }

    @Override
    public void savePassword(String site, String login, String notEncryptedPassword) {
        String encryptedPassword = encryptionService.encrypt(notEncryptedPassword);
        PasswordEntry newPasswordEntry = new PasswordEntry(site, login, encryptedPassword);
        repository.savePassword(newPasswordEntry);
    }

    @Override
    public List<PasswordEntry> getPasswordsBySite(String site) {
        return repository.getPasswordsBySite(site);
    }

    @Override
    public List<PasswordEntry> getPasswordsBySiteAndLogin(String site, String login) {
        return repository.getPasswordsBySiteAndLogin(site, login);
    }

    @Override
    public List<PasswordEntry> getAll() {
        return repository.getAllPasswords();
    }

    @Override
    public void copyToClipboard(PasswordEntry passwordEntry) {
        clipboardManager.copyToClipboard(encryptionService.decrypt(passwordEntry.getEncryptedPassword()));
    }

    @Override
    public void updatePassword(PasswordEntry oldPasswordEntry, String newNotEncryptedPassword) {
        String encryptedPassword = encryptionService.encrypt(newNotEncryptedPassword);
        oldPasswordEntry.setEncryptedPassword(encryptedPassword);
        repository.updatePassword(oldPasswordEntry);
    }

    @Override
    public void deletePassword(UUID id) {
        repository.deletePassword(id);
    }
}
