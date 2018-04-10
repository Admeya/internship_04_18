package ru.iteco.internship.service;

import org.springframework.stereotype.Service;

/**
 * Сервис для работы с документами
 * Created by ie.bykova on 09.04.2018.
 */
@Service
public class DocumentService {
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
