# App

This is a simple Spring Boot application example.
Its most key features:
- two REST endpoints 
  - GET actions 
  - POST action
- DB usage
- dockerized deployment
- even some tests

## Non-dockerized run

1. Set up DB & connection details in `application.properties` 
   - in case of DB from docker compose - `spring.datasource.url=jdbc:mysql://localhost:3307/db?autoReconnect=true` 
2. Run Application


## Dockerized run

1. Assemble project
   1. In terminal perform `./gradlew assemble` (you may need to specify `org.gradle.java.home` in `gradle.properties`; see [this](https://stackoverflow.com/questions/18487406/how-do-i-tell-gradle-to-use-specific-jdk-version)) 
   2. or use Gradle tool window from IntelliJ (`app/Tasks/build/assemble`)
2. Run the following:

```shell
docker build -t app
docker-compose up -d
```

Note that the application container may fail a few times before a successful start.
This is because it tries to connect the DB before the DB is able to accept a connection.

## Usage 

The app in both cases (non-dockerized and dockerized runs) can be accessed at [https://localhost:8080](https://localhost:8080).
There are three REST endpoints: 
0. `GET /api/health`
1. `POST /api/action` allowing the addition of action objects
2. `GET /api/actions` that gives all the actions from DB

See **sample_requests** directory for examples.
