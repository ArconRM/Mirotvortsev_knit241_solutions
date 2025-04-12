package org.knit.solutions.task20.PasswordManager.clipboard;

public interface ClipboardManager {
    void copyToClipboard(String content);

    void clearClipboard(int delay);
}
