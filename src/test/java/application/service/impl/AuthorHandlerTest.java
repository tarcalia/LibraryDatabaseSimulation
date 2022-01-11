package application.service.impl;

import application.domain.Author;
import application.repository.AuthorRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for {@link AuthorHandler} class.
 */
public class AuthorHandlerTest {
    private static final Author AUTHOR_1 = new Author("Author_1");
    private static final Author AUTHOR_2 = new Author("Author_2");
    private static final Author AUTHOR_3 = new Author("Author_3");
    private static final String AUTHOR_NAME = ("Author_1");
    private static final List<Author> REPOSITORY_LIST = new ArrayList<>(Arrays.asList(AUTHOR_1, AUTHOR_2, AUTHOR_3));

    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    private AuthorHandler underTest;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveAuthorEntity() {
        //given
        //when
        underTest.saveAuthorEntity(AUTHOR_NAME);
        //then
        verify(authorRepository).save(new Author(AUTHOR_NAME));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSaveAuthorEntityWithNullValue() {
        //given
        //when
        underTest.saveAuthorEntity(null);
        //then

    }

    @Test
    public void testFindAllAuthors() {
        //given
        when(authorRepository.findAll()).thenReturn(REPOSITORY_LIST);
        //when
        List<Author> result = underTest.findAllAuthors();
        //then
        assertThat(result, equalTo(REPOSITORY_LIST));
    }
}
