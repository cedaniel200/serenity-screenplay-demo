@web
Feature: Place an order at Swag Labs

  Scenario: Place an order with two products
    Given Ana is logged in with "standard_user" and "secret_sauce"
    When she places an order for "Sauce Labs Backpack" and "Sauce Labs Bike Light" with shipping details "Ana", "Perez" and "110111"
    Then she sees the order confirmation "Thank you for your order!"
