package com.cedaniel200.automatizacion.tasks;

import com.cedaniel200.automatizacion.exceptions.CheckoutError;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static com.cedaniel200.automatizacion.exceptions.CheckoutError.MESSAGE_CHECKOUT_FAILED;
import static com.cedaniel200.automatizacion.userinterfaces.CheckoutPage.COMPLETE_HEADER;
import static com.cedaniel200.automatizacion.userinterfaces.CheckoutPage.FINISH;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

public class ConfirmOrder implements Task {

    @Override
    @Step("{0} confirms the order")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(FINISH, isVisible()).forNoMoreThan(Duration.ofSeconds(10)),
                Click.on(FINISH)
        );

        actor.should(seeThat(the(COMPLETE_HEADER), isVisible())
                .orComplainWith(CheckoutError.class, MESSAGE_CHECKOUT_FAILED));
    }

    public static ConfirmOrder now() {
        return instrumented(ConfirmOrder.class);
    }
}
