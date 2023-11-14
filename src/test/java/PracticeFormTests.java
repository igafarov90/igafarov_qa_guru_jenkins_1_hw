import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.PracticeFormPage;

public class PracticeFormTests extends TestBase {
    TestData testData = new TestData();
    PracticeFormPage practiceFormPage = new PracticeFormPage();

    @Test()
    void successFillPracticeFormAllFields() {
        practiceFormPage.openPage()
                .checkTitle(testData.pageTitle)
                .setFirstName(testData.userName)
                .setLastName(testData.lastName)
                .setUserEmail(testData.userEmail)
                .setGender(testData.userGender)
                .setUserNumber(testData.userMobile)
                .setDateOfBirth(testData.year, testData.month, testData.date)
                .setSubjects(testData.subjects)
                .setHobbies(testData.userHobbies)
                .uploadPicture("test.jpg")
                .setCurrentAddress(testData.userAddress)
                .setState(testData.state)
                .setCity(testData.city)
                .pressSubmit();

        practiceFormPage.checkModalWindowHeader(testData.modalWindowHeader)
                .checkTableResult("Student Name", testData.userName + " " + testData.lastName)
                .checkTableResult("Student Email", testData.userEmail)
                .checkTableResult("Gender", testData.userGender)
                .checkTableResult("Mobile", testData.userMobile)
                .checkTableResult("Date of Birth", testData.date + " " + testData.month + "," + testData.year)
                .checkTableResult("Subjects", testData.subjects)
                .checkTableResult("Hobbies", testData.userHobbies)
                .checkTableResult("Picture", "test.jpg")
                .checkTableResult("Address", testData.userAddress)
                .checkTableResult("State and City", testData.state + " " + testData.city);
    }

    @Test
    void successFillPracticeFormRequiredFields() {
        practiceFormPage.openPage()
                .setFirstName(testData.userName)
                .setLastName(testData.lastName)
                .setGender(testData.userGender)
                .setUserNumber(testData.userMobile)
                .setDateOfBirth(testData.year, testData.month, testData.date)
                .pressSubmit();
        practiceFormPage.checkModalWindowHeader(testData.modalWindowHeader)
                .checkTableResult("Student Name", testData.userName + " " + testData.lastName)
                .checkTableResult("Gender", testData.userGender)
                .checkTableResult("Mobile", testData.userMobile)
                .checkTableResult("Date of Birth", testData.date + " " + testData.month + "," + testData.year);
    }

    @Test
    void checkMultiSelectHobbies(){
        practiceFormPage.openPage()
                .setFirstName(testData.userName)
                .setLastName(testData.lastName)
                .setGender(testData.userGender)
                .setUserNumber(testData.userMobile)
                .setDateOfBirth(testData.year, testData.month, testData.date)
                .setHobbies(testData.userHobbies)
                .setHobbies(testData.secondHobby)
                .pressSubmit();
        practiceFormPage.checkModalWindowHeader(testData.modalWindowHeader)
                .checkTableResult("Student Name", testData.userName + " " + testData.lastName)
                .checkTableResult("Gender", testData.userGender)
                .checkTableResult("Mobile", testData.userMobile)
                .checkTableResult("Date of Birth", testData.date + " " + testData.month + "," + testData.year)
                .checkTableResult("Hobbies", testData.userHobbies + ", " + testData.secondHobby);       ;
    }

    @ParameterizedTest()
    @ValueSource(strings = {
            "",
            "десятьбукв",
            "111111111"
    })
    void fillMobileNumberByInvalidData(String value) {
        practiceFormPage.openPage()
                .setUserNumber(value)
                .pressSubmit();
        practiceFormPage.checkBorderColorUserNumberInput("border-color", testData.inputBorderColorError);
    }

    @ParameterizedTest()
    @NullAndEmptySource
    void fillMobileNumberByNullAndEmpty(String value) {
        practiceFormPage.openPage()
                .setUserNumber(value)
                .pressSubmit();
        practiceFormPage.checkBorderColorUserNumberInput("border-color", testData.inputBorderColorError);
    }
}
