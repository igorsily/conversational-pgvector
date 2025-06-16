# Etapa 1: Build da aplicação com Maven e JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Define o diretório de trabalho no container de build
WORKDIR /build

# Copia os arquivos do projeto para o container
COPY . .

# Executa o build do projeto, ignorando os testes
RUN mvn clean package -DskipTests

# Etapa 2: Runtime com JRE 21 (imagem mais leve)
FROM eclipse-temurin:21-jre-alpine

# Define o diretório de trabalho no container final
WORKDIR /app

# Copia o JAR gerado da etapa de build para o container final
COPY --from=builder /build/target/*.jar app.jar

# Define variáveis de ambiente com valores padrão (podem ser sobrescritos em tempo de execução)
ENV DB_HOST=localhost \
    DB_PORT=5432 \
    DB_NAME=seu_banco \
    DB_USERNAME=seu_usuario \
    DB_PASSWORD=senha \
    OPENAI_API_KEY=""\
    AZURE_CONNECTION_STRING="" \
    AZURE_CONTAINER_NAME="" \
    LOCAL_BASE_PATH="" \
    S3_ACCESS_KEY="" \
    S3_SECRET_KEY="" \
    S3_BUCKET_NAME=""

# Exponha a porta da aplicação (ajustar se necessário)
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
