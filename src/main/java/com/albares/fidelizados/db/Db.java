package com.albares.fidelizados.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Db {
 
    private Connection myconnection = null;
    public List<PreparedStatement> psList = new ArrayList();
    public List<ResultSet> rsList = new ArrayList();
    public Db() {  
        super();
    } 
        
    
    public void connect() throws SQLException{
        myconnection = DbPool.getInstance().getDataSource().getConnection();
    }
    

    public void disconnect() throws SQLException{
        try {
            for(ResultSet rs:this.rsList){
                if (rs != null) rs.close();
            }
            this.rsList.clear();
            this.rsList=null;
            for(PreparedStatement ps:this.psList){
                if (ps != null) ps.close();
            }
            this.psList.clear();
            this.psList=null;
        }finally{
            if (this.myconnection != null) this.myconnection.close();
        }
    }

    public Connection getMyConnection() {
        return this.myconnection;
    }
    
    public PreparedStatement prepareStatement(String sql) throws SQLException{
        //Método para tener en una lista todas las prepareStatement hechas con esta myDb y cerrarlas antes de hacer connection close
        PreparedStatement ps = this.myconnection.prepareStatement(sql);
        
        this.psList.add(ps);
        
        return ps;
    }
    
    public ResultSet executeQuery(PreparedStatement ps) throws SQLException{
        //Método para tener en una lista todos los ResultSet hechos con esta myDb y cerrarlas antes de hacer connection close
        ResultSet rs = ps.executeQuery();

        this.rsList.add(rs);
        
        return rs;
    }
    
    
    public void executUpdate(PreparedStatement ps) throws SQLException{
        ps.executeUpdate();
    }
    
}