package ru.iteco.internship;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import ru.iteco.internship.dao.DocumentDao;
import ru.iteco.internship.dao.DocumentDaoImpl;
import ru.iteco.internship.service.DocumentService;

import static org.mockito.Mockito.when;

/**
 * Тестирование сервиса по работе с документами
 * Created by ie.bykova on 09.04.2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class ComplicatedDocumentServiceTests {
    @Spy
    private DocumentDao dao = new DocumentDaoImpl();
    private DocumentService service = new DocumentService(dao);

    // В проверяемый метод заходит, однако в методе вызывается два метода заглушки, и when тут некорректно отрабатывает
    @Test
    public void HP_isValidAddressTest() {
        when(service.isValidAddress(3L)).thenReturn(true);
    }
}
