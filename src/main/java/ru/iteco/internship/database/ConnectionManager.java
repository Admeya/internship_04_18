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
public class ConnectionManager{

    private static ConnectionManager connection;
    private String url;
    private String user;
    private String pass;
    private String driver;

    private ConnectionManager() {
        FileInputStream fis = null;
        Properties property = new Properties();
        String path = "src/main/resources/config.properties";
        try {
            fis = new FileInputStream(path);
            property.load(fis);
            this.driver = property.getProperty("driver");
            this.url = property.getProperty("url");
            this.user = property.getProperty("username");
            this.pass = property.getProperty("password");
            Class.forName(driver);
        } catch (FileNotFoundException e) {
            log.error("Конфигурационный файл " + path + " не найден", e);
        } catch (IOException e) {
            log.error("Невозможно прочитать данные из файла " + path, e);
        } catch (ClassNotFoundException e) {
            log.error("Не найден класс " + driver, e);
        }
    }

    public synchronized static ConnectionManager getInstance() {
        if (connection == null) {
            connection = new ConnectionManager();
        }
        return connection;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection con = DriverManager.getConnection(url, user, pass);
        return con;
    }
}
