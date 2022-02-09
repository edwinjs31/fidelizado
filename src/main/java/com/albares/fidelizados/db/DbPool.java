package com.albares.fidelizados.db;

import com.albares.fidelizados.utils.Parameters;
import org.apache.commons.dbcp2.BasicDataSource;

public class DbPool {
    
    private final BasicDataSource dataSource;
    private static DbPool instance = null;

    private DbPool(String user, String pass, String url){
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername(user);
        dataSource.setPassword(pass);
        dataSource.setUrl(url);    
        dataSource.setMaxIdle(10);     
        dataSource.setMaxTotal(100);    //100 es el limite del servidor, dejamos un 10% de margen de seguridad
    }
    
    public BasicDataSource getDataSource(){
        return dataSource;
    }
    
    public static DbPool getInstance(){
        if (instance==null){
            instance = new DbPool(Parameters.DB_USER,Parameters.DB_PASS,Parameters.DB_URL);
        }
        return instance;
    }
}