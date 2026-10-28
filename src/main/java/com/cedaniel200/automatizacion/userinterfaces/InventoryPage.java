package com.cedaniel200.automatizacion.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class InventoryPage {

    private InventoryPage() {
    }

    public static final Target INVENTORY_LIST = Target.the("lista de productos")
            .locatedBy("[data-test='inventory-list']");
    public static final Target ADD_TO_CART = Target.the("botón de añadir al carrito de {0}")
            .locatedBy("[data-test='add-to-cart-{0}']");
    public static final Target CART_BADGE = Target.the("contador del carrito")
            .locatedBy("[data-test='shopping-cart-badge']");
    public static final Target CART_LINK = Target.the("enlace al carrito")
            .locatedBy("[data-test='shopping-cart-link']");
}
