package application.service.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Unit test for {@link IntToStringConverter} class.
 */
public class IntToStringConverterTest {
    private IntToStringConverter underTest;
    private static String VALID_STRING = "2";
    private static String NOT_VALID_STRING = "Not Valid String";
    private static String NOT_VALID_STRING_WITH_NUMBER = "Not Valid String with Number 2";
    private static Integer RESULT = 2;

    @BeforeMethod
    public void setUp() {
        underTest = new IntToStringConverter();
    }

    @Test
    public void testConvertToInteger() {
        //given
        //when
        int expected = underTest.convertToInteger(VALID_STRING);
        //then
        assertThat(expected, equalTo(RESULT));
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "dataProviderForFindAllAuthors")
    public void testConvertToIntegerWithIllegalArgumentExp(String value) {
        //given
        //when
        underTest.convertToInteger(value);
        //then
    }

    @DataProvider(name = "dataProviderForFindAllAuthors")
    public Object[][] sampleResults() {
        return new Object [][] {
                {NOT_VALID_STRING_WITH_NUMBER},
                {NOT_VALID_STRING},
        };
    }
}
