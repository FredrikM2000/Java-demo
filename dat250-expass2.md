DAT250 Assignment 2 – Short Report

1. Technical Issues and Learning Experience
This was my first assignment using Spring Boot and REST APIs, so there was a notable learning curve. I had to figure out how the system manages resources, object references, and automated testing. Some of the issues I encountered and resolved include:

- Null fields in Votes: Initially, creating a Vote sometimes resulted in null values for nested objects like voter and poll. I resolved this by ensuring the Vote contained full references to existing User and Poll objects before posting it.
- Understanding PUT vs POST: At first, I was unsure whether to use @PutMapping or @PostMapping for updating votes. After reviewing the assignment and REST conventions, it became clear that @PutMapping should be used for updates.
- Automated testing setup: I experimented with multiple approaches, including TestRestTemplate, before settling on using Spring’s RestClient with JUnit, which allowed me to automate testing without manual interaction.

These steps helped me understand how Spring Boot handles dependency injection, serialization, and RESTful operations.

2. Pending/Optional Tasks
The following optional tasks were not completed:
1. API Documentation (Step 6): I did not integrate Springdoc to generate Swagger UI documentation for the API.
2. Build Automation (Step 7): I did not create a GitHub Actions workflow to automatically run the test scenarios.

3. Summary
All required tasks were successfully completed. The API supports CRUD operations for Users, Polls, and Votes, and automated tests verify that creating, updating, listing, and deleting resources works as expected. The assignment provided a practical introduction to Spring Boot and REST API development.
