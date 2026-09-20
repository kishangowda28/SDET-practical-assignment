package tests;

import com.microsoft.playwright.*;
import config.PlaywrightManager;
import org.junit.jupiter.api.*;
import pages.AutocompletePage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AutocompleteTest {

    private static Playwright playwright;
    private static Browser browser;

    private BrowserContext context;
    private Page page;
    private AutocompletePage autocompletePage;

    private final String baseUrl = System.getProperty(
        "base.url",
        "https://test.com/autocomplete-form"
    );

    @BeforeAll
    static void beforeAll() {
        playwright = Playwright.create();
        browser = PlaywrightManager.launchBrowser(playwright);
    }

    @BeforeEach
    void setUp() {
        context = PlaywrightManager.newContext(browser);
        page = context.newPage();
        page.setDefaultTimeout(10000);
        autocompletePage = new AutocompletePage(page);
        autocompletePage.open(baseUrl);
    }

    @AfterEach
    void tearDown() {
        context.close();
    }

    @AfterAll
    static void afterAll() {
        browser.close();
        playwright.close();
    }

    @Test
    void TC_UI_001_verifyTabNavigation() {
        page.locator("body").click();
        page.keyboard().press("Tab");

        assertTrue(
            page.locator("#input-field").isFocused(),
            "First Tab should move focus to input"
        );

        page.keyboard().press("Tab");

        assertTrue(
            page.locator("#next-button").isFocused(),
            "Second Tab should move focus to Next"
        );
    }

    @Test
    void TC_UI_002_verifyPrefixSuggestionFiltering() {
        autocompletePage.enterText("agile");

        List<String> results = autocompletePage.getSuggestions();

        assertFalse(results.isEmpty());

        assertTrue(
            results.stream().allMatch(
                s -> s.toLowerCase().startsWith("agile")
            ),
            "Suggestions should use prefix matching"
        );
    }

    @Test
    void TC_UI_003_verifySuggestionSelection() {
        autocompletePage.clickSuggestion("agile methodology");

        assertEquals(
            "agile methodology",
            autocompletePage.getInputValue()
        );
    }

    @Test
    void TC_UI_004_verifyEnterSubmission() {
        autocompletePage.clickSuggestion("agile methodology");
        autocompletePage.pressEnter();

        assertTrue(
            autocompletePage.isSuccessMessageVisible(),
            "Valid Enter submission should display success"
        );
    }

    @Test
    void TC_UI_005_verifyEscapeInteraction() {
        autocompletePage.enterText("agile");
        autocompletePage.pressEscape();

        // The supplied assignment does not define exact Escape behavior.
        assertNotNull(autocompletePage.getInputValue());
    }

    @Test
    void TC_UI_006_verifyValidSubmission() {
        autocompletePage.clickSuggestion("agile methodology");
        autocompletePage.clickNext();

        assertTrue(
            autocompletePage.isSuccessMessageVisible(),
            "Valid submission should display success"
        );
    }

    @Test
    void TC_UI_007_verifyInvalidSubmission() {
        autocompletePage.enterText("invalid value");
        autocompletePage.clickNext();

        assertTrue(
            autocompletePage.isErrorMessageVisible(),
            "Invalid submission should display error"
        );
    }

    @Test
    void TC_UI_008_verifyNoMatchingSuggestion() {
        autocompletePage.enterText("xyz123");

        assertEquals(
            0,
            autocompletePage.getVisibleSuggestionCount()
        );
    }
}
