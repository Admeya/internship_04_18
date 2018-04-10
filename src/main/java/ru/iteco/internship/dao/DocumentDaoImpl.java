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
            "select doc.document_id, doc.fio, doc.doc_name, types.doc_type_name, " +
                    "doc.date_start, doc.date_end, doc.amount, rent.address " +
            "from v_actual_documents doc " +
            "join persons p using(person_id) " +
            "join doc_types types using(doc_type_id) " +
            "left join documents_rent rent " +
            "on doc.document_id = rent.document_id and doc.doc_type_id = 1 " +
            "where doc.document_id = ?";

    private static final String SELECT_FIO_ADDRESS =
            "select  doc.fio, doc.doc_name, rent.address " +
            "from v_actual_documents doc " +
            "join documents_rent rent " +
            "on doc.document_id = rent.document_id and doc.doc_type_id = 1";

    private static final String SELECT_FIO_MAX_SUM =
            "select doc.fio, sum(doc.amount) amount " +
            "from  v_actual_documents doc " +
            "join persons p using(person_id) " +
            "group by p.person_id, doc.fio " +
            "order by amount desc " +
            "limit 1";

    private static final String SELECT_COUNT_TYPES =
            "select count(document_id) cnt, doc_types.doc_type_name " +
            "from v_actual_documents doc " +
            "join doc_types using(doc_type_id) " +
            "group by doc_types.doc_type_id, doc_types.doc_type_name " +
            "order by cnt";

    @Override
    public Document getById(Long documentId){
        Document doc = null;

        if (documentId != null) {
            try (Connection connection = connectionManager.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
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
            } catch (ClassNotFoundException e) {
                log.error("Класс не найден " + e.getMessage());
            }
        }
        return doc;
    }

    @Override
    public List<Document> getFioAddress() {
        List<Document> documents = new ArrayList<>();
        try(Connection connection = connectionManager.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_FIO_ADDRESS);

            while (rs.next()){
                Document doc = new Document();
                doc.setUserFio(rs.getString("fio"));
                doc.setDocName(rs.getString("doc_name"));
                doc.setAddress(rs.getString("address"));
                documents.add(doc);
            }
        } catch (SQLException e) {
            log.error("Исключение при извлечении информации из БД " + e.getMessage());
        } catch (ClassNotFoundException e) {
            log.error("Класс не найден " + e.getMessage());
        }
        return documents;
    }

    @Override
    public List<Document> getFioMaxSum() {
        List<Document> documents = new ArrayList<>();
        try(Connection connection = connectionManager.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_FIO_MAX_SUM);

            while (rs.next()){
                Document doc = new Document();
                doc.setUserFio(rs.getString("fio"));
                doc.setAmount(rs.getBigDecimal("amount"));
                documents.add(doc);
            }
        } catch (SQLException e) {
            log.error("Исключение при извлечении информации из БД " + e.getMessage());
        } catch (ClassNotFoundException e) {
            log.error("Класс не найден " + e.getMessage());
        }
        return documents;
    }

    @Override
    public List<Document> getCountDocTypes() {
        List<Document> documents = new ArrayList<>();
        try(Connection connection = connectionManager.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_COUNT_TYPES);

            while (rs.next()){
                Document doc = new Document();
                doc.setCount(rs.getLong("cnt"));
                doc.setDocTypeName(rs.getString("doc_type_name"));
                documents.add(doc);
            }
        } catch (SQLException e) {
            log.error("Исключение при извлечении информации из БД " + e.getMessage());
        } catch (ClassNotFoundException e) {
            log.error("Класс не найден " + e.getMessage());
        }
        return documents;
    }
}
