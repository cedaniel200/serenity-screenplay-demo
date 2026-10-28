package com.cedaniel200.automatizacion.stepdefinitions;

import com.cedaniel200.automatizacion.models.Buyer;
import com.cedaniel200.automatizacion.models.User;
import com.cedaniel200.automatizacion.questions.TheCartBadge;
import com.cedaniel200.automatizacion.questions.TheOrderConfirmation;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.List;

import static com.cedaniel200.automatizacion.tasks.AddToCart.theProducts;
import static com.cedaniel200.automatizacion.tasks.Checkout.as;
import static com.cedaniel200.automatizacion.tasks.Login.with;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class PurchaseStepDefinitions {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("{word} inicia sesión con el usuario {string} y la contraseña {string}")
    public void iniciaSesion(String actor, String username, String password) {
        theActorCalled(actor).attemptsTo(with(new User(username, password)));
    }

    @When("agrega al carrito los productos {string} y {string}")
    public void agregaAlCarrito(String first, String second) {
        theActorInTheSpotlight().attemptsTo(theProducts(List.of(first, second)));
    }

    @Then("el carrito muestra {int} productos")
    public void elCarritoMuestra(int expectedItems) {
        theActorInTheSpotlight().should(seeThat(TheCartBadge.shows(expectedItems)));
    }

    @When("completa la compra como {string} {string} con código postal {string}")
    public void completaLaCompra(String firstName, String lastName, String postalCode) {
        theActorInTheSpotlight().attemptsTo(as(new Buyer(firstName, lastName, postalCode)));
    }

    @Then("ve el mensaje de confirmación {string}")
    public void veElMensaje(String message) {
        theActorInTheSpotlight().should(seeThat(TheOrderConfirmation.says(message)));
    }
}
