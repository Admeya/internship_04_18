package ru.iteco.internship;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import ru.iteco.internship.database.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *  Проливка миграций
 */
@Slf4j
public class RunLiquibase {
    private static ConnectionManager connectionManager = ConnectionManager.getInstance();

    @Test
    public void Test(){
        Liquibase liquibase;
        try (Connection c = connectionManager.getConnection()) {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(c));
            liquibase = new Liquibase("src\\main\\resources\\liquibase\\changelog-master.xml", new FileSystemResourceAccessor(), database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (SQLException e) {
            log.error("Исключение при выполнении SQL-запроса", e);
        } catch (DatabaseException e) {
            log.error("Исключение БД ", e);
        } catch (LiquibaseException e) {
            log.error("Исключение при выполнении миграции", e);
        }
    }
}
