package com.cedaniel200.automatizacion.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Pulsa con el ratón en el centro del elemento, moviendo el puntero primero.
 *
 * <p>Algunos enlaces de React no tienen caja propia y WebDriver los considera no interactuables;
 * mover el ratón y pulsar en la coordenada sí los activa, igual que haría una persona.</p>
 */
public class ClickWithMouse implements Interaction {

    private final Target target;

    private ClickWithMouse(Target target) {
        this.target = target;
    }

    @Override
    @Step("{0} clicks with the mouse on #target")
    public <T extends Actor> void performAs(T actor) {
        WebElement element = target.resolveFor(actor);
        new Actions(BrowseTheWeb.as(actor).getDriver())
                .moveToElement(element)
                .click()
                .perform();
    }

    public static ClickWithMouse on(Target target) {
        return new ClickWithMouse(target);
    }
}
