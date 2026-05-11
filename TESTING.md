# Testing Guidelines

## Goal

All tests must be readable, deterministic, isolated, and focused on behavior, not implementation details.

The project uses a dedicated `mindtrack-test` module for integration tests.

## Test Module Rules

Project modules:

- `mindtrack-core` — business/domain logic
- `mindtrack-application` — Spring Boot application, configuration, infrastructure
- `mindtrack-test` — unit, integration and API tests

Allowed dependencies:

```text
test -> application -> core