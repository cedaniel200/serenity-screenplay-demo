package com.cedaniel200.automatizacion.tasks;

import com.cedaniel200.automatizacion.exceptions.CheckoutError;
import com.cedaniel200.automatizacion.models.Buyer;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static com.cedaniel200.automatizacion.exceptions.CheckoutError.MESSAGE_CHECKOUT_FAILED;
import static com.cedaniel200.automatizacion.userinterfaces.CheckoutPage.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

public class Checkout implements Task {

    private final Buyer buyer;

    public Checkout(Buyer buyer) {
        this.buyer = buyer;
    }

    @Override
    @Step("{0} completa la compra")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(Login.SITE + "cart.html"),
                WaitUntil.the(CHECKOUT, isVisible()).forNoMoreThan(Duration.ofSeconds(10)),
                Click.on(CHECKOUT)
        );

        actor.attemptsTo(
                WaitUntil.the(FIRST_NAME, isVisible()).forNoMoreThan(Duration.ofSeconds(10)),
                Enter.theValue(buyer.firstName()).into(FIRST_NAME),
                Enter.theValue(buyer.lastName()).into(LAST_NAME),
                Enter.theValue(buyer.postalCode()).into(POSTAL_CODE),
                Click.on(CONTINUE),
                WaitUntil.the(FINISH, isVisible()).forNoMoreThan(Duration.ofSeconds(10)),
                Click.on(FINISH)
        );

        actor.should(seeThat(the(COMPLETE_HEADER), isVisible())
                .orComplainWith(CheckoutError.class, MESSAGE_CHECKOUT_FAILED));
    }

    public static Checkout as(Buyer buyer) {
        return instrumented(Checkout.class, buyer);
    }
}
