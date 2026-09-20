# 4 - Detailed Test Cases

## TC-001 - Valid suggestion submission
**Preconditions:** User is on the autocomplete form.
**Steps:**
1. Select `agile methodology`.
2. Click Next.
**Expected:** API submission succeeds with HTTP 200 and success message appears.
**Test Data:** `agile methodology`

## TC-002 - Invalid free-text submission
**Preconditions:** User is on the form.
**Steps:**
1. Type `invalid value`.
2. Click Next.
**Expected:** Error message appears.
**Test Data:** `invalid value`

## TC-003 - Prefix filtering
**Steps:**
1. Type `agile`.
2. Inspect visible suggestions.
**Expected:** Matching suggestions remain visible according to prefix matching.
**Test Data:** `agile`

## TC-004 - Match-anywhere filtering
**Steps:**
1. Enable match-anywhere backend configuration.
2. Type a substring occurring inside suggestions.
3. Inspect visible suggestions.
**Expected:** Suggestions containing the substring remain visible.
**Test Data:** A configured substring.

## TC-005 - Suggestion selection
**Steps:**
1. Click `agile methodology`.
2. Read the input.
**Expected:** Input is populated with `agile methodology`.

## TC-006 - Tab navigation
**Steps:**
1. Focus the page.
2. Press Tab twice.
**Expected:** Focus moves through the input and Next button.

## TC-007 - Enter and Escape
**Steps:**
1. Select a valid suggestion and press Enter.
2. Type a value and press Escape.
**Expected:** Enter submits according to form behavior. Escape performs the configured application behavior. Exact Escape behavior is not defined by the assignment.

## TC-008 - API contract validation
**Steps:**
1. GET the API response.
2. Validate required fields and types.
**Expected:** All fields exist and `completed` is Boolean.

## TC-009 - Missing required field
**Steps:**
1. Validate a response without `suggestion_list`.
**Expected:** Validation fails.

## TC-010 - Invalid completed type
**Steps:**
1. Validate a response with `"completed": "true"`.
**Expected:** Validation fails.
