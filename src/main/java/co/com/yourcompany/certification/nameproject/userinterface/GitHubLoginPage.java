package co.com.yourcompany.certification.nameproject.userinterface;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.annotations.DefaultUrl;
import org.openqa.selenium.By;

@DefaultUrl("https://github.com/login")
public class GitHubLoginPage extends PageObject {
    public static final Target USERNAME_OR_EMAIL_ADDRESS = Target.the("Username or email address field")
                                                            .located(By.id("login_field"));
    public static final Target PASSWORD = Target.the("Password field").located(By.id("password"));
    public static final Target SIGN_IN = Target.the("Sign in").located(By.name("commit"));

}
