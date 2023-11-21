package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.DatePickerComponent;
import pages.components.ModalWindowComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {
    DatePickerComponent datePickerComponent = new DatePickerComponent();
    ModalWindowComponent modalWindowComponent = new ModalWindowComponent();
    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            datePickerInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            uploadPictureInput = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateInput = $("#state"),
            stateCityWrapper = $("#stateCity-wrapper"),
            cityInput = $("#city"),
            submitButton = $("#submit"),
            modalWindow = $(".modal-dialog"),
            modalHeader = $("#example-modal-sizes-title-lg"),
            pageTitle = $(".practice-form-wrapper");


    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        killBanner();

        return this;
    }

    public void killBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }

    public PracticeFormPage checkTitle(String value) {
        pageTitle.shouldHave(text(value));

        return this;
    }

    public PracticeFormPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public PracticeFormPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public PracticeFormPage setUserEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public PracticeFormPage setGender(String value) {
        genderWrapper.$(byText(value))
                .click();

        return this;
    }

    public PracticeFormPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public PracticeFormPage setDateOfBirth(String year, String month, int day) {
        datePickerInput.click();
        datePickerComponent.setDateOfBirth(year, month, day);
        return this;
    }

    public PracticeFormPage setHobbies(String value) {
        hobbiesWrapper.$(byText(value))
                .click();

        return this;
    }

    public PracticeFormPage setSubjects(String value) {
        subjectsInput.setValue(value)
                .pressEnter();

        return this;
    }

    public PracticeFormPage uploadPicture(String filename) {
        uploadPictureInput.uploadFromClasspath(filename);

        return this;
    }

    public PracticeFormPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);

        return this;
    }

    public PracticeFormPage setState(String value) {
        stateInput.click();
        stateCityWrapper.$(byText(value))
                .click();

        return this;
    }

    public PracticeFormPage setCity(String value) {
        cityInput.click();
        stateCityWrapper.$(byText(value))
                .click();

        return this;
    }

    public void pressSubmit() {
        submitButton.click();

    }

    public PracticeFormPage checkModalWindowHeader(String value) {
        modalWindow.should(appear);
        modalHeader.shouldHave(text(value));

        return this;
    }

    public PracticeFormPage checkTableResult(String key, String value) {
        modalWindowComponent.checkTable(key, value);

        return this;
    }

    public void checkBorderColorUserNumberInput(String key, String value) {
        userNumberInput.shouldHave(cssValue(key, value));

    }
}
