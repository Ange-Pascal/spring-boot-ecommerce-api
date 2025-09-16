# Utilise Java 22 officiel
FROM eclipse-temurin:22-jdk

# Crée un dossier pour l'application
WORKDIR /app

# Copie le JAR dans le conteneur
COPY target/*.jar app.jar

# Expose le port sur lequel ton app Spring Boot écoute (par défaut 8080)
EXPOSE 8080

# Commande pour lancer ton application
ENTRYPOINT ["java", "-jar", "app.jar"]
