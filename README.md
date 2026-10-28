# Serenity BDD + Screenplay — Demo Swag Labs

![Java](https://img.shields.io/badge/Java-17-blue)
![Serenity BDD](https://img.shields.io/badge/Serenity%20BDD-5.4.1-green)
![Cucumber](https://img.shields.io/badge/Cucumber-8.0.4-brightgreen)
![Gradle](https://img.shields.io/badge/Gradle-9.8.2-darkgreen)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

Flujo de compra completo contra [saucedemo.com](https://www.saucedemo.com): iniciar sesión, añadir
productos y finalizar la compra. Es la
[plantilla](https://github.com/cedaniel200/serenity-screenplay-template) con un caso real implementado.

## Qué incluye

| Paso | Qué ejercita |
|---|---|
| Inicio de sesión | Formulario con credenciales y espera a la carga del inventario |
| Añadir al carrito | Dos productos distintos |
| Checkout | Datos del comprador y confirmación del pedido |

Sobre esa base:

- **Screenplay completo**: `tasks`, `questions`, `userinterfaces` y `models`.
- **Aserción real**: el mensaje de confirmación del pedido.
- **Credenciales públicas**: el sitio documenta sus usuarios de prueba, así que cualquiera puede
  ejecutar la suite.
- **Cucumber 8 sobre JUnit Platform**, con el informe de Serenity generado desde el plugin.

## Requisitos

- **JDK 17**. El build fija un toolchain de Java 17.
- **Un navegador instalado** (Chrome por defecto). El driver lo resuelve Selenium Manager.
- **Conexión a internet**: el escenario se ejecuta contra un sitio público.

## Cómo se ejecuta

```sh
./gradlew test
./gradlew aggregate
```

El informe queda en `target/site/serenity/index.html`.

Para ejecutarlo sin abrir ventana:

```sh
./gradlew test -Dheadless.mode=true
```

Las credenciales (`standard_user` / `secret_sauce`) son las públicas del sitio y viajan como datos
del escenario, no están en el código.

## Estructura

```text
src/main/java/com/cedaniel200/automatizacion
├── tasks/            # Login, AddToCart, GoToCheckout, FillShippingInfo, ConfirmOrder, PlaceAnOrder
├── questions/        # TheOrderConfirmation
├── userinterfaces/   # LoginPage, InventoryPage, CheckoutPage
├── models/           # User, Buyer, Product
└── exceptions/       # AuthenticationError, CheckoutError

src/test
├── java/.../runners/PurchaseTestSuite.java
├── java/.../stepdefinitions/PurchaseStepDefinitions.java
└── resources/features/purchase.feature
```

## Relación con la plantilla

Usa la estructura de [serenity-screenplay-template](https://github.com/cedaniel200/serenity-screenplay-template)
con el paquete adaptado al proyecto.

## Docker

```sh
docker compose up --abort-on-container-exit
```

El navegador corre en el servicio `chrome` (imagen oficial de Selenium); los tests le envían
comandos por HTTP y no abren ninguna ventana en tu máquina. El informe queda en
`./target/site/serenity/`.

## Ten en cuenta

- **Depende de un sitio de terceros.** Si Swag Labs cambia su DOM, los localizadores se rompen. Usan
  atributos `data-test`, que es lo más estable que ofrece la página.
- **El aviso de contraseña de Chrome hay que desactivarlo.** Tras enviar el formulario aparece una
  burbuja del navegador («contraseña filtrada» / «guardar contraseña»). No es UI de la página: tapa
  el contenido y se come los clics siguientes, así que los fallos aparecen en pasos que no tienen
  relación. Se desactiva en `serenity.conf` con dos cosas: `--disable-features=PasswordLeakDetection`
  y las preferencias `credentials_enable_service`, `profile.password_manager_enabled` y
  `profile.password_manager_leak_detection`. El flag solo no basta.
- **`serenity.conf` va en `src/test/resources`**, no en la raíz del proyecto: es donde Serenity lo
  busca según la documentación oficial. En la raíz se ignora silenciosamente.
- **Versión anterior**: hasta `v1.0.0` el escenario automatizaba GitHub y requería credenciales
  propias. Se cambió porque un demo que no se puede ejecutar sin una cuenta no demuestra nada.
- **`gradle.startParameter.continueOnFailure = true`** está heredado: Gradle sigue tras una tarea
  fallida.

## Licencia

[MIT](LICENSE) © Cesar Daniel
