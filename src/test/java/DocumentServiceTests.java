import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import ru.iteco.internship.service.DocumentsService;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;

/**
 * Created by ie.bykova on 09.04.2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class DocumentServiceTests {

    DocumentsService service = mock(DocumentsService.class);

    @Before
    public void before(){
        service = new DocumentsService();
    }

    @Test
    public void calculateAverageSumTest(){
        BigDecimal average = (service.calculateAverageSum(new BigDecimal(1000.20), new BigDecimal(2000.40)))
                .setScale(2, BigDecimal.ROUND_UP);
        Assert.assertEquals(new BigDecimal(1500.31).setScale(2, BigDecimal.ROUND_UP), average);
    }
}
