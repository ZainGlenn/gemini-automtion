package com.gemini.util;

import com.codeborne.selenide.Configuration;
import com.gemini.exception.GeminiTestRuntimeException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BrowserSettings {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    private ChromeOptions chromeOptions;

    public BrowserSettings() {
        log.info("Setting up chrome browser run configurations");
    }


    public BrowserSettings setWindowSize() {
        log.info("Setting browser size");
        TestUtil testUtil = TestUtil.getInstance();
        String windowSize = testUtil.getProperty("window.size");
        if (windowSize != null && !windowSize.isEmpty()) {
            Configuration.browserSize = windowSize;
        }
        log.info("Completed Setting browser size");
        return this;
    }

    public BrowserSettings setChromeOptions(boolean disableNotifications,boolean overrideHeadless) {
        log.info("Setting chrome options");
        TestUtil testUtil = TestUtil.getInstance();
        String prefs = "intl.accept_languages=" + testUtil.getLocale();
        if (disableNotifications) {
            // 2 = block   1 = allow  0 = ask
            prefs = prefs + ",profile.default_content_setting_values.media_stream_camera=1";
        }
        System.setProperty("chromeoptions.prefs", prefs);
        System.setProperty("chromeoptions.args", "--disable-gpu,--no-sandbox,--disable-dev-shm-usage,--use-fake-device-for-media-stream");
        if(overrideHeadless){
            Configuration.headless = false;
        }else{
            Configuration.headless = testUtil.isJenkins();
        }

        log.info("Completed Setting chrome options");
        Configuration.holdBrowserOpen=testUtil.isHolderBrowserOpen();
        return this;
    }

    public BrowserSettings setSelenoid() {
        log.info("Checking if selenoid is configured");
        TestUtil testUtil = TestUtil.getInstance();
        if (testUtil.isRemote()) {
            log.info("Selenoid is configured building url");
            Configuration.browser = "chrome";
            String baseUrl = System.getenv("SELENOID_URL");
            baseUrl = !Objects.isNull(baseUrl) ? baseUrl : testUtil.remoteUrl();
            if (Objects.isNull(baseUrl)) {
                throw new GeminiTestRuntimeException("No Selenoid URL set", new Throwable());
            }
            String urlBuilder = String.format("%s/wd/hub", baseUrl);
            log.info("(ENV) Setting url to {}", urlBuilder);
            Configuration.remote = urlBuilder;
        }
        return this;
    }

    public BrowserSettings setDefaultDesiredCapabilities() {
        log.info("Setting Desired Capabilities");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String vnc = System.getenv("ENABLE_VNC");
        String video = System.getenv("ENABLE_VIDEO");

        if (!Objects.isNull(vnc)) {
            capabilities.setCapability("enableVNC", Boolean.parseBoolean(vnc));
        }

        if (!Objects.isNull(video)) {
            capabilities.setCapability("enableVideo", Boolean.parseBoolean(video));
        }

        Configuration.browserCapabilities = capabilities;
        log.info("Completed Setting Desired Capabilities");
        return this;
    }

    private void createChromeOptions(boolean headless, String localisation, String windowSize) {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("intl.accept_languages", localisation);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--use-fake-device-for-media-stream");
        if (headless) {
            chromeOptions.setHeadless(true);
        }
        setWindowSizeWhenNotSelenide(windowSize);
    }

    private void setWindowSizeWhenNotSelenide(String windowSize) {
        if (windowSize != null && !windowSize.isEmpty()) {
            log.debug("Setting window size to [{}].", windowSize);
            String sizes = windowSize.replace("x", ",");
            chromeOptions.addArguments("window-size=" + sizes);
        }
    }

    public void createDriverWithoutSelenide() {
        TestUtil testUtil = TestUtil.getInstance();
        if (!testUtil.isSelenoid()) {
            whenNotSelenide(testUtil);
        }
    }

    private void whenNotSelenide(TestUtil testUtil) {
        if (chromeOptions == null) {
            chromeOptions = new ChromeOptions();
        }

        String jenkinsProperty = testUtil.getProperty("jenkins.headless");
        String windowSize = testUtil.getProperty("window.size");
        boolean isJenkins = false;
        if (jenkinsProperty != null) {
            isJenkins = Boolean.parseBoolean(jenkinsProperty);
        }

        if (isJenkins) {
            log.info("Running in headless mode!!!");
            System.setProperty("chromeoptions.args", "--headless,--disable-gpu,--no-sandbox,--disable-dev-shm-usage, --use-fake-device-for-media-stream");
            createChromeOptions(true, testUtil.getLocale(), windowSize);
        } else {
            System.setProperty("chromeoptions.args", "--disable-gpu,--no-sandbox,--disable-dev-shm-usage, --use-fake-device-for-media-stream");
            createChromeOptions(false, testUtil.getLocale(), windowSize);
        }
    }

    public WebDriver getDriver() {
        return new ChromeDriver(chromeOptions);
    }
}
