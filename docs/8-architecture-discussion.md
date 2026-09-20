# 8 - Architecture Discussion

## Design
The UI automation uses Page Object Model.

## Layers
- `pages`: locators and reusable UI actions.
- `tests`: JUnit UI tests.
- `config`: browser and context setup.
- `api`: REST response validation and negative tests.
- `docs`: assignment documentation.

## Maintainability
Selectors are centralized in the page object. Tests use business-level methods such as `clickSuggestion()` and `clickNext()` rather than duplicating selectors.

## Configuration
Runtime system properties:
- `base.url`
- `api.url`
- `headless`

This keeps environment-specific endpoints outside test logic.
