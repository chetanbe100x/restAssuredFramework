# REST Assured API Automation Framework

A comprehensive, industry-standard REST API testing framework built with Java, REST Assured, and TestNG.

## Features

- Modular and maintainable architecture
- Environment-specific configuration
- Detailed logging and reporting
- Support for authentication
- JSON schema validation
- Data-driven testing capabilities
- Parallel test execution

## Project Structure

```
├── src
│   ├── main/java/com/restassured/framework
│   │   ├── client          # API client classes
│   │   ├── config          # Configuration management
│   │   ├── constants       # Constants and enums
│   │   ├── listeners       # TestNG listeners
│   │   ├── pojo            # POJO classes for serialization/deserialization
│   │   ├── reporting       # Reporting utilities
│   │   └── utils           # Utility classes
│   │
│   └── test
│       ├── java/com/restassured/framework/tests
│       │   ├── api         # API test classes
│       │   ├── base        # Base test classes
│       │   └── integration # Integration test classes
│       │
│       └── resources
│           ├── config      # Configuration files
│           ├── payloads    # Request payload templates
│           ├── schemas     # JSON/XML schemas for validation
│           └── testng      # TestNG XML files
│
├── logs                    # Test execution logs
└── reports                 # Test execution reports
```

## Prerequisites

- Java 11 or higher
- Gradle 7.0 or higher

## Getting Started

1. Clone the repository
2. Configure environment properties in `src/test/resources/config`
3. Run tests using Gradle:

```bash
# Run all tests
./gradlew test

# Run smoke tests
./gradlew test -PtestSuite=smoke

# Run regression tests
./gradlew test -PtestSuite=regression

# Run tests with specific environment
./gradlew test -Denv=qa
```

## Key Components

### REST Assured Client

The framework provides a robust REST Assured client that handles:

- Request building
- Response validation
- Authentication
- Logging
- Error handling

### Configuration Management

Environment-specific configuration is managed through properties files:

- `dev-config.properties`
- `qa-config.properties`
- `prod-config.properties`

### Reporting

The framework generates detailed HTML reports using Extent Reports, including:

- Test status (pass/fail/skip)
- Request details
- Response details
- Error messages
- Execution time

### Logging

Comprehensive logging is implemented using SLF4J and Logback, with logs stored in:

- Console output
- Log files

## Best Practices

1. Use the provided client classes for API interactions
2. Implement one test class per API resource
3. Keep tests independent and atomic
4. Use appropriate assertions
5. Validate response schemas
6. Implement proper error handling
7. Use meaningful test names and descriptions

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request
