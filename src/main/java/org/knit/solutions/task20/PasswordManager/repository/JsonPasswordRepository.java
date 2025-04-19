package org.knit.solutions.task20.PasswordManager.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.knit.solutions.task20.PasswordManager.model.PasswordEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class JsonPasswordRepository implements PasswordRepository {
    private static final Logger logger = LoggerFactory.getLogger(JsonPasswordRepository.class);
    private static final String FILE_PATH = "src/main/resources/assets/passwords.json";
    private final Gson gson;

    public JsonPasswordRepository() {
        this.gson = new Gson();
    }

    @Override
    public void savePassword(PasswordEntry newPasswordEntry) {
        List<PasswordEntry> passwords = getAllPasswords();
        passwords.add(newPasswordEntry);
        writePasswords(passwords);
    }

    private void writePasswords(List<PasswordEntry> passwords) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(passwords, writer);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public List<PasswordEntry> getPasswordsBySite(String site) {
        return getAllPasswords()
                .stream()
                .filter(p -> p.getSite().contains(site))
                .collect(Collectors.toList());
    }

    @Override
    public List<PasswordEntry> getPasswordsBySiteAndLogin(String site, String login) {
        return getAllPasswords()
                .stream()
                .filter(p -> p.getSite().contains(site) && login.equals(p.getLogin()))
                .collect(Collectors.toList());
    }

    @Override
    public List<PasswordEntry> getAllPasswords() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type type = new TypeToken<List<PasswordEntry>>(){}.getType();
            List<PasswordEntry> passwords = gson.fromJson(reader, type);
            return passwords != null ? passwords : new ArrayList<>();
        } catch (IOException e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void updatePassword(PasswordEntry updatedPasswordEntry) {
        List<PasswordEntry> passwords = getAllPasswords();
        for (PasswordEntry existingPassword : passwords) {
            if (existingPassword.getId().equals(updatedPasswordEntry.getId())) {
                existingPassword.setSite(updatedPasswordEntry.getSite());
                existingPassword.setLogin(updatedPasswordEntry.getLogin());
                existingPassword.setEncryptedPassword(updatedPasswordEntry.getEncryptedPassword());
                break;
            }
        }
        writePasswords(passwords);
    }

    @Override
    public void deletePassword(UUID id) {
        List<PasswordEntry> passwords = getAllPasswords();
        passwords.removeIf(p -> p.getId().equals(id));
        writePasswords(passwords);
    }
}
