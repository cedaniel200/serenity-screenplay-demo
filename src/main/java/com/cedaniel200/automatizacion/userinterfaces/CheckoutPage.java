package com.cedaniel200.automatizacion.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class CheckoutPage {

    private CheckoutPage() {
    }

    public static final Target CHECKOUT = Target.the("botón de checkout")
            .locatedBy("[data-test='checkout']");
    public static final Target FIRST_NAME = Target.the("campo de nombre")
            .locatedBy("[data-test='firstName']");
    public static final Target LAST_NAME = Target.the("campo de apellido")
            .locatedBy("[data-test='lastName']");
    public static final Target POSTAL_CODE = Target.the("campo de código postal")
            .locatedBy("[data-test='postalCode']");
    public static final Target CONTINUE = Target.the("botón de continuar")
            .locatedBy("[data-test='continue']");
    public static final Target FINISH = Target.the("botón de finalizar")
            .locatedBy("[data-test='finish']");
    public static final Target COMPLETE_HEADER = Target.the("mensaje de confirmación")
            .locatedBy("[data-test='complete-header']");
}
