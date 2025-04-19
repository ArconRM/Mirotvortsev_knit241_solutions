package org.knit.solutions.task20.PasswordManager.clipboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class ClipboardManagerImpl implements ClipboardManager {
    private static final Logger logger = LoggerFactory.getLogger(ClipboardManagerImpl.class);
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private static final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    private static final int DELAY_BEFORE_CLEANING = 30;

    @Override
    public void copyToClipboard(String content) {
        clipboard.setContents(new StringSelection(content), null);
        clearClipboard(DELAY_BEFORE_CLEANING);
        logger.info("В буфер положена новая строка.");
    }

    @Override
    public void clearClipboard(int delay) {
        scheduler.schedule(() -> {
            clipboard.setContents(new StringSelection(""), null);
            System.out.println("Буфер очищен.");
            logger.info("Буфер очищен с задержкой: {}", delay);
        }, delay, TimeUnit.SECONDS);
    }
}
