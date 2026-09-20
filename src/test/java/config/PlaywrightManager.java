package config;

import com.microsoft.playwright.*;

public final class PlaywrightManager {
    private PlaywrightManager() {}

    public static Browser launchBrowser(Playwright playwright) {
        boolean headless = Boolean.parseBoolean(
            System.getProperty("headless", "false")
        );

        return playwright.chromium().launch(
            new BrowserType.LaunchOptions().setHeadless(headless)
        );
    }

    public static BrowserContext newContext(Browser browser) {
        return browser.newContext(
            new Browser.NewContextOptions().setLocale("en-IN")
        );
    }
}
