package pages.widgets;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class Table {
    private final SelenideElement table = $("table");

    public void checkTable(String key, String value) {
        table.$(byText(key))
                .parent()
                .shouldHave(text(value));
    }
}
