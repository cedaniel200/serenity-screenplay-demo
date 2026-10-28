@web
Feature: Compra en Swag Labs

  Scenario: Comprar dos productos
    Given Ana inicia sesión con el usuario "standard_user" y la contraseña "secret_sauce"
    When agrega al carrito los productos "sauce-labs-backpack" y "sauce-labs-bike-light"
    Then el carrito muestra 2 productos
    When completa la compra como "Ana" "Perez" con código postal "110111"
    Then ve el mensaje de confirmación "Thank you for your order!"
