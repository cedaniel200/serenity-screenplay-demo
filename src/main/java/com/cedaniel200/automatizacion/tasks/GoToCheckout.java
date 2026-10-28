package com.cedaniel200.automatizacion.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static com.cedaniel200.automatizacion.userinterfaces.CheckoutPage.CHECKOUT;
import static com.cedaniel200.automatizacion.userinterfaces.CheckoutPage.FIRST_NAME;
import static com.cedaniel200.automatizacion.userinterfaces.InventoryPage.CART_LINK;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class GoToCheckout implements Task {

    @Override
    @Step("{0} goes to the checkout")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CART_LINK, isVisible()).forNoMoreThan(Duration.ofSeconds(10)),
                Click.on(CART_LINK),
                WaitUntil.the(CHECKOUT, isVisible()).forNoMoreThan(Duration.ofSeconds(10)),
                Click.on(CHECKOUT),
                WaitUntil.the(FIRST_NAME, isVisible()).forNoMoreThan(Duration.ofSeconds(10))
        );
    }

    public static GoToCheckout now() {
        return instrumented(GoToCheckout.class);
    }
}
