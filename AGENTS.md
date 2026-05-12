# Project Overview

Backend application written in Java using Spring Boot.

Architecture style:
- modular monolith
- DDD-inspired module boundaries
- event-driven integrations
- Outbox pattern for Kafka publishing

Main modules:
- core
- application
- test

## Module Rules

Allowed dependencies:

application -> core
test -> application

Forbidden dependencies:

core -> application
core -> test
application -> test