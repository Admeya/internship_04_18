package ru.iteco.internship.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iteco.internship.dao.DocumentDao;
import ru.iteco.internship.dao.DocumentDaoImpl;
import ru.iteco.internship.dto.Document;

/**
 * Сервис для работы с документами
 * Created by ie.bykova on 09.04.2018.
 */
@Service
@Slf4j
public class DocumentService {

    @Autowired
    DocumentDao dao;

    public DocumentService(DocumentDao dao){
        this.dao = dao;
    }

    /**
     * Вычисление среднего значения по договорам
     * @param document_id_first идентификатор первого договора
     * @param document_id_second идентификатор второго договора
     * @return
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
    public Integer calculateAverageSum(int amount1, int amount2) {
        return (amount1 + amount2)/2;
    }
}
