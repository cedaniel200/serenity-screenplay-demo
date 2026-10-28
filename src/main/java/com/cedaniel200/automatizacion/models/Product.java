package com.cedaniel200.automatizacion.models;

/**
 * Un producto del catálogo. El slug es el identificador que usa el sitio en sus atributos
 * data-test; vive aquí para que los escenarios hablen de nombres y no de detalles del DOM.
 */
public record Product(String name, String slug) {

    public static Product named(String name) {
        return switch (name) {
            case "Sauce Labs Backpack" -> new Product(name, "sauce-labs-backpack");
            case "Sauce Labs Bike Light" -> new Product(name, "sauce-labs-bike-light");
            default -> throw new IllegalArgumentException("Unknown product: " + name);
        };
    }
}
