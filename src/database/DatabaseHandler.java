/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Jayakuru
 */
public class DatabaseHandler {
    private static DatabaseHandler handler= null;
    private static final String BD_URL ="jdbc:derby:HospitalDB;create=true;";
    public static Connection conn=null;
    private static Statement stmt=null;
    
    private DatabaseHandler(){
        
        createConnection();
        setupEmployeeTable();
    }
    
    public static DatabaseHandler getInstance(){
        
        if(handler==null){
            handler= new DatabaseHandler();
        }
        return handler;
        
    }
    
    
    void createConnection(){
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn=DriverManager.getConnection(BD_URL);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    void setupEmployeeTable(){
        String TABLE_NAME = "EMPLOYEE";
        
        try{
            stmt = conn.createStatement();
            
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            if (tables.next()){
                System.out.println("Table" + TABLE_NAME +" READY");
                
            }
            else{
                stmt.execute("CREATE TABLE" + TABLE_NAME+"("
                        +"      id varchar(200) primary key,"
                        + "     name varchar(200),"
                        + "     position varchar(200),"
                        + "     gender varchar(10),"
                        + "     isAvail boolean default true"
                +" )");
                        
            }
        }catch(SQLException e){
            System.err.println(e.getMessage()+"....setupdatabase ");
        }finally
                {
                }      
    }
    
    public ResultSet execQuery(String query){
        ResultSet result;
        
        try{
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
           
        }catch(SQLException ex){
            System.out.println("Exception at execQuery:datHadler"+ ex.getLocalizedMessage());
            return null;
        }finally{
    }
    return result;
    }
    
    public boolean execAction(String qu)
    {
        try{
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error"+ex.getMessage(),"Error occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execQuery:datahandler"+ ex.getLocalizedMessage());
            return false;
        }finally{
        }
    }
    
}
