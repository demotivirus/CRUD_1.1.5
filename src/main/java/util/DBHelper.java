package util;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static DBHelper instatnce;

    public static DBHelper getInstatnce(){
        if (instatnce == null)
            return instatnce = new DBHelper();
        return instatnce;
    }

    private DBHelper(){}

    //JDBC
    private static Connection connection;

    //Hibernate
    private static SessionFactory sessionFactory;

    //JDBC
    public static Connection getConnection(){
        if (connection == null)
            connection = createJdbcConnection();
        return connection;
    }

    //Hibernate
    public static SessionFactory getConfiguration() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    //JDBC
    private static Connection createJdbcConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            //DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://"). //db type
                    append("localhost:"). //host name
                    append("3306/"). //port
                    append("crud_service?"). //db name
                    append("user=root&"). //login
                    append("password=root&"). //password
                    append("useLegacyDatetimeCode=false&").
                    append("serverTimezone=UTC&characterEncoding=utf-8");

            Connection newConnection = DriverManager.getConnection(url.toString());
            System.out.println(url.toString());
            return newConnection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    //Hibernate
    private static SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        System.out.println("Hibernate Java Config serviceRegistry created");
        return configuration.buildSessionFactory(serviceRegistry);
    }

    //Hibernate
    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/crud_service?serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");

        return configuration;
    }
}