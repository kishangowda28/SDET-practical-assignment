# 3 - Defect Identification

## Defect 1 - completed has wrong JSON type

The supplied response contains:

```json
"completed": "true"
```

FR-05 requires a Boolean.

Expected:

```json
"completed": true
```

This is a definite contract violation.

## Defect 2 - Locale needs environment-value clarification

The response contains:

```json
"locale": "en"
```

`en` is structurally a valid BCP 47 language tag. It should not be called invalid solely because the example uses `en-IN`.

However, the assignment states the user context is India and gives `en-IN` as an example. If the product contract requires the user's actual locale, the returned `en` may be an incorrect value even though it is valid BCP 47 syntax. This should be confirmed against the application's locale contract.

## Defect 3 - Timestamp timezone/value concern

The response uses timestamps ending in `Z`, which represents UTC. FR-05 states timestamps are in the user's local time. For the stated India context, this may be a contract mismatch unless the backend intentionally serializes UTC.

The timestamps are chronologically consistent: end_date occurs two minutes after start_date.
