package com.gemini.util.steps.context;

import com.gemini.util.BrowserSettings;
import com.thoughtworks.gauge.BeforeScenario;

public class HookSteps {
    @BeforeScenario
    public void BeforeScenario() {
        new BrowserSettings().setSelenoid()
                .setDefaultDesiredCapabilities()
                .setChromeOptions(false,false)
                .setWindowSize();
    }
}
