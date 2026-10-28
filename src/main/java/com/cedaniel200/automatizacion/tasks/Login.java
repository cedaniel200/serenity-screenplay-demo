package com.cedaniel200.automatizacion.tasks;

import com.cedaniel200.automatizacion.exceptions.AuthenticationError;
import com.cedaniel200.automatizacion.models.User;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static com.cedaniel200.automatizacion.exceptions.AuthenticationError.MESSAGE_AUTHENTICATION_FAILED;
import static com.cedaniel200.automatizacion.userinterfaces.InventoryPage.INVENTORY_LIST;
import static com.cedaniel200.automatizacion.userinterfaces.LoginPage.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

public class Login implements Task {

    public static final String SITE = "https://www.saucedemo.com/";

    private final User user;

    public Login(User user) {
        this.user = user;
    }

    @Override
    @Step("{0} inicia sesión en Swag Labs")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(SITE),
                WaitUntil.the(USERNAME, isVisible()).forNoMoreThan(Duration.ofSeconds(10)),
                Enter.theValue(user.username()).into(USERNAME),
                Enter.theValue(user.password()).into(PASSWORD),
                Click.on(LOGIN_BUTTON)
        );

        actor.should(seeThat(the(INVENTORY_LIST), isVisible())
                .orComplainWith(AuthenticationError.class, MESSAGE_AUTHENTICATION_FAILED));
    }

    public static Login as(User user) {
        return instrumented(Login.class, user);
    }
}
