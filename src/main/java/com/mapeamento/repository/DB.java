package com.mapeamento.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class DB {

    private static SessionFactory factory;

    private static void createFactory(){
        if (factory != null) {
            return;
        }
        factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
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
