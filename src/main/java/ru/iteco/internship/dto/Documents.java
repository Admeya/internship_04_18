package ru.iteco.internship.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Таблица документов
 * Created by ie.bykova on 09.04.2018.
 */
public class Documents {
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

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUserFio() {
        return userFio;
    }

    public void setUserFio(String userFio) {
        this.userFio = userFio;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getDocTypeName() {
        return docTypeName;
    }

    public void setDocTypeName(String docTypeName) {
        this.docTypeName = docTypeName;
    }
}
