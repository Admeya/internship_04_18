package ru.iteco.internship;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ru.iteco.internship.dao.DocumentDao;
import ru.iteco.internship.dao.DocumentDaoImpl;
import ru.iteco.internship.dto.Document;
import ru.iteco.internship.enums.DocumentTypes;

import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Тестирование {@link DocumentDao}
 * Created by ie.bykova on 09.04.2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class DocumentDaoImplTests {

    @InjectMocks
    private DocumentDao dao = new DocumentDaoImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getFioAddressTestHP(){
        assertEquals(2, dao.getFioAddress().size());
    }

    @Test
    public void getByIdTestHP(){
        Long documentId = 2L;
        Document doc = dao.getById(documentId);
        assertTrue(doc.getDocumentId().equals(documentId));
    }

    @Test
    public void getByIdTestNHP(){
        Long documentId = null;
        Document doc = dao.getById(documentId);
        Assert.assertEquals(null, doc);
    }

    @Test
    public void getFioMaxSumHP(){
        List<Document> docs = dao.getFioMaxSum();
        assertEquals(1, docs.size());
        assertEquals(new BigDecimal(1989), docs.get(0).getAmount());
    }

    @Test
    public void getCountDocTypesHP(){
        List<Document> docs = dao.getCountDocTypes();
        assertEquals(2, docs.size());
        assertTrue(docs.get(1).getDocTypeName().equals(DocumentTypes.RENT.getValue()));
    }
}
