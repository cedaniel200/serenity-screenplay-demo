package com.cedaniel200.automatizacion.tasks;

import com.cedaniel200.automatizacion.models.Buyer;
import com.cedaniel200.automatizacion.models.Product;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * La acción de negocio que se prueba: realizar una orden completa.
 *
 * <p>Compone las tareas necesarias para que el escenario hable de negocio y no de pantallas.
 * Todo el recorrido ocurre por la interfaz.</p>
 */
public class PlaceAnOrder implements Task {

    private final List<Product> products;
    private final Buyer buyer;

    public PlaceAnOrder(List<Product> products, Buyer buyer) {
        this.products = products;
        this.buyer = buyer;
    }

    @Override
    @Step("{0} places an order")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                AddToCart.theProducts(products),
                GoToCheckout.now(),
                FillShippingInfo.with(buyer),
                ConfirmOrder.now()
        );
    }

    public static PlaceAnOrder with(List<Product> products, Buyer buyer) {
        return instrumented(PlaceAnOrder.class, products, buyer);
    }
}
