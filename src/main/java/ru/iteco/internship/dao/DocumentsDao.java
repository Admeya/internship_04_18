package ru.iteco.internship.dao;

import ru.iteco.internship.dto.Documents;

import java.util.List;

/**
 * DAO для работы с документами
 * Created by ie.bykova on 09.04.2018.
 */
public interface DocumentsDao {
    /**
     * Показать ФИО пользователей и адреса участков аренды
     * по действующим в данный момент договорам
     * @return список записей
     */
    List<Documents> getFioAddress();

    /**
     * Вывести ФИО пользователей с максимальной суммой по договорам
     * @return список записей
     */
    List<Documents> getFioMaxSum();

    /**
     * Показать количество действующих договоров по каждому из существующих типов
     * @return список записей
     */
    List<Documents> getCountDocTypes();
}
