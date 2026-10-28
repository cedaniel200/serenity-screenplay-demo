package com.cedaniel200.automatizacion.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.Keys;

/**
 * Enfoca un elemento y pulsa Enter: la forma de activar un enlace con el teclado.
 * Se usa para el carrito, cuyo enlace no responde al clic sintético de WebDriver.
 */
public class PressEnter implements Interaction {

    private final Target target;

    private PressEnter(Target target) {
        this.target = target;
    }

    @Override
    @Step("{0} presses Enter on #target")
    public <T extends Actor> void performAs(T actor) {
        target.resolveFor(actor).sendKeys(Keys.ENTER);
    }

    public static PressEnter on(Target target) {
        return new PressEnter(target);
    }
}
