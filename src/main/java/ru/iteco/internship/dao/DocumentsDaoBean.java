package ru.iteco.internship.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.iteco.internship.database.ConnectionManager;
import ru.iteco.internship.dto.Documents;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ru.iteco.internship.database.GlobalConstants.*;


/**
 * DAO для работы с документами
 * Created by ie.bykova on 09.04.2018.
 */
@Component
@Slf4j
public class DocumentsDaoBean implements DocumentsDao {
    private static ConnectionManager connectionManager = ConnectionManager.getInstance();

    @Override
    public List<Documents> getFioAddress(){
        List<Documents> documents = new ArrayList<>();
        try(Connection connection = connectionManager.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_FIO_ADDRESS);

            while (rs.next()){
                Documents doc = new Documents();
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
    public List<Documents> getFioMaxSum() {
        List<Documents> documents = new ArrayList<>();
        try(Connection connection = connectionManager.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_FIO_MAX_SUM);

            while (rs.next()){
                Documents doc = new Documents();
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
    public List<Documents> getCountDocTypes() {
        List<Documents> documents = new ArrayList<>();
        try(Connection connection = connectionManager.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_COUNT_TYPES);

            while (rs.next()){
                Documents doc = new Documents();
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
