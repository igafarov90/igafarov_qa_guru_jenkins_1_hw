package pages.widgets;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DatePicker {

    private final SelenideElement setYear = $(".react-datepicker__year-select"),
            setMonth = $(".react-datepicker__month-select"),
            setDay = $(".react-datepicker__month");

    public void setDate(String year, String month, int day) {
        setYear.selectOption(year);
        setMonth.selectOption(month);
        setDay.$(byText(String.valueOf(day))).click();
    }

}
