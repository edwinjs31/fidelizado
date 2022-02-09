package com.albares.fidelizados.utils;

public final class Parameters {

    public Parameters() {
    }

    public static final String PROJECT_NAME = "Fidelizados";

    public static final String DB_URL = "jdbc:postgresql://localhost:5432/fidelizados_db";
    public static final String DB_USER = "fidelizados_user";
    public static final String DB_PASS = Secrets.DB_PASS;

    public static final int APP_USER = 0;
    public static final int APP_BUSINESS = 1;

    public static int attempts = 3;

    //public static final String BASEPATH = "/opt/tomcat/webapps/images/";
    public static final String BASE_PATH = "C:/Users/edwin/Documents/NetBeansProjects/8-WEB-SERVICES/fidelizados/target/fidelizados-1.0/";
    public static final String RELATIVE_PATH = "images/";
    public static final String SERVER_URL = "http://localhost:8084/fidelizados/";

}
