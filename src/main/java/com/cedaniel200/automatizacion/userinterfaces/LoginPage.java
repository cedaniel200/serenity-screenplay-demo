package com.cedaniel200.automatizacion.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class LoginPage {

    private LoginPage() {
    }

    public static final Target USERNAME = Target.the("campo de usuario")
            .locatedBy("[data-test='username']");
    public static final Target PASSWORD = Target.the("campo de contraseña")
            .locatedBy("[data-test='password']");
    public static final Target LOGIN_BUTTON = Target.the("botón de inicio de sesión")
            .locatedBy("[data-test='login-button']");
}
