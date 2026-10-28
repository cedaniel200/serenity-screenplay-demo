package com.cedaniel200.automatizacion.exceptions;

public class AuthenticationError extends AssertionError {

    public static final String MESSAGE_AUTHENTICATION_FAILED = "No se pudo iniciar sesión en el sitio";

    public AuthenticationError(String message, Throwable cause) {
        super(message, cause);
    }
}
