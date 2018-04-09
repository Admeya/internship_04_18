package ru.iteco.internship.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Сервис для работы с документами
 * Created by ie.bykova on 09.04.2018.
 */
@Service
public class DocumentsService {
    /**
     * Расчет средней суммы двух договоров
     * @param amount1 сумма первого договора
     * @param amount2 сумма второго договора
     * @return средняя сумма двух договоров
     */
    public BigDecimal calculateAverageSum(BigDecimal amount1, BigDecimal amount2) {
        return (amount1.add(amount2)).divide(new BigDecimal(2));
    }
}
