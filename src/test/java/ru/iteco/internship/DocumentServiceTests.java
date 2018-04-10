package ru.iteco.internship;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import ru.iteco.internship.service.DocumentService;

/**
 * Тестирование сервиса по работе с документами
 * Created by ie.bykova on 09.04.2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class DocumentServiceTests {

    @InjectMocks
    private DocumentService service = new DocumentService();

    @Test
    public void calculateAverageSumTestHp(){
        Integer average = service.calculateAverageSum(1000, 2000);
        Assert.assertEquals(new Integer(1500), average);
    }


    @Test
    public void calculateAverageSumTestMaxIntNHP(){
        Integer average = service.calculateAverageSum(Integer.MAX_VALUE-1, 2);
        Assert.assertEquals(new Integer(1_073_741_824), average);
    }

    @Test
    public void calculateAverageSumTestNullHP(){
        Integer average = service.calculateAverageSum(0, 0);
        Assert.assertEquals(new Integer(0), average);
    }
}
