import com.github.javafaker.Faker;

public class TestData {
    Faker faker = new Faker();
    public String pageTitle = "Student Registration Form",
            modalWindowHeader = "Thanks for submitting the form",
            userName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress("com"),
            userGender = faker.options().option("Male", "Other", "Female"),
            userMobile = faker.phoneNumber().subscriberNumber(10),
            userHobbies = faker.options().option("Sports", "Reading", "Music"),
            month = faker.options().option("January", "February", "March", "April",
                    "May", "June", "July", "August",
                    "September", "October", "November", "December"),
            year = String.valueOf(faker.number().numberBetween(1980, 2020)),
            subjects = faker.options().option("Computer Science", "Commerce",
                    "Chemistry", "English", "Economics", "Social Studies"),
            userAddress = faker.address().fullAddress(),
            state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"),
            city = setCity(state),
            secondHobby = setSecondHobby(userHobbies),
            inputBorderColorError = "rgb(220, 53, 69)",
            picture = "test.jpg";
    int date = faker.number().numberBetween(1, 28);

    public String setCity(String state) {
        switch (state) {
            case "NCR":
                return faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh":
                return faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana":
                return faker.options().option("Karnal", "Panipat");
            case "Rajasthan":
                return faker.options().option("Jaipur", "Jaiselmer");

            default:
                return null;
        }
    }

    public String setSecondHobby(String userHobbies) {
        switch (userHobbies) {
            case ("Sports"):
                return faker.options().option("Reading", "Music");
            case ("Reading"):
                return faker.options().option("Sports", "Music");
            case ("Music"):
                return faker.options().option("Sports", "Reading");
            default:
                return null;
        }
    }
}
