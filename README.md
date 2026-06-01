# Electrical Components Management System

This is a small Spring Boot project intended as a learning playground for GitHub Actions CI, Maven, JUnit 5, Mockito and JaCoCo.

## Project Overview

The application manages electrical components (Resistor, Capacitor, Inductor, Diode, Transistor). It uses a simple in-memory repository (ConcurrentHashMap) and provides a REST API to create, read, update and delete components.

Tech stack:
- Java 17
- Spring Boot 3.x
- Maven
- Lombok
- JUnit 5 + Mockito
- JaCoCo for code coverage

## Run locally

Start the application:

```bash
mvn spring-boot:run
```

The API base path is: `http://localhost:8080/api/v1/components`

## API Documentation (Swagger UI)

Once the application is running, open the Swagger UI to explore and call endpoints in your browser:

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

The Swagger UI provides an interactive interface to send requests to the running server and inspect responses.

## Running tests

Run unit tests:

```bash
mvn test
```

Run full build and generate coverage report (JaCoCo):

```bash
mvn clean verify
```

Reports will be generated under `target/site/jacoco/index.html`.

## GitHub Actions Workflow

The workflow file is located at `.github/workflows/ci.yml` and triggers on `push` and `pull_request`.

Pipeline steps:
1. Checkout code
2. Setup Java 17
3. Cache Maven dependencies
4. Run `mvn clean verify` (this executes tests and generates JaCoCo report)
5. Upload artifact (the generated JAR)

If tests fail the pipeline step `mvn clean verify` will fail and the workflow will stop. Inspect logs in the Actions tab of your repository to debug failures.

## CI Learning Exercises

Try these exercises to learn CI behavior:

1. Intentionally break a test (e.g. change expected value in a test).
2. Commit and push the change.
3. Observe CI failure in GitHub Actions.
4. Fix the test or code locally.
5. Push again and observe CI success.

## API Examples

Create component:

```bash
curl -X POST http://localhost:8080/api/v1/components -H "Content-Type: application/json" -d '{"componentName":"R1","componentType":"Resistor","manufacturer":"Acme","price":0.1,"quantity":100}'
```

Get component by id:

```bash
curl http://localhost:8080/api/v1/components/1
```

Update component:

```bash
curl -X PUT http://localhost:8080/api/v1/components/1 -H "Content-Type: application/json" -d '{"componentName":"R1-upd","componentType":"Resistor","manufacturer":"Acme","price":0.2,"quantity":50}'
```

Delete component:

```bash
curl -X DELETE http://localhost:8080/api/v1/components/1
```

## Notes

- No database is used. All data is stored in-memory and lost on restart.
- Use this project to experiment with CI and incremental improvements.

