package ru.iteco.internship.database;

/**
 * Created by ie.bykova on 09.04.2018.
 */
public class GlobalConstants {
    public static final String SELECT_FIO_ADDRESS =
            "SELECT  P.LAST_NAME||' '|| P.FIRST_NAME||' '|| P.MIDDLE_NAME as FIO, " +
            "        DOC.DOC_NAME, " +
            "        RENT.ADDRESS " +
            "FROM v_actual_documents DOC" +
            "    JOIN DOCUMENTS_RENT RENT" +
            "        ON DOC.DOCUMENT_ID = RENT.DOCUMENT_ID AND DOC.DOC_TYPE_ID = 1" +
            "    JOIN PERSONS P USING(PERSON_ID)";

    public static final String SELECT_FIO_MAX_SUM =
            "SELECT  P.LAST_NAME||' '|| P.FIRST_NAME||' '|| P.MIDDLE_NAME as FIO, SUM(DOC.AMOUNT) AMOUNT" +
            "FROM  v_actual_documents DOC" +
            "    JOIN PERSONS P USING(PERSON_ID) " +
            "GROUP BY P.PERSON_ID, P.LAST_NAME, P.FIRST_NAME, P.MIDDLE_NAME " +
            "ORDER BY AMOUNT DESC " +
            "LIMIT 1";

    public static final String SELECT_COUNT_TYPES =
            "SELECT COUNT(DOCUMENT_ID) CNT, DOC_TYPES.DOC_TYPE_NAME" +
            "FROM v_actual_documents DOC" +
            "    JOIN DOC_TYPES" +
            "      USING(DOC_TYPE_ID) " +
            "GROUP BY DOC_TYPES.DOC_TYPE_ID, DOC_TYPES.DOC_TYPE_NAME " +
            "ORDER BY CNT";
}
