# Imagen para ejecutar la suite dentro de un contenedor.
#
# No incluye navegador: el navegador corre en el servicio `chrome` (imagen oficial de
# Selenium) y esta imagen solo ejecuta los tests apuntando a ese grid. Ver docker-compose.yml.
FROM amazoncorretto:17

WORKDIR /app

# El wrapper de Gradle 9 invoca xargs; la imagen base de Amazon Linux no lo trae.
RUN dnf install -y findutils && dnf clean all

# El wrapper se resuelve antes de copiar el código para aprovechar la caché de capas.
COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle
RUN chmod +x gradlew && ./gradlew --version --no-daemon

COPY src ./src

CMD ["./gradlew", "test", "aggregate", "--no-daemon"]
