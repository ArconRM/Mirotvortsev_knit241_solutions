package org.knit.solutions.task20.PasswordManager.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

@Repository
public class JsonMasterPasswordRepository implements MasterPasswordRepository {
    private static final Logger logger = LoggerFactory.getLogger(JsonMasterPasswordRepository.class);
    private static final String FILE_PATH = "src/main/java/org/knit/solutions/task20/PasswordManager/assets/masterPassword.json";
    private final Gson gson;

    public JsonMasterPasswordRepository() {
        gson = new Gson();
    }

    @Override
    public char[] saveMasterPassword(char[] password) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(password, writer);
            return password;
        } catch (IOException e) {
            logger.error("Ошибка сохранения мастер пароля", e);
            throw new RuntimeException("Ошибка сохранения мастер пароля", e);
        }
    }

    @Override
    public char[] getMasterPassword() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type type = new TypeToken<char[]>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            logger.error("Ошибка получения мастер пароля", e);
            throw new RuntimeException("Ошибка получения мастер пароля", e);
        }
    }
}
