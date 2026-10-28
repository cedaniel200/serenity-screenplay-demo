# Serenity BDD + Screenplay — Demo

![Java](https://img.shields.io/badge/Java-17-blue)
![Serenity BDD](https://img.shields.io/badge/Serenity%20BDD-5.4.1-green)
![Cucumber](https://img.shields.io/badge/Cucumber-8.0.4-brightgreen)
![Gradle](https://img.shields.io/badge/Gradle-9.8.2-darkgreen)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

Caso completo de automatización web con Serenity BDD y el patrón Screenplay: autenticación,
creación de un recurso y validación del resultado. Es la
[plantilla](https://github.com/cedaniel200/serenity-screenplay-template) con el ejemplo implementado.

## Qué incluye

- **Screenplay completo**: `tasks`, `interactions`, `questions`, `userinterfaces`, con `models`,
  `builders` y `enumerables` encima de la estructura base.
- **Un flujo de negocio de punta a punta**: iniciar sesión, crear un repositorio y comprobar nombre
  y URL con aserciones reales de Hamcrest.
- **Validaciones en el modelo**: usuario y repositorio se construyen con builders que rechazan
  datos inválidos antes de llegar al navegador.
- **Cucumber 8 sobre JUnit Platform**, con el informe de Serenity generado desde el plugin.

## Requisitos

- **JDK 17**. El build fija un toolchain de Java 17.
- **Un navegador instalado** (Chrome por defecto). El driver lo resuelve Selenium Manager.
- **Credenciales de GitHub** con permiso para crear repositorios: `github-user` y `password`.

> [!WARNING]
> El escenario **crea un repositorio real** llamado `TEST_BDD` en la cuenta que se indique. No lo
> apuntes a una cuenta compartida ni a una organización de producción. Y si la cuenta tiene 2FA
> activo, el inicio de sesión por interfaz no va a funcionar.

## Cómo se ejecuta

```sh
./gradlew test -Dgithub-user=<usuario> -Dpassword=<contraseña>
./gradlew aggregate
```

El informe queda en `target/site/serenity/index.html`.

Sin credenciales, la suite arranca y falla en la validación del modelo con
`UserModelCreationException: Invalid username (null) or password (null)`. Es el comportamiento
esperado, no un fallo de la configuración.

## Docker

```sh
export GITHUB_USER=<usuario>
export GITHUB_PASSWORD=<contraseña>
docker compose up --abort-on-container-exit
```

El navegador no corre en tu máquina ni en el contenedor de los tests: vive en el servicio
`chrome` (imagen oficial de Selenium) y los tests le envían comandos por HTTP. En tu pantalla no
se abre ninguna ventana.

El informe queda en `./target/site/serenity/`. Las credenciales se leen de `GITHUB_USER` y
`GITHUB_PASSWORD`.

## Estructura

```text
src/main/java/co/com/yourcompany/certification/nameproject
├── tasks/              # Start, CreateRepository
├── interactions/       # EnterAndHide, SelectDropDownButton
├── questions/          # TheCurrent, TheRepository
├── userinterfaces/     # páginas de GitHub
├── models/             # User, Repository
│   ├── builders/       # UserBuilder, RepositoryBuilder
│   └── enumerables/    # GitIgnore, License
├── exceptions/
└── util/

src/test
├── java/.../runners/GitHubTestSuite.java
├── java/.../stepdefinitions/
└── resources/features/create_repository.feature
```

## Relación con la plantilla

La estructura es la de [serenity-screenplay-template](https://github.com/cedaniel200/serenity-screenplay-template),
más tres capas que el ejemplo necesita: `models/builders`, `models/enumerables` y `util`. Si partes
de la plantilla, estos paquetes no vienen: se añaden cuando el caso los pide.

## Ten en cuenta

- **Depende de GitHub.** Si cambia su flujo de creación de repositorios, el ejemplo deja de servir.
- **No se puede ejecutar sin cuenta.** Es una demo de referencia, no una suite que corra en CI sin
  secretos.
- **El `Jenkinsfile` necesita tu Jenkins.** La pipeline elige `sh` o `bat` según el agente, así que
  corre en Unix y en Windows, pero espera una credencial llamada `password-github` y los plugins de
  siempre. Si no tienes ese servidor, ignóralo: el flujo real es `./gradlew` o Docker.

## Licencia

[MIT](LICENSE) © Cesar Daniel
