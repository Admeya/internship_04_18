package ru.iteco.internship;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import ru.iteco.internship.dao.DocumentDao;
import ru.iteco.internship.dao.DocumentDaoImpl;
import ru.iteco.internship.dto.Document;
import ru.iteco.internship.enums.DocumentTypes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Тестирование {@link DocumentDao}
 * Created by ie.bykova on 09.04.2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class DocumentDaoImplTests {

    @Spy
    private DocumentDao dao = new DocumentDaoImpl();

    @Test
    public void HP_getFioAddressTest() {
        assertEquals(2, dao.getFioAndAddress().size());
    }

    @Test
    public void HP_getByIdTest() {
        Long documentId = 2L;
        Document doc = dao.getById(documentId);
        assertTrue(doc.getDocumentId().equals(documentId));
    }

    @Test
    public void NHP_getByIdTest() {
        Long documentId = null;
        Document doc = dao.getById(documentId);
        Assert.assertEquals(null, doc);
    }

    @Test
    public void HP_getFioMaxSum() {
        List<Document> docs = dao.getFioMaxSum();
        assertEquals(1, docs.size());
        assertEquals(new BigDecimal(1989), docs.get(0).getAmount());
    }

    @Test
    public void HP_getCountDocTypes() {
        List<Document> docs = dao.getCountDocTypes();
        assertEquals(2, docs.size());
        assertTrue(docs.get(1).getDocTypeName().equals(DocumentTypes.RENT.getValue()));
    }

    @Test
    public void HP_isValid() {
        when(dao.getById(Matchers.anyLong())).thenReturn(new Document());

        Answer<Document> ansDoc = invocationOnMock -> {
            Object[] arguments = invocationOnMock.getArguments();

            if (arguments != null && arguments.length > 0 && arguments[0] != null) {
                return (Document) arguments[0];
            }
            return null;
        };
        when(dao.getFioAndAddress()).thenAnswer(ansDoc);
        when(dao.getCountDocTypes()).thenReturn(new ArrayList<>());
        when(dao.getFioMaxSum()).thenReturn(new ArrayList<>());
    }
}
