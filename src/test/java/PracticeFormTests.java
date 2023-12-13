import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.PracticeFormPage;

import static io.qameta.allure.Allure.step;

public class PracticeFormTests extends TestBase {
    TestData testData = new TestData();
    PracticeFormPage practiceFormPage = new PracticeFormPage();

    @Tag("practice_form")
    @Owner("igafarov")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Успешное заполнения формы регистрации, все поля")
    @Test()
    void successFillPracticeFormAllFields() {
        step("Открыть форму регистрации", () -> {
            practiceFormPage.openPage()
                    .checkTitle(testData.pageTitle);
        });
        step("Заполнить все поля в форме и нажать submit", () -> {
            practiceFormPage.setFirstName(testData.userName)
                    .setLastName(testData.lastName)
                    .setUserEmail(testData.userEmail)
                    .setGender(testData.userGender)
                    .setUserNumber(testData.userMobile)
                    .setDateOfBirth(testData.year, testData.month, testData.date)
                    .setSubjects(testData.subjects)
                    .setHobbies(testData.userHobbies)
                    .uploadPicture(testData.picture)
                    .setCurrentAddress(testData.userAddress)
                    .setState(testData.state)
                    .setCity(testData.city)
                    .pressSubmit();
        });
        step("Проверка заполнения таблицы в модальном окне", () -> {
            practiceFormPage.checkModalWindowHeader(testData.modalWindowHeader)
                    .checkTableResult("Student Name", testData.userName + " " + testData.lastName)
                    .checkTableResult("Student Email", testData.userEmail)
                    .checkTableResult("Gender", testData.userGender)
                    .checkTableResult("Mobile", testData.userMobile)
                    .checkTableResult("Date of Birth", testData.date + " " + testData.month + "," + testData.year)
                    .checkTableResult("Subjects", testData.subjects)
                    .checkTableResult("Hobbies", testData.userHobbies)
                    .checkTableResult("Picture", testData.picture)
                    .checkTableResult("Address", testData.userAddress)
                    .checkTableResult("State and City", testData.state + " " + testData.city);
        });
    }

    @Tag("practice_form")
    @Owner("igafarov")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Успешное заполнения формы регистрации, только обязательные поля")
    @Test
    void successFillPracticeFormRequiredFields() {
        step("Открыть форму регистрации", () -> {
            practiceFormPage.openPage();
        });
        step("Заполнить все поля в форме и нажать submit", () -> {
            practiceFormPage.setFirstName(testData.userName)
                    .setLastName(testData.lastName)
                    .setGender(testData.userGender)
                    .setUserNumber(testData.userMobile)
                    .setDateOfBirth(testData.year, testData.month, testData.date)
                    .pressSubmit();
        });
        step("Проверка заполнения таблицы в модальном окне", () -> {
            practiceFormPage.checkModalWindowHeader(testData.modalWindowHeader)
                    .checkTableResult("Student Name", testData.userName + " " + testData.lastName)
                    .checkTableResult("Gender", testData.userGender)
                    .checkTableResult("Mobile", testData.userMobile)
                    .checkTableResult("Date of Birth", testData.date + " " + testData.month + "," + testData.year);
        });
    }

    @Tag("practice_form")
    @Owner("igafarov")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Заполнение поля Телефон вводом невалидных данных")
    @ParameterizedTest()
    @ValueSource(strings = {
            "11111111111",
            "д!с%тьб:кв",
            "111111111"
    })
    void fillMobileNumberByInvalidData(String value) {
        step("Открыть форму регистрации", () -> {
            practiceFormPage.openPage();
        });
        step("Ввести " + value + " поле Телефон и нажать submit", () -> {
            practiceFormPage.setUserNumber(value)
                    .pressSubmit();
        });
        step("Проверка изменения цвета границ поля", () -> {
            practiceFormPage.checkBorderColorUserNumberInput("border-color", testData.inputBorderColorError);
        });
    }

    @Tag("practice_form")
    @Owner("igafarov")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Заполнение поля Телефон ввод нулла и пустого значения ")
    @ParameterizedTest()
    @NullAndEmptySource
    void fillMobileNumberByNullAndEmpty(String value) {
        step("Открыть форму регистрации", () -> {
            practiceFormPage.openPage();
        });
        step("Ввести " + value + " поле Телефон и нажать submit", () -> {
            practiceFormPage.setUserNumber(value)
                    .pressSubmit();
        });
        step("Проверка изменения цвета границ поля", () -> {
            practiceFormPage.checkBorderColorUserNumberInput("border-color", testData.inputBorderColorError);
        });
    }
}
