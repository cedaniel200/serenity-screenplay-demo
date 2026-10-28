package com.cedaniel200.automatizacion.tasks;

import com.cedaniel200.automatizacion.models.Buyer;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static com.cedaniel200.automatizacion.userinterfaces.CheckoutPage.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class FillShippingInfo implements Task {

    private final Buyer buyer;

    public FillShippingInfo(Buyer buyer) {
        this.buyer = buyer;
    }

    @Override
    @Step("{0} fills the shipping information")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(buyer.firstName()).into(FIRST_NAME),
                Enter.theValue(buyer.lastName()).into(LAST_NAME),
                Enter.theValue(buyer.postalCode()).into(POSTAL_CODE),
                WaitUntil.the(CONTINUE, isVisible()).forNoMoreThan(Duration.ofSeconds(10)),
                Click.on(CONTINUE),
                WaitUntil.the(FINISH, isVisible()).forNoMoreThan(Duration.ofSeconds(10))
        );
    }

    public static FillShippingInfo with(Buyer buyer) {
        return instrumented(FillShippingInfo.class, buyer);
    }
}
