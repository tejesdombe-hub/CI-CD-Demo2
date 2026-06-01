# Skills & Learning Objectives — Electrical Components Management System

This file captures the learning objectives, skills exercised, and the full project prompt used to generate this repository. It's intended as a reference for instructors, students, and CI exercises.

## Checklist of skills covered

- GitHub Actions: workflow authoring, caching, artifact upload, CI triggers (push, pull_request)
- Continuous Integration concepts: build, test, verify, fail-fast, artifact publishing
- Maven build lifecycle: compile, test, verify, package
- Java 17 language features and platform
- Spring Boot 3.x application development (REST controllers, services, beans)
- Spring Validation (Jakarta Validation annotations)
- Lombok for boilerplate reduction (builders, getters/setters)
- JUnit 5 unit testing
- Mockito for mocking dependencies in unit tests
- Spring test slice: @WebMvcTest and MockMvc for controller tests
- Exception handling best practices (ControllerAdvice + custom exceptions)
- In-memory storage implementation using ConcurrentHashMap and AtomicLong
- Clean layered architecture: controller → service → repository → storage
- DTO patterns: request/response objects to avoid exposing entities
- JaCoCo code coverage tooling and configuration
- Writing tests that exercise success, validation failure, and not-found scenarios
- Creating reproducible CI failures and recovery exercises (test-breaking and fix workflow)

## How to use this file

- Read the skills checklist to see which competencies this project exercises.
- Use the "CI Learning Exercises" in the main README to practice breaking and fixing builds.
- Inspect the tests under `src/test/java` to see how unit and controller tests are structured.

## Project Prompt (original)

The following is the original project prompt used to generate this repository. It includes functional requirements, architecture, testing, CI, and README requirements.

---

Build an Electrical Components Management System (CI Learning Project)

Act as a Senior Java Architect and generate a complete Spring Boot project specifically designed for learning GitHub Actions CI pipelines.

Objective

The primary goal of this project is to learn:

* GitHub Actions
* Continuous Integration (CI)
* Maven Build Lifecycle
* JUnit 5 Testing
* Mockito Testing
* Code Coverage with JaCoCo
* Pull Request Validation
* Build Artifact Generation
* CI Pipeline Failure and Recovery

This is a learning project, so keep the implementation simple but follow clean architecture and industry-standard coding practices.

Technology Stack

* Java 17
* Spring Boot 3.x
* Maven
* Lombok
* JUnit 5
* Mockito
* Spring Validation
* JaCoCo

Do NOT use Docker.

Do NOT use a database.

Use an in-memory storage implementation using ConcurrentHashMap.

Project Name

Electrical Components Management System

Functional Requirements

The system should manage electrical components.

Supported component types:

* Resistor
* Capacitor
* Inductor
* Diode
* Transistor

Component Entity

Fields:

```java
Long id;
String componentName;
String componentType;
String manufacturer;
Double price;
Integer quantity;
```

Validation Rules:

* componentName cannot be blank
* componentType cannot be blank
* manufacturer cannot be blank
* price must be greater than zero
* quantity cannot be negative

APIs

Create Component

```http
POST /api/v1/components
```

Get Component By Id

```http
GET /api/v1/components/{id}
```

Get All Components

```http
GET /api/v1/components
```

Update Component

```http
PUT /api/v1/components/{id}
```

Delete Component

```http
DELETE /api/v1/components/{id}
```

Architecture

Follow layered architecture:

```
Controller
    ↓
Service
    ↓
Repository
    ↓
In-Memory Storage
```

Create packages:

```
controller
service
repository
dto
model
exception
config
```

Use constructor injection everywhere.

Use Lombok where appropriate.

DTO Requirements

Create separate DTOs:

```
CreateComponentRequest
UpdateComponentRequest
ComponentResponse
```

Do not expose entities directly from controllers.

Exception Handling

Implement:

```
ComponentNotFoundException
GlobalExceptionHandler
```

Handle:

* Resource not found
* Validation errors
* Generic exceptions

Return meaningful API responses.

Unit Testing Requirements (Very Important)

Generate comprehensive JUnit 5 and Mockito tests.

Service Layer Tests

Cover:

* createComponent_success
* getComponentById_success
* getComponentById_notFound
* getAllComponents_success
* updateComponent_success
* updateComponent_notFound
* deleteComponent_success
* deleteComponent_notFound

Use Mockito where appropriate.

Controller Layer Tests

Use:

```java
@WebMvcTest
MockMvc
```

Cover:

* Create component success
* Get component success
* Update component success
* Delete component success
* Validation failure scenarios
* Not found scenarios

Coverage Requirements

Configure JaCoCo plugin.

Target:

* Minimum 80% line coverage
* Coverage report generated during Maven verify phase

Command:

```bash
mvn clean verify
```

Maven Requirements

The project must successfully run:

```bash
mvn clean compile
mvn test
mvn clean verify
mvn clean package
```

No compilation warnings.

No failing tests.

GitHub Actions

Create:

```
.github/workflows/ci.yml
```

Requirements:

Trigger

```yaml
on:
  push:
  pull_request:
```

Pipeline Steps

1. Checkout code
2. Setup Java 17
3. Cache Maven dependencies
4. Execute:

```bash
mvn clean verify
```

5. Generate JaCoCo coverage report
6. Upload generated JAR artifact
7. Fail pipeline if tests fail

README

Create a detailed README.md including:

Project Overview

Explain purpose of project.

Running Locally

```bash
mvn spring-boot:run
```

Running Tests

```bash
mvn test
```

Running Coverage

```bash
mvn clean verify
```

GitHub Actions Workflow

Explain:

* How workflow is triggered
* What each step does
* How to inspect logs
* How to view failed builds

CI Learning Exercises

Include examples such as:

1. Intentionally break a test.
2. Push code.
3. Observe CI failure.
4. Fix test.
5. Push again.
6. Observe CI success.

Code Quality Expectations

* Follow SOLID principles where reasonable.
* Use meaningful naming conventions.
* Add JavaDoc for important classes.
* Keep methods small and readable.
* Follow Spring Boot best practices.
* Production-quality structure, but beginner-friendly implementation.

Generate the complete project structure with all source files, tests, pom.xml, GitHub Actions workflow, and README.

---

## Tags / Skills mapping (quick reference)

- CI/CD: GitHub Actions, workflow YAML, caching, artifact upload
- Build: Maven (compile/test/verify/package), Surefire, JaCoCo
- Languages & Frameworks: Java 17, Spring Boot 3, Jakarta Validation
- Testing: JUnit 5, Mockito, @WebMvcTest, MockMvc
- Libraries: Lombok, springdoc-openapi (Swagger)
- Architecture: layered controller/service/repository + DTOs

---

If you want this file modified (for example to remove the full prompt, add instructor notes, or include a short checklist for grading), tell me how you'd like it updated and I'll apply the change.

