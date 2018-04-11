package ru.iteco.internship.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.iteco.internship.dao.DocumentDao;
import ru.iteco.internship.dao.DocumentDaoImpl;
import ru.iteco.internship.dto.Document;
import ru.iteco.internship.enums.DocumentTypes;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с документами
 * Created by ie.bykova on 09.04.2018.
 */
@Service
@Slf4j
public class DocumentService {

    private DocumentDao dao;
    public DocumentService(DocumentDao dao){
        this.dao = dao;
    }

    /**
     * Получение сумм договоров по идентификаторам и вычисление среднего значения по договорам
     * @param document_id_first идентификатор первого договора
     * @param document_id_second идентификатор второго договора
     * @return средняя сумма двух договоров
     */
    public double calculateAverageDocumentSum(long document_id_first, long document_id_second){
        dao = new DocumentDaoImpl();
        Document doc = dao.getById(document_id_first);
        int sum = doc.getAmount().intValue();
        Document doc1 =  dao.getById(document_id_second);
        int sum1 = doc1.getAmount().intValue();

        return calculateAverageSum(sum,sum1);
    }

    /**
     * Расчет средней суммы двух договоров
     * @param amount1 сумма первого договора
     * @param amount2 сумма второго договора
     * @return средняя сумма двух договоров
     */
    public Long calculateAverageSum(int amount1, int amount2) {
        return ((long)amount1 + amount2)/2;
    }

    /**
     * Проверка на заполненность поля адреса у договора
     * @param documentId идентификатор договора
     * @return заполнено или нет
     */
    public boolean isValidAddress (Long documentId) {
        Boolean condition = false;
        if (documentId == null) return condition;

        Document doc = dao.getById(documentId);
        if (doc.getAddress() != null){
            List<Document> doc2 = dao.getFioAndAddress();
            Optional<Document> opt = doc2.stream().filter(d->d.getDocumentId().equals(doc.getDocumentId())).findFirst();
            if (opt.isPresent()){
                log.info("У договора заполнен адрес аренды");
                condition = true;
            } else {
                log.error("Внимание! У договора нет адреса аренды!");
            }
        } else if (DocumentTypes.RENT.getValue().equals(doc.getDocTypeName())){
            log.error("Внимание! У договора аренды нет адреса аренды!");
        } else {
            log.error("У договора не должно быть адреса аренды!");
        }
        return condition;
    }
}
