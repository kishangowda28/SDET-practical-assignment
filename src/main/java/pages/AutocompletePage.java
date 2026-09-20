package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.List;

public class AutocompletePage {
    private final Page page;
    private final Locator inputField;
    private final Locator suggestions;
    private final Locator nextButton;
    private final Locator errorMessage;
    private final Locator successMessage;

    public AutocompletePage(Page page) {
        this.page = page;
        inputField = page.locator("#input-field");
        suggestions = page.locator(".suggestions li");
        nextButton = page.locator("#next-button");
        errorMessage = page.locator(".error-message");
        successMessage = page.locator(".success-container p");
    }

    public void open(String url) {
        page.navigate(url);
    }

    public void enterText(String text) {
        inputField.fill(text);
    }

    public void pressTab() {
        inputField.press("Tab");
    }

    public void pressEnter() {
        inputField.press("Enter");
    }

    public void pressEscape() {
        inputField.press("Escape");
    }

    public void clickSuggestion(String suggestion) {
        page.locator(".suggestions li")
            .filter(new Locator.FilterOptions().setHasText(suggestion))
            .click();
    }

    public String getInputValue() {
        return inputField.inputValue();
    }

    public List<String> getSuggestions() {
        return suggestions.allTextContents();
    }

    public int getVisibleSuggestionCount() {
        return page.locator(".suggestions li:visible").count();
    }

    public void clickNext() {
        nextButton.click();
    }

    public boolean isSuccessMessageVisible() {
        return successMessage.isVisible();
    }

    public boolean isErrorMessageVisible() {
        return errorMessage.isVisible();
    }
}
