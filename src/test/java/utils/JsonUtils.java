package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    private JsonUtils() {
    }

    public static List<Map<String, String>> readJsonArray(String fileName) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            InputStream inputStream = JsonUtils.class
                    .getClassLoader()
                    .getResourceAsStream(fileName);

            if (inputStream == null) {
                throw new RuntimeException("JSON file not found: " + fileName);
            }

            return objectMapper.readValue(
                    inputStream,
                    new TypeReference<List<Map<String, String>>>() {}
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file: " + fileName, e);
        }
    }
    public static <T> List<T> readJsonArray(String fileName, TypeReference<List<T>> typeReference) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            InputStream inputStream = JsonUtils.class
                    .getClassLoader()
                    .getResourceAsStream(fileName);

            if (inputStream == null) {
                throw new RuntimeException("JSON file not found: " + fileName);
            }

            return objectMapper.readValue(inputStream, typeReference);

        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file: " + fileName, e);
        }
    }
}