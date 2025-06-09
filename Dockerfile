# Use a imagem oficial do OpenJDK 21 para rodar sua aplicação
FROM eclipse-temurin:21-jdk-jammy

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o JAR gerado para dentro do container
COPY target/chatbot-vendas-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta padrão do Spring Boot (8080)
EXPOSE 8080

# Variáveis de ambiente (exemplo, você pode adicionar as que precisar)
ENV JAVA_OPTS=""

# Comando para rodar a aplicação
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
