# chat-client-service

Minimal Spring Boot client service that communicates with the OpenAI-backed Spring AI provider.

## Project overview
A Java (Spring Boot) service built with Maven that uses the Spring AI OpenAI provider to interact with OpenAI models.

## Requirements
1. Java 21
2. Maven 3.6+
3. An OpenAI API key (stored in environment variable `OPENAI_API_KEY`)

## Configuration
- The service reads the OpenAI API key from the environment variable `OPENAI_API_KEY`.
- The default AI model is configured in the application properties (project-level config). Adjust the model property in `src/main/resources/application.properties` if required.

## Setting `OPENAI_API_KEY` on Windows
1. Command Prompt (current session):
   - `set OPENAI_API_KEY=your_api_key_here`
2. PowerShell (current session):
   - `$env:OPENAI_API_KEY = "your_api_key_here"`
3. Persist for future sessions (Command Prompt):
   - `setx OPENAI_API_KEY "your_api_key_here"`

## Run (development)
1. From project root:
   - `mvn spring-boot:run`
2. Or build and run the jar:
   1. `mvn clean package -DskipTests`
   2. `java -jar target/chat-client-service-<version>.jar`

## Build & Test
1. Build: `mvn clean package`
2. Run tests: `mvn test`

## IntelliJ IDEA (quick tips)
- Use Run \> Edit Configurations to add `OPENAI_API_KEY` to the environment variables for the application run configuration.
- Use the built-in Maven tool window to run goals like `spring-boot:run`, `clean`, `package`, and `test`.

## Security
- Do not commit API keys or secrets to source control.
- Use environment variables or a secret manager for production deployments.

## Contributing
1. Fork the repository
2. Create a feature branch
3. Open a pull request with a clear description

## License
Specify your project license in `LICENSE` (e.g., MIT, Apache-2.0).