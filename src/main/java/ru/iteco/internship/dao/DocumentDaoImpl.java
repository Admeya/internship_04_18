package ru.iteco.internship.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.iteco.internship.database.ConnectionManager;
import ru.iteco.internship.dto.Document;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO для работы с документами
 * Created by ie.bykova on 09.04.2018.
 */
@Component
@Slf4j
public class DocumentDaoImpl implements DocumentDao {
    private static ConnectionManager connectionManager = ConnectionManager.getInstance();

    private static final String SELECT_BY_ID =
            "SELECT doc.document_id, doc.fio, doc.doc_name, types.doc_type_name, " +
                    "doc.date_start, doc.date_end, doc.amount, rent.address " +
                    "FROM v_actual_documents doc " +
                    "JOIN persons p USING(person_id) " +
                    "JOIN doc_types types USING(doc_type_id) " +
                    "LEFT JOIN documents_rent rent " +
                    "ON doc.document_id = rent.document_id AND doc.doc_type_id = 1 " +
                    "WHERE doc.document_id = ?";

    private static final String SELECT_FIO_ADDRESS =
            "SELECT  doc.document_id, doc.fio, doc.doc_name, rent.address " +
                    "FROM v_actual_documents doc " +
                    "JOIN documents_rent rent " +
                    "ON doc.document_id = rent.document_id AND doc.doc_type_id = 1";

    private static final String SELECT_FIO_MAX_SUM =
            "SELECT  doc.fio, sum(doc.amount) amount " +
                    "FROM  v_actual_documents doc " +
                    "JOIN persons p USING(person_id) " +
                    "GROUP BY p.person_id, doc.fio " +
                    "ORDER BY amount DESC " +
                    "LIMIT 1";

    private static final String SELECT_COUNT_TYPES =
            "SELECT count(document_id) cnt, doc_types.doc_type_name " +
                    "FROM v_actual_documents doc " +
                    "JOIN doc_types USING(doc_type_id) " +
                    "GROUP BY doc_types.doc_type_id, doc_types.doc_type_name " +
                    "ORDER BY cnt";

    @Override
    public Document getById(Long documentId) {
        Document doc = null;
        if (documentId == null) return doc;

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, documentId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                doc = new Document();
                doc.setUserFio(rs.getString("fio"));
                doc.setDocumentId(rs.getLong("document_id"));
                doc.setAddress(rs.getString("address"));
                doc.setAmount(rs.getBigDecimal("amount"));
                doc.setDateEnd(rs.getDate("date_end"));
                doc.setDateStart(rs.getDate("date_start"));
                doc.setDocName(rs.getString("doc_name"));
                doc.setDocTypeName(rs.getString("doc_type_name"));
            }
        } catch (SQLException e) {
            log.error("Исключение при извлечении информации из БД " + e.getMessage());
        }
        return doc;
    }

    @Override
    public List<Document> getFioAndAddress() {
        List<Document> documents = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SELECT_FIO_ADDRESS)) {
            while (rs.next()) {
                Document doc = new Document();
                doc.setDocumentId(rs.getLong("document_id"));
                doc.setUserFio(rs.getString("fio"));
                doc.setDocName(rs.getString("doc_name"));
                doc.setAddress(rs.getString("address"));
                documents.add(doc);
            }
        } catch (SQLException e) {
            log.error("Исключение при извлечении информации из БД " + e.getMessage());
        }
        return documents;
    }

    @Override
    public List<Document> getFioMaxSum() {
        List<Document> documents = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SELECT_FIO_MAX_SUM)) {
            while (rs.next()) {
                Document doc = new Document();
                doc.setUserFio(rs.getString("fio"));
                doc.setAmount(rs.getBigDecimal("amount"));
                documents.add(doc);
            }
        } catch (SQLException e) {
            log.error("Исключение при извлечении информации из БД " + e.getMessage());
        }
        return documents;
    }

    @Override
    public List<Document> getCountDocTypes() {
        List<Document> documents = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SELECT_COUNT_TYPES)) {
            while (rs.next()) {
                Document doc = new Document();
                doc.setCount(rs.getLong("cnt"));
                doc.setDocTypeName(rs.getString("doc_type_name"));
                documents.add(doc);
            }
        } catch (SQLException e) {
            log.error("Исключение при извлечении информации из БД " + e.getMessage());
        }
        return documents;
    }
}
