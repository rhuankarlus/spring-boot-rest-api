package br.com.rk.util.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.stream.IntStream;

/**
 * @author Rhuan Karlus
 * @since 12/04/2019
 */
public class JsonCreator {

    public static String createJsonStringArray(final Object object, final int numberElements) {
        final StringBuilder jsonStringArray = new StringBuilder("[");
        IntStream
                .rangeClosed(1, numberElements)
                .forEach(value -> jsonStringArray.append(asJsonString(object)).append(","));

        return jsonStringArray.deleteCharAt(jsonStringArray.length() - 1).append("]").toString();
    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
