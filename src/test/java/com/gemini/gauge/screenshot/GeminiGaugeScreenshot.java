package com.gemini.gauge.screenshot;

import com.codeborne.selenide.Selenide;
import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.GaugeConstant;
import com.thoughtworks.gauge.screenshot.CustomScreenshotWriter;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GeminiGaugeScreenshot implements CustomScreenshotWriter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String takeScreenshot() {
        try {
            String screenshotPath = Selenide.screenshot(String.format("%d", System.currentTimeMillis()));
            if(screenshotPath == null){
                return "";
            }
            Gauge.writeMessage("XScreenshotPath: " + screenshotPath);
            File screenshotFile = new File(screenshotPath);
            File gaugeScreenshotFile = gaugeScreenshotFile(screenshotFile.getName());
            IOUtils.copy(new FileInputStream(screenshotFile), new FileOutputStream(gaugeScreenshotFile));
            return screenshotFile.getName();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private File gaugeScreenshotFile(String fileName) {
        Path path = Paths.get(System.getenv(GaugeConstant.SCREENSHOTS_DIR_ENV), fileName);
        return new File(path.toAbsolutePath().toString());
    }
}