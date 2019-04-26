package br.com.rk.entities.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Rhuan Karlus
 * @since 28/03/19
 */
public class LocalDateTimeConverterTest {

    public LocalDateTimeConverter sut;

    @BeforeEach
    public void setUp() {
        sut = new LocalDateTimeConverter();
    }

    @Test
    public void should_convert_local_date_time_to_correct_timestamp() {
        final LocalDateTime localDateTime = LocalDateTime.now();

        assertEquals(
                Timestamp.valueOf(localDateTime),
                sut.convertToDatabaseColumn(localDateTime),
                "The local date time was converted incorrectly");
    }

    @Test
    public void should_convert_code_to_correct_audit() {
        final LocalDateTime localDateTime = LocalDateTime.now();
        final Timestamp timestamp = Timestamp.valueOf(localDateTime);

        assertEquals(
                timestamp,
                Timestamp.valueOf(sut.convertToEntityAttribute(timestamp)),
                "The auditType was converted incorrectly");
    }

    @Test
    public void should_convert_to_null_when_local_date_time_is_null() {
        assertNull(
                sut.convertToDatabaseColumn(null),
                "The conversor should return null to persist on database when local date time is null.");
    }

    @Test
    public void should_convert_to_null_when_timestamp_is_null() {
        assertNull(
                sut.convertToEntityAttribute(null),
                "The conversor should return null when the table value is null.");
    }

}
