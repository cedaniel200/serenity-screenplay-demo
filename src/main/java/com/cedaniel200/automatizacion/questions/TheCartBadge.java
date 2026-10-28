package com.cedaniel200.automatizacion.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static com.cedaniel200.automatizacion.userinterfaces.InventoryPage.CART_BADGE;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

@Subject("El contador del carrito muestra los productos añadidos")
public class TheCartBadge implements Question<Boolean> {

    private final int expectedItems;

    public TheCartBadge(int expectedItems) {
        this.expectedItems = expectedItems;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        actor.attemptsTo(
                WaitUntil.the(CART_BADGE, isVisible()).forNoMoreThan(Duration.ofSeconds(10))
        );
        String badge = Text.of(CART_BADGE).answeredBy(actor);
        try {
            return Integer.parseInt(badge.trim()) == expectedItems;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static TheCartBadge shows(int expectedItems) {
        return new TheCartBadge(expectedItems);
    }
}
