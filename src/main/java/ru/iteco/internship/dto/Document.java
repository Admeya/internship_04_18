package ru.iteco.internship.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Документ
 * Created by ie.bykova on 09.04.2018.
 */
@Getter
@Setter
public class Document {
    /**
     * Идентификатор документа
     */
    private Long documentId;
    /**
     * Название документа
     */
    private String docName;
    /**
     * Дата начала договора
     */
    private Date dateStart;
    /**
     * Дата окончания договора
     */
    private Date dateEnd;
    /**
     * Сумма договора
     */
    private BigDecimal amount;
    /**
     * ФИО пользователя
     */
    private String userFio;
    /**
     * Адрес участка аренды
     */
    private String address;
    /**
     * Количество договоров
     */
    private Long count;
    /**
     * Наименование типа договора
     */
    private String docTypeName;
}
