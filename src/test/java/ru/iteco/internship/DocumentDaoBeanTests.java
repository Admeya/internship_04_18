package ru.iteco.internship;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import ru.iteco.internship.dao.DocumentDao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Тестирование {@link DocumentDao}
 * Created by ie.bykova on 09.04.2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class DocumentDaoBeanTests {

//    @Before
//    @Test
//    public void setUp() {
//        MockitoAnnotations.initMocks(DocumentDao.class);
//    }

    private DocumentDao doc = mock(DocumentDao.class);

    @Test
    public void getFioAddressTest(){
        assertEquals(2, doc.getFioAddress().size());
    }

}
