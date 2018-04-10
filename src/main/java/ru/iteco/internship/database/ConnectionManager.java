package ru.iteco.internship.database;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Менеджер соединений
 */
@Slf4j
public class ConnectionManager {
    private String url;
    private String user;
    private String pass;
    private String driver;

    private enum PoolSingleton {
        INSTANCE;
        private static final ConnectionManager singleton = new ConnectionManager();
        private ConnectionManager getSingleton() {
            return singleton;
        }
    }

    public static ConnectionManager getInstance() {
        return PoolSingleton.INSTANCE.getSingleton();
    }

    ConnectionManager()  {
        FileInputStream fis;
        Properties property = new Properties();
        String path = "src/main/resources/config.properties";
        try {
            fis = new FileInputStream(path);
            property.load(fis);
            driver = property.getProperty("driver");
            url = property.getProperty("url");
            user = property.getProperty("username");
            pass = property.getProperty("password");
            Class.forName(driver);
        } catch (FileNotFoundException e) {
            log.error("Конфигурационный файл " + path + " не найден ", e);
        } catch (IOException e) {
            log.error("Невозможно прочитать данные из файла " + path, e);
        } catch (ClassNotFoundException e) {
            log.error("Не найден класс " + driver, e);
        }
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
