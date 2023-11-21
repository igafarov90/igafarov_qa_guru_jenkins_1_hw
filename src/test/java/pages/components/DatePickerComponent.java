package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DatePickerComponent {

    private final SelenideElement yearOfBirth = $(".react-datepicker__year-select"),
            monthOfBirth = $("react-datepicker__month-select"),
            birthday = $(".react-datepicker__month:not(.react-datepicker__day--outside-month)");

    public void setDateOfBirth(String year, String month, int day) {
        yearOfBirth.selectOption(year);
        monthOfBirth.selectOption(month);
        birthday.$(byText(String.valueOf(day))).click();
    }
}
