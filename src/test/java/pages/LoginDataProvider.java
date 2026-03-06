package pages;

import org.testng.annotations.DataProvider; // ⚠️ Muy importante

public class LoginDataProvider {

    @DataProvider(name = "invalidLoginData")
    public static Object[][] invalidLoginData() {
        return new Object[][] {
                {"admin@codewi14.com", "codewise123"},
                {"admin@codewise.com", "codewise45"},
                {"admin@codewise.com", ""},
                {"", "codewise123"},
                {"", ""}
        };
    }

    @DataProvider(name = "validLoginData")
    public static Object[][] validLoginData() {
        return new Object[][] {
                {"admin@codewise.com", "codewise123"}
        };
    }
}
