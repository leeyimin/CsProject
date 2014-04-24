
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.C63ACCD1-B180-39EE-C54B-B45A5A53D44D]
// </editor-fold> 
public class Model {
    private Connection conn;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.959616C0-9A41-1A04-F0C7-62A382AC875A]
    // </editor-fold> 
    public Model () {
        connect();
    }
/*
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.410FB467-F179-4E58-95DD-9C8641A0320F]
    // </editor-fold> 
    public boolean updatePublication (ArrayList<ArrayList<String>> list){
        try {
            Statement stmt = conn.createStatement();
            String delQuery = "DELETE FROM publication";
            stmt.executeUpdate(delQuery);
            
            int count =0, icount=1;
            String addQuery ="INSERT INTO publication() VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(addQuery);
            
            for(ArrayList<String> r: list){
                icount=1;
                ps.setInt(icount, count);
                count++;
                icount++;
                for(String str:r){
                    ps.setString(icount, str);
                    icount++;
                }
                ps.executeUpdate();
            }
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            return false;
        }
    }
    */
    public boolean updatePublication (File pFile){
        try {
            Statement stmt = conn.createStatement();
            String delQuery = "DELETE FROM publication";
            stmt.executeUpdate(delQuery);
            
            //System.out.println(pFile.getAbsolutePath());
            String loadQuery= "LOAD DATA LOCAL INFILE \'" + pFile.getAbsolutePath().replace("\\", "\\\\")
                    + "\' INTO TABLE publication FIELDS TERMINATED BY \',\'" 
                    +" OPTIONALLY ENCLOSED BY \'\"\'"
                    +" LINES TERMINATED BY \'\\n\'";
            stmt.executeUpdate(loadQuery);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "JDASdb";
            String userName = "root";
            String password = "";//"Monster Hunter 3 Ultimate";
            
            //Create a connect object (via getConnection)
            conn = DriverManager.getConnection(url+dbName,userName,password);
            System.out.println("Connected to the database");
        } //to be updated
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 /*  
    public boolean mergeAndUpdateRIERecords(ArrayList<ArrayList<String>> list) {
        try {
            Statement stmt = conn.createStatement();
            
            int count = 0, icount = 1;
                //TODO replace table name, insert set of 13 values???
            String addQuery ="INSERT INTO _____() VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(addQuery);
            
            for(ArrayList<String> r: list){
                icount = 1;
                ps.setInt(icount, count);
                count++;
                icount++;
                for(String str:r){
                    ps.setString(icount, str);
                    icount++;
                }
                ps.executeUpdate();
            }
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            //TODO propagate this up?
        }
        finally{
            return false;
        }
        
        
        
        
    }*/
    
    public ResultSet getResultSet(String s) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( s);
            
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; // TODO look at this later
    }

    public ResultSet hasConflict(String tblname, ArrayList<String> list) throws SQLException {
        Statement stmt=conn.createStatement();
        if(tblname.equals("rierecords")){
            ResultSet rs =stmt.executeQuery("SELECT * FROM " +tblname+
                    " WHERE id = "+list.get(0));
            if(!rs.isBeforeFirst())return null;
            boolean flag=false;
            for(int i=6;i<=13;i++){
                if(!rs.getString(i).equals(list.get(i-1)))flag=true;
            }
            if(flag)return rs;
            
        }
        else if(tblname.equals("publication")){
            ResultSet rs =stmt.executeQuery("SELECT * FROM "+tblname
            +" WHERE title=\""+list.get(0)+"\"");
            
            if(!rs.isBeforeFirst())return null;
            boolean flag=false;
            for(int i=1;i<=3;i++){
                if(!rs.getString(i).equals(list.get(i-1)))flag=true;
            }
            if(flag)return rs;
        }
        return null;
    }

    public void addRecord(String tblname, ArrayList<String> list) throws SQLException {
        Statement stmt = conn.createStatement();
        
        if(tblname.equals("rierecords")){
            stmt.executeQuery("DELETE FROM "+tblname+" WHERE id="+list.get(0));
            String addQuery ="INSERT INTO "+tblname+"() VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(addQuery);
            int count =1;
            for(String str:list){
                ps.setString(count, str);
                count++;
            }
            ps.executeUpdate();
        }
        else if(tblname.equals("publication")){
            stmt.executeQuery("DELETE FROM "+tblname+" WHERE title=\""+list.get(0)+"\"");
            String addQuery ="INSERT INTO publication() VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(addQuery);
            int count =1;
            for(String str:list){
                ps.setString(count, str);
                count++;
            }
            ps.executeUpdate();
        }
        
    }

    void updateIndividualRecord(String tableName, int id, String colName, String newValue) {
        try {
            Statement stmt = conn.createStatement();
            String updateString = "UPDATE " + tableName + " SET " + colName + " = \'" + newValue + "\' WHERE ID = " + id + ";";
            System.out.println(updateString);
            
            stmt.executeUpdate(updateString);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void deleteRecord(String tableName, int id) {
        try {
            Statement stmt = conn.createStatement();
            String updateString = "DELETE FROM " + tableName + " WHERE ID = " + id + ";";
            System.out.println(updateString);
            
            stmt.executeUpdate(updateString);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

