package api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.OffsetDateTime;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class ApiValidationTest {

    private final ObjectMapper mapper = new ObjectMapper();
    private final HttpClient client = HttpClient.newHttpClient();

    private final String apiUrl = System.getProperty(
        "api.url",
        "https://test.com/api/response"
    );

    @Test
    void TC_API_001_validateResponseContract() throws Exception {
        HttpResponse<String> response = getResponse();

        assertEquals(200, response.statusCode());

        JsonNode json = mapper.readTree(response.body());

        String[] required = {
            "account_id",
            "account_email",
            "start_date",
            "end_date",
            "locale",
            "text",
            "suggestion_list",
            "completed"
        };

        for (String field : required) {
            assertTrue(json.has(field), field + " is required");
        }

        assertTrue(json.get("account_id").isTextual());
        assertTrue(json.get("account_email").isTextual());
        assertTrue(json.get("start_date").isTextual());
        assertTrue(json.get("end_date").isTextual());
        assertTrue(json.get("locale").isTextual());
        assertTrue(json.get("text").isTextual());
        assertTrue(json.get("suggestion_list").isTextual());

        assertTrue(
            json.get("completed").isBoolean(),
            "completed must be JSON Boolean"
        );

        OffsetDateTime start =
            OffsetDateTime.parse(json.get("start_date").asText());

        OffsetDateTime end =
            OffsetDateTime.parse(json.get("end_date").asText());

        assertFalse(
            end.isBefore(start),
            "end_date must not be before start_date"
        );

        assertTrue(
            isBcp47Like(json.get("locale").asText()),
            "Invalid BCP 47 language-tag structure"
        );

        validateSuggestionList(json);
    }

    @Test
    void TC_API_002_negativeMissingField() {
        String invalidJson = """
        {
          "account_id": "98765",
          "account_email": "test123@gmail.com",
          "start_date": "2024-03-15T10:30:00Z",
          "end_date": "2024-03-15T10:32:00Z",
          "locale": "en-IN",
          "text": "agile methodology",
          "completed": true
        }
        """;

        assertThrows(
            AssertionError.class,
            () -> validateRequiredFields(invalidJson)
        );
    }

    @Test
    void TC_API_003_negativeInvalidCompletedType() {
        String invalidJson = """
        {
          "account_id": "98765",
          "account_email": "test123@gmail.com",
          "start_date": "2024-03-15T10:30:00Z",
          "end_date": "2024-03-15T10:32:00Z",
          "locale": "en-IN",
          "text": "agile methodology",
          "suggestion_list": "agile methodology",
          "completed": "true"
        }
        """;

        assertThrows(
            AssertionError.class,
            () -> validateCompletedIsBoolean(invalidJson)
        );
    }

    private HttpResponse<String> getResponse() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl))
            .GET()
            .build();

        return client.send(
            request,
            HttpResponse.BodyHandlers.ofString()
        );
    }

    private void validateRequiredFields(String body) throws Exception {
        JsonNode json = mapper.readTree(body);

        String[] required = {
            "account_id",
            "account_email",
            "start_date",
            "end_date",
            "locale",
            "text",
            "suggestion_list",
            "completed"
        };

        for (String field : required) {
            assertTrue(json.has(field), field + " is required");
        }
    }

    private void validateCompletedIsBoolean(String body) throws Exception {
        JsonNode json = mapper.readTree(body);

        assertTrue(
            json.get("completed").isBoolean(),
            "completed must be Boolean"
        );
    }

    private boolean isBcp47Like(String locale) {
        Pattern pattern = Pattern.compile(
            "^[A-Za-z]{2,8}(?:-[A-Za-z0-9]{1,8})*$"
        );

        return pattern.matcher(locale).matches();
    }

    private void validateSuggestionList(JsonNode json) {
        String text =
            json.get("text").asText().toLowerCase();

        String[] suggestions =
            json.get("suggestion_list")
                 .asText()
                 .split(",");

        for (String raw : suggestions) {
            String suggestion =
                raw.trim().toLowerCase();

            assertTrue(
                suggestion.startsWith(text)
                    || suggestion.contains(text),
                "Suggestion does not match text: " + suggestion
            );
        }
    }
}
