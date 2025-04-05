package org.knit.solutions.task20.PasswordManager.clipboard;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

@Component
public class ClipboardManagerImpl implements ClipboardManager {
    @Override
    public void copyToClipboard(String content) {
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(new StringSelection(content), null);
    }
}
