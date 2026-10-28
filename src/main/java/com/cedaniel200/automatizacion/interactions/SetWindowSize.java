package com.cedaniel200.automatizacion.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.Dimension;

/**
 * Fija el tamaño de la ventana.
 *
 * <p>El modo headless arranca con un viewport de 800x600 y esta aplicación cambia el diseño
 * según el ancho: con la ventana pequeña hay botones que quedan fuera de la zona visible y el
 * clic no llega a disparar la navegación.</p>
 */
public class SetWindowSize implements Interaction {

    private final int width;
    private final int height;

    private SetWindowSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    @Step("{0} ajusta la ventana a #width x #height")
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).getDriver().manage().window().setSize(new Dimension(width, height));
    }

    public static SetWindowSize to(int width, int height) {
        return new SetWindowSize(width, height);
    }
}
