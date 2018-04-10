package ru.iteco.internship.enums;

/**
 * Типы договоров
 *
 * Created by ie.bykova on 10.04.2018.
 */
public enum DocumentTypes {
    RENT(0L, "Договор аренды"),
    SERVICE(1L, "Договор оказания услуг");

    private long id;
    private String value;

    DocumentTypes(long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }
    public String getValue() {
        return value;
    }
}
