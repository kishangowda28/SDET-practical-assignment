# SDET Practical Assignment - Java + Playwright

## Eclipse / Maven setup

### Prerequisites
- JDK 17+
- Eclipse IDE with Maven support
- Maven 3.8+
- Internet access for Maven dependency/browser installation

### Import into Eclipse
1. Extract this ZIP.
2. Eclipse -> File -> Import -> Maven -> Existing Maven Projects.
3. Select the extracted `SDET-Practical-Assignment` folder.
4. Finish the import.
5. Right-click project -> Maven -> Update Project.

### Install Playwright Chromium
Open a terminal in the project root:

```bash
mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install chromium"
```

### Configure the UI URL
The assignment gives `https://test.com/autocomplete-form` as an assumed URL. Replace it with the real evaluator URL or pass:

```bash
mvn test -Dbase.url=https://YOUR-TEST-APP/autocomplete-form
```

### Configure the API URL

```bash
mvn test -Dapi.url=https://YOUR-TEST-APP/api/response
```

### Run all tests

```bash
mvn clean test
```

### Run headed / headless

```bash
mvn test -Dheadless=false
mvn test -Dheadless=true
```

### Run from Eclipse
Right-click `AutocompleteTest.java` -> Run As -> JUnit Test.

## Important
The supplied assignment contains the UI HTML structure and requirements but does not provide a live implementation or a real API endpoint. Therefore the default URLs are placeholders.

The exact Escape behavior is also not defined in the supplied requirements, so the Escape test deliberately does not assume a behavior that was not specified.

## Included
- Java 17 Maven project
- Playwright UI automation
- JUnit 5
- Page Object Model
- API contract validation
- API negative tests
- Requirement analysis
- Top 10 test scenarios
- Defect identification
- Detailed test cases
- AI reflection
- Architecture discussion
