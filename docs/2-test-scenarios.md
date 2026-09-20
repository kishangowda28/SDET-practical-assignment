# 2 - Top 10 Test Scenarios

| Rank | Scenario | Risk | Rationale |
|---|---|---|---|
| 1 | Valid selection and submission | Critical | Core journey and persistence behavior. |
| 2 | Invalid input submission | Critical | Validates rejection and error handling. |
| 3 | API response contract | Critical | Contract failure can affect persisted data and integrations. |
| 4 | completed Boolean validation | Critical | Explicit data-type requirement. |
| 5 | Missing required API field | Critical | Detects incomplete records. |
| 6 | Prefix suggestion filtering | High | Core autocomplete behavior. |
| 7 | Match-anywhere filtering | High | Configuration changes matching semantics. |
| 8 | Suggestion selection populates input | High | Required before successful submission. |
| 9 | Tab navigation | Medium | Required keyboard interaction. |
| 10 | Enter/Escape | Medium | Required keyboard interactions; exact Escape behavior is unspecified. |
