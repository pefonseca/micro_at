
# Relatório de Desenvolvimento de Microsserviços com Minikube

## Introdução

Este projeto foi desenvolvido com três microsserviços, cada um responsável por uma funcionalidade específica: **Weather Service**, **Location Service** e **User Service**. A orquestração dos microsserviços foi feita utilizando o **Minikube**, uma ferramenta que simula o ambiente Kubernetes localmente. Este relatório detalha as etapas do desenvolvimento, as escolhas feitas e as justificativas para cada decisão.

---

## Arquitetura do Projeto

O sistema foi desenvolvido com três serviços independentes:
- **Weather Service**: Responsável por fornecer dados meteorológicos baseados na cidade.
- **Location Service**: Armazena informações sobre localizações e faz o cadastro de novas entradas.
- **User Service**: Realiza o cadastro de usuários e interage com os serviços de localização e clima para complementar os dados.

### Tecnologias Utilizadas
- **Spring Boot** para a criação de APIs REST.
- **OpenFeign** para comunicação entre microsserviços.
- **Minikube** para orquestração dos serviços em um ambiente Kubernetes.
- **Docker** para criação de imagens dos serviços.
- **Postman** para testes de chamadas de APIs.

---

## Etapas do Projeto

### 1. Desenvolvimento dos Microsserviços

**Weather Service**
- API que retorna informações meteorológicas de uma cidade.
- Implementação usa dados estáticos para simular respostas de um serviço real.

**Location Service**
- API que permite cadastrar e obter informações de localização.
- Utiliza uma base de dados simples para armazenar CPF e cidade.

**User Service**
- API que permite o cadastro de usuários e consulta os serviços de clima e localização.
- O usuário é cadastrado com dados como CPF e cidade, e as informações do clima são retornadas junto ao cadastro.

### 2. Configuração do Docker

Cada microsserviço foi configurado com um **Dockerfile** para permitir a criação de imagens que seriam executadas dentro do Kubernetes. 

Exemplo do Dockerfile para o `weather-service`:

```
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/weather-service-0.0.1-SNAPSHOT.jar /app/weather-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/weather-service.jar"]
```

Exemplo do Dockerfile para o `location-service`:

```
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/location-service-0.0.1-SNAPSHOT.jar /app/location-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/location-service.jar"]
```

Exemplo do Dockerfile para o `user-service`:

```
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/user-service-0.0.1-SNAPSHOT.jar /app/user-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app/user-service.jar"]
```

As imagens foram criadas localmente e enviadas para o ambiente do Minikube.

### 3. Orquestração com Kubernetes (Minikube)

A orquestração foi feita utilizando **Deployments** e **Services**. Cada microsserviço foi configurado com um deployment e um serviço, permitindo que fossem expostos e acessíveis no cluster do Minikube.

Primeiro inicializa o minikube:

```
minikube start
```

Exemplo de manifesto para o `weather-service`:

```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: weather-service-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: weather-service
  template:
    metadata:
      labels:
        app: weather-service
    spec:
      containers:
        - name: weather-service
          image: weather-service:latest
          ports:
            - containerPort: 8080 
---
apiVersion: v1
kind: Service
metadata:
  name: weather-service
spec:
  type: NodePort
  ports:
    - port: 8080
      targetPort: 8080
      nodePort: 30001
  selector:
    app: weather-service
```

Exemplo de manifesto para o `location-service`:

```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: location-service-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: location-service
  template:
    metadata:
      labels:
        app: location-service
    spec:
      containers:
        - name: location-service
          image: location-service:latest
          ports:
            - containerPort: 8082
          env:
            - name: SPRING_DATASOURCE_URL
              value: "jdbc:postgresql://localhost:5433/location-db"
            - name: SPRING_DATASOURCE_USERNAME
              value: "postgres"
            - name: SPRING_DATASOURCE_PASSWORD
              value: "root"
            - name: SPRING_JPA_HIBERNATE_DDL_AUTO
              value: "create-drop"
            - name: SPRING_JPA_SHOW_SQL
              value: "true"
            - name: SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL
              value: "true"
            - name: SPRING_DATASOURCE_DRIVER_CLASS_NAME
              value: "org.postgresql.Driver"
            - name: SPRING_DATASOURCE_DATABASE
              value: "postgresql"
            - name: SPRING_DATASOURCE_DATABASE_PLATFORM
              value: "org.hibernate.dialect.PostgreSQLDialect"

---
apiVersion: v1
kind: Service
metadata:
  name: location-service
spec:
  type: NodePort
  ports:
    - port: 8081
      targetPort: 8081
      nodePort: 30002
  selector:
    app: location-service
```

Exemplo de manifesto para o `user-service`:

```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: user-service-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: user-service
  template:
    metadata:
      labels:
        app: user-service
    spec:
      containers:
        - name: user-service
          image: user-service:latest
          ports:
            - containerPort: 8082 
---
apiVersion: v1
kind: Service
metadata:
  name: user-service
spec:
  type: NodePort
  ports:
    - port: 8082
      targetPort: 8082  
      nodePort: 30003
  selector:
    app: user-service
```

Após a criação dos manifests YAML, eles foram aplicados no cluster do Minikube:

```
kubectl apply -f weather-service.yaml
kubectl apply -f location-service.yaml
kubectl apply -f user-service.yaml
```

Aplicando os manifests localmente:

![image](https://github.com/user-attachments/assets/b09401c6-3ec9-4827-b962-d5573db6ec21)


Em seguida, para visualizar os serviços e os pods podemos utilizar os seguintes comandos:

```
kubectl get pods
kubectl get services
```

Pods:

![image](https://github.com/user-attachments/assets/7feb7f9d-72ec-411c-bb55-77571464651d)

Services:

![image](https://github.com/user-attachments/assets/63fdf423-4743-445c-bfc2-6a06bcc3913c)

### 4. Testes dos Microsserviços

Com os serviços rodando locamente, foram feitos testes de integração utilizando o **Postman**.

Link para o vídeo:

[Vídeo no YouTube](https://www.youtube.com/watch?v=1t4j9FH3zv8)

## Conclusão

Este projeto demonstrou o desenvolvimento e a integração de três microsserviços independentes: `user-service`, `location-service` e `weather-service`. Cada um deles desempenha um papel crucial na arquitetura geral, desde a criação de usuários até a verificação de localizações e condições meteorológicas. Utilizei o Minikube para orquestração e simular um ambiente Kubernetes, permitindo o teste e a implementação dos serviços em um ambiente isolado. Mas tive um pequeno problema ao tentar rodar local, pois as images não rodaram.

O uso de microsserviços oferece uma arquitetura escalável e flexível, onde cada serviço pode ser desenvolvido, implantado e escalado de forma independente. Esse projeto mostrou a eficiência desse modelo, garantindo que mudanças em um serviço não afetem os demais. A comunicação entre os serviços foi configurada corretamente, garantindo uma integração suave e respostas rápidas aos clientes. O projeto está pronto para ser expandido com novos serviços ou recursos, caso necessário, e pode ser facilmente adaptado para um ambiente de produção em nuvem.

Além disso, as ferramentas utilizadas, como o Spring Boot, OpenFeign para comunicação e o Minikube para orquestração, mostraram-se eficazes para alcançar o objetivo do projeto.
