package dao;

import model.User;
import org.hibernate.Session;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {
    private String daoType = null;
    private Session session;

    public UserDaoFactory(){
        if (daoType == null)
            readConfig();
        System.out.println("Config type is " + daoType);
    }

    public UserDao getUserDao(){
        if (daoType.equalsIgnoreCase("Hibernate"))
            return new UserHibernateDao(session);
        if (daoType.equalsIgnoreCase("JDBC"))
            return new UserJdbcDao();
        else{
            System.out.println("Error. Read standard config");
            return new UserJdbcDao();
        }
    }

    private void readConfig(){
        Properties properties = new Properties();

        try(InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties")){
            properties.load(is);

            daoType = properties.getProperty("DaoType");
            System.out.println("Config file read successful");
        } catch (IOException e) {
            System.out.println("Config read don't success");
            e.printStackTrace();
        }
    }
}