package com.cedaniel200.automatizacion.stepdefinitions;

import com.cedaniel200.automatizacion.models.Buyer;
import com.cedaniel200.automatizacion.models.Product;
import com.cedaniel200.automatizacion.models.User;
import com.cedaniel200.automatizacion.questions.TheOrderConfirmation;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.List;

import com.cedaniel200.automatizacion.tasks.Login;
import com.cedaniel200.automatizacion.tasks.PlaceAnOrder;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class PurchaseStepDefinitions {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("{word} is logged in with {string} and {string}")
    public void isLoggedIn(String actor, String username, String password) {
        theActorCalled(actor).attemptsTo(
                Login.as(new User(username, password))
        );
    }

    @When("she places an order for {string} and {string} with shipping details {string}, {string} and {string}")
    public void shePlacesAnOrder(String firstProduct, String secondProduct,
                                 String firstName, String lastName, String postalCode) {
        List<Product> products = List.of(Product.named(firstProduct), Product.named(secondProduct));
        theActorInTheSpotlight().attemptsTo(
                PlaceAnOrder.with(
                        products, new Buyer(firstName, lastName, postalCode))
        );
    }

    @Then("she sees the order confirmation {string}")
    public void sheSeesTheOrderConfirmation(String message) {
        theActorInTheSpotlight().should(seeThat(TheOrderConfirmation.says(message)));
    }
}
