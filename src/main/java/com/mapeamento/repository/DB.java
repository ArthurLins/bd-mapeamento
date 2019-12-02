package com.mapeamento.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class DB {

    private static class ConnectionData {
        static String user;
        static String pass;
        static String url;
    }

    private static void loadHerokuVars() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("DB_PASS") != null
                && processBuilder.environment().get("DB_USER") != null
                && processBuilder.environment().get("DB_URL") != null) {
            ConnectionData.url = processBuilder.environment().get("DB_URL");
            ConnectionData.pass = processBuilder.environment().get("DB_PASS");
            ConnectionData.user = processBuilder.environment().get("DB_USER");
            return;
        }
        ConnectionData.url = "jdbc:mysql://localhost:3306/bd3";
        ConnectionData.pass = "";
        ConnectionData.user = "root";
    }

    private static SessionFactory factory;

    private static void createFactory(){
        if (factory != null) {
            return;
        }
        loadHerokuVars();
        Configuration hConfig = new Configuration().configure("hibernate.cfg.xml");
        Properties cfg = hConfig.getProperties();
        cfg.put(Environment.USER, ConnectionData.user);
        cfg.put(Environment.PASS, ConnectionData.pass);
        cfg.put(Environment.URL, ConnectionData.url);

        factory = hConfig.buildSessionFactory();


    }

    public static void init(){
        if (factory == null){
            createFactory();
        }
    }

    public static Session getSession(){
        if (factory == null){
            createFactory();
        }
        return factory.openSession();
    }


}
