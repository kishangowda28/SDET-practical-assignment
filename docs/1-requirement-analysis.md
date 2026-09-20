# 1 - Requirement Analysis

## FR-01 Text Input
Users can type any response in the text field or select a suggestion.

## FR-02 Suggestion Filtering - Prefix Match
If typed characters match the initial characters of a suggestion, that suggestion remains visible. If they do not match the beginning of any suggestion, those suggestions disappear.

## FR-03 Suggestion Filtering - Match Anywhere
When enabled in backend configuration, suggestions remain visible if they contain the typed text anywhere.

## FR-04 Form Submission
Selecting Next sends a REST API call. HTTP 200 indicates successful submission, followed by a success message. Invalid input displays an error message.

## FR-05 Backend Data Contract
Required properties:
- account_id
- account_email
- start_date
- end_date
- locale
- text
- suggestion_list
- completed

The assignment also specifies Chrome on Windows 10, English, and India/IST test context.
