package br.com.rk.entities.converters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author Rhuan Karlus
 * @since 28/03/19
 */
public class LocalDateTimeConverterTest {

    public LocalDateTimeConverter sut;

    @Before
    public void setUp() {
        sut = new LocalDateTimeConverter();
    }

    @Test
    public void should_convert_local_date_time_to_correct_timestamp() {
        final LocalDateTime localDateTime = LocalDateTime.now();

        Assert.assertEquals(
                "The local date time was converted incorrectly",
                Timestamp.valueOf(localDateTime),
                sut.convertToDatabaseColumn(localDateTime));
    }

    @Test
    public void should_convert_code_to_correct_audit() {
        final LocalDateTime localDateTime = LocalDateTime.now();
        final Timestamp timestamp = Timestamp.valueOf(localDateTime);

        Assert.assertEquals(
                "The auditType was converted incorrectly",
                timestamp,
                Timestamp.valueOf(sut.convertToEntityAttribute(timestamp)));
    }

    @Test
    public void should_convert_to_null_when_local_date_time_is_null() {
        Assert.assertNull(
                "The conversor should return null to persist on database when local date time is null.",
                sut.convertToDatabaseColumn(null));
    }

    @Test
    public void should_convert_to_null_when_timestamp_is_null() {
        Assert.assertNull(
                "The conversor should return null when the table value is null.",
                sut.convertToEntityAttribute(null));
    }

}
