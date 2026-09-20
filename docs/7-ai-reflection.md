# 7 - AI Reflection

## Tools Used
ChatGPT.

## Usage Areas
- Maven/Java Playwright project structure
- Page Object Model design
- JUnit UI automation
- API contract validation
- Requirement analysis
- Defect analysis
- Documentation

## Modifications Made

1. Valid and invalid submission tests were made outcome-specific. A valid submission must assert success; an invalid submission must assert an error instead of allowing either result.

2. Locale analysis was corrected so that `en` is not incorrectly called invalid BCP 47. The analysis distinguishes syntactic BCP 47 validity from whether the application should return the user's expected `en-IN` locale.

3. The UI/API URLs are configurable instead of inventing real endpoints not supplied in the assignment.

4. Escape behavior is not assumed because the supplied requirements do not specify exactly what Escape must do.

## AI Limitation Identified
A simplistic interpretation could treat `en` as invalid because `en-IN` is shown as an example. That would be technically incorrect because `en` is itself a valid BCP 47 language tag. The assignment does not provide enough information to prove that `en-IN` is mandatory rather than an example.
