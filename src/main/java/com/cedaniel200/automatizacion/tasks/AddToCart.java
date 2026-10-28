package com.cedaniel200.automatizacion.tasks;

import com.cedaniel200.automatizacion.models.Product;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;
import java.util.List;

import static com.cedaniel200.automatizacion.userinterfaces.InventoryPage.ADD_TO_CART;
import static com.cedaniel200.automatizacion.userinterfaces.InventoryPage.CART_BADGE;
import static com.cedaniel200.automatizacion.userinterfaces.InventoryPage.REMOVE_FROM_CART;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AddToCart implements Task {

    private final List<Product> products;

    public AddToCart(List<Product> products) {
        this.products = products;
    }

    @Override
    @Step("{0} adds the products to the cart")
    public <T extends Actor> void performAs(T actor) {
        products.forEach(product -> actor.attemptsTo(
                Click.on(ADD_TO_CART.of(product.slug())),
                // Espera a que la lista se re-renderice antes del siguiente clic: si no, el
                // segundo botón se queda a medio pintar y el clic se pierde.
                WaitUntil.the(REMOVE_FROM_CART.of(product.slug()), isVisible())
                        .forNoMoreThan(Duration.ofSeconds(10))
        ));

        actor.attemptsTo(
                WaitUntil.the(CART_BADGE, isVisible()).forNoMoreThan(Duration.ofSeconds(10))
        );
    }

    public static AddToCart theProducts(List<Product> products) {
        return instrumented(AddToCart.class, products);
    }
}
