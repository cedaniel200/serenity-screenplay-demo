package co.com.yourcompany.certification.nameproject.tasks;

import co.com.yourcompany.certification.nameproject.exceptions.StartError;
import co.com.yourcompany.certification.nameproject.exceptions.UserModelCreationException;
import co.com.yourcompany.certification.nameproject.interactions.EnterAndHide;
import co.com.yourcompany.certification.nameproject.models.User;
import co.com.yourcompany.certification.nameproject.userinterface.GitHubLoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.annotations.Step;

import static co.com.yourcompany.certification.nameproject.exceptions.StartError.MESSAGE_FAILED_AUTHENTICATION;
import static co.com.yourcompany.certification.nameproject.exceptions.StartError.MESSAGE_LOGIN_PAGE_NOT_LOADED;
import static co.com.yourcompany.certification.nameproject.models.builders.UserBuilder.theUser;
import static co.com.yourcompany.certification.nameproject.userinterface.UserGitHubHomePage.DASHBOARD;
import static co.com.yourcompany.certification.nameproject.userinterface.GitHubLoginPage.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

public class Start implements Task {

    private static final String USERNAME = "DEFAULT_USERNAME";
    private static final String USER_PASSWORD = "DEFAULT_PASSWORD";
    private final User user;
    private GitHubLoginPage gitHubLoginPage;

    public Start(User user) {
        this.user = user;
    }

    @Override
    @Step("{0} performs an authentication")
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(Open.browserOn(gitHubLoginPage));

        theActor.should(seeThat(the(USERNAME_OR_EMAIL_ADDRESS), isVisible())
                .orComplainWith(StartError.class,
                        MESSAGE_LOGIN_PAGE_NOT_LOADED));

        theActor.attemptsTo(
                Enter.theValue(user.getUsername()).into(USERNAME_OR_EMAIL_ADDRESS),
                EnterAndHide.theValue(user.getPassword()).as("a password").into(PASSWORD),
                Click.on(SIGN_IN));

        theActor.should(seeThat(the(DASHBOARD), isVisible())
                .orComplainWith(StartError.class,
                        MESSAGE_FAILED_AUTHENTICATION));
    }

    public static Start authenticating(User user) {
        return instrumented(Start.class, user);
    }

    public static Start withAnAuthenticatedDefaultUser() throws UserModelCreationException {
        return instrumented(Start.class, theUser(USERNAME).withPassword(USER_PASSWORD));
    }

}
