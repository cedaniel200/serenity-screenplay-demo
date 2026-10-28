package com.cedaniel200.automatizacion.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;

import static com.cedaniel200.automatizacion.userinterfaces.CheckoutPage.COMPLETE_HEADER;

@Subject("La confirmación de la compra muestra el mensaje esperado")
public class TheOrderConfirmation implements Question<Boolean> {

    private final String expectedMessage;

    public TheOrderConfirmation(String expectedMessage) {
        this.expectedMessage = expectedMessage;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String message = Text.of(COMPLETE_HEADER).answeredBy(actor);
        return message != null && message.trim().equalsIgnoreCase(expectedMessage);
    }

    public static TheOrderConfirmation says(String expectedMessage) {
        return new TheOrderConfirmation(expectedMessage);
    }
}
