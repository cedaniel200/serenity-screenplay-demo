package com.cedaniel200.automatizacion.exceptions;

public class CheckoutError extends AssertionError {

    public static final String MESSAGE_CHECKOUT_FAILED = "No se pudo completar la compra";

    public CheckoutError(String message, Throwable cause) {
        super(message, cause);
    }
}
