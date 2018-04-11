package ru.iteco.internship.dao;

import ru.iteco.internship.dto.Document;

import java.util.List;

/**
 * DAO для работы с документами
 * Created by ie.bykova on 09.04.2018.
 */
public interface DocumentDao {
    /**
     * Получение документа по идентификатору
     * @param documentId идентификатор документа
     * @return документ
     */
    Document getById(Long documentId);
    /**
     * Показать ФИО пользователей и адреса участков аренды
     * по действующим в данный момент договорам
     * @return список записей
     */
    List<Document> getFioAndAddress();

    /**
     * Вывести ФИО пользователей с максимальной суммой по договорам
     * @return список записей
     */
    List<Document> getFioMaxSum();

    /**
     * Показать количество действующих договоров по каждому из существующих типов
     * @return список записей
     */
    List<Document> getCountDocTypes();
}
