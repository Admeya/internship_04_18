package ru.iteco.internship;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import ru.iteco.internship.dao.DocumentDao;
import ru.iteco.internship.dao.DocumentDaoImpl;
import ru.iteco.internship.service.DocumentService;

/**
 * Тестирование сервиса по работе с документами
 * Created by ie.bykova on 09.04.2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class DocumentServiceTests {

    @Spy
    private DocumentDao dao = new DocumentDaoImpl();
    private DocumentService service = new DocumentService(dao);

    @Test
    public void HP_calculateAverageSumTest() {
        Long average = service.calculateAverageSum(1000, 2000);
        Assert.assertEquals(new Long(1500), average);
    }

    @Test
    public void HP_calculateAverageSumMaxIntTest() {
        Long average = service.calculateAverageSum(Integer.MAX_VALUE - 1, 2);
        Assert.assertEquals(new Long(1_073_741_824), average);
    }

    @Test
    public void HP_calculateAverageSumNullTest() {
        Long average = service.calculateAverageSum(0, 0);
        Assert.assertEquals(new Long(0), average);
    }

    @Test
    public void HP_ServiceMethodTest() {
        Assert.assertEquals(994.00, service.calculateAverageDocumentSum(2L, 3L), 0.01);
    }
}
