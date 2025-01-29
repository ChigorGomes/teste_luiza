# Usando a imagem oficial do OpenJDK 21
FROM openjdk:21

WORKDIR /app


# Copia o arquivo JAR para dentro do container
COPY ../target/oracleebs-1.0.0.jar /app/oracleebs-1.0.0.jar


# Expõe a porta da aplicação

RUN chmod +x /app/oracleebs-1.0.0.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "oracleebs-1.0.0.jar",".jar"]

EXPOSE 9080
