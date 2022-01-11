package application.service.impl;

import application.domain.Customer;
import application.repository.CustomerRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link CustomerHandler} class.
 */
public class CustomerHandlerTest {
    private static final String CUSTOMER_NAME = "TEST NAME";
    private static final String CUSTOMER_NAME_2 = "NAME TEST";
    private static final Customer CUSTOMER_1 = new Customer(CUSTOMER_NAME);
    private static final Customer CUSTOMER_2 = new Customer(CUSTOMER_NAME_2);
    private static final Integer CUSTOMER_ID = 1;
    private static final List<Customer> REPOSITORY_LIST = new ArrayList<>(Arrays.asList(CUSTOMER_1, CUSTOMER_2));

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerHandler underTest;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCustomerById() {
        //given
        when(customerRepository.findByCustomerId(CUSTOMER_ID)).thenReturn(CUSTOMER_1);
        //when
        Customer expected = underTest.getCustomerById(CUSTOMER_ID);
        //then
        assertThat(expected.getCustomerId(), equalTo(CUSTOMER_1.getCustomerId()));
        assertThat(expected.getCustomerName(), equalTo(CUSTOMER_1.getCustomerName()));
        assertThat(expected, equalTo(CUSTOMER_1));
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetCustomerByIdWithNullValue() {
        //given
        //when
        underTest.getCustomerById(null);
        //then
    }

    @Test
    public void testGetAllCustomer() {
        //given
        when(customerRepository.findAll()).thenReturn(REPOSITORY_LIST);
        //when
        List<Customer> expected = underTest.getAllCustomer();
        //then
        assertThat(expected.size(), equalTo(REPOSITORY_LIST.size()));
        assertThat(expected.get(0).getCustomerName(), equalTo(CUSTOMER_1.getCustomerName()));
        assertThat(expected.get(1).getCustomerName(), equalTo(CUSTOMER_2.getCustomerName()));
    }

    @Test(dataProvider = "dataProviderForIsNameValid")
    public void testIsNameValid(String name, boolean result) {
        //given
        //when
        boolean expected = underTest.isNameValid(name);
        //then
        assertThat(expected, equalTo(result));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testIsNameValidWithNullValue() {
        //given
        //when
        underTest.isNameValid(null);
        //then
    }


    @DataProvider(name = "dataProviderForIsNameValid")
    public Object[][] sampleResults() {
        return new Object [][] {
                {CUSTOMER_NAME, true},
                {"Dr. Sample Shane", true},
                {"NM", false},
                {"This should be a really long name for a Customer", false},
                {"Dr. 22", false},
        };
    }
}
