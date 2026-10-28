package com.cedaniel200.automatizacion.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import java.util.List;

import static com.cedaniel200.automatizacion.userinterfaces.InventoryPage.ADD_TO_CART;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AddToCart implements Task {

    private final List<String> productSlugs;

    public AddToCart(List<String> productSlugs) {
        this.productSlugs = productSlugs;
    }

    @Override
    @Step("{0} añade los productos al carrito")
    public <T extends Actor> void performAs(T actor) {
        productSlugs.forEach(slug ->
                actor.attemptsTo(Click.on(ADD_TO_CART.of(slug)))
        );
    }

    public static AddToCart theProducts(List<String> productSlugs) {
        return instrumented(AddToCart.class, productSlugs);
    }
}
