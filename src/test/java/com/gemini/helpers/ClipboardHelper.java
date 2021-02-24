package com.gemini.helpers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ClipboardHelper {

    public String retrieveTextUsingSelenium(SelenideElement element) throws IOException, UnsupportedFlavorException {
        element.click();
        pause(500);
        element.sendKeys(Keys.chord(Keys.META, "a"));
        element.sendKeys(Keys.chord(Keys.META, "c"));
        pause(1000);

        Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        if (systemClipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
            Object text = systemClipboard.getData(DataFlavor.stringFlavor);
            return (String) text;
        }
        return null;
    }

    private void pause(int seconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
