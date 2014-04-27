
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.C63ACCD1-B180-39EE-C54B-B45A5A53D44D]
// </editor-fold> 
public class Model extends Observable {
    private Connection conn;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.959616C0-9A41-1A04-F0C7-62A382AC875A]
    // </editor-fold> 
    public Model () {
        connect();
    }
   

    public void upload(String tblname, File pFile) throws SQLException{
        Statement stmt = conn.createStatement();
        String delQuery = "DELETE FROM "+tblname;
        stmt.executeUpdate(delQuery);

        String loadQuery= "LOAD DATA LOCAL INFILE \'" + pFile.getAbsolutePath().replace("\\", "\\\\")
                + "\' INTO TABLE "+tblname
                + " FIELDS TERMINATED BY \',\'" 
                +" OPTIONALLY ENCLOSED BY \'\"\'"
                +" LINES TERMINATED BY \'\\n\'";
        stmt.executeUpdate(loadQuery);
        setChanged();
        notifyObservers();
    }

    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "JDASdb";
            String userName = "root";
            String password = "";//add password here
            
            //Create a connect object (via getConnection)
            conn = DriverManager.getConnection(url+dbName,userName,password);
            System.out.println("Connected to the database");
        } //to be updated
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    
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
            rs.next();
            for(int i=6;i<=13;i++){
                String cStr;
                cStr=rs.getString(i);
                if(cStr==null)cStr="";
                if(!cStr.equals(list.get(i-1)))flag=true;
            }
            if(flag)return rs;
            
        }
        else if(tblname.equals("publication")){
            ResultSet rs =stmt.executeQuery("SELECT * FROM "+tblname
            +" WHERE title=\""+list.get(0)+"\"");
            
            if(!rs.isBeforeFirst())return null;
            boolean flag=false;
            rs.next();
            for(int i=1;i<=3;i++){
                String cStr;
                cStr=rs.getString(i);
                if(cStr==null)cStr="";
                if(!cStr.equals(list.get(i-1)))flag=true;
            }
            if(flag)return rs;
        }
        return null;
    }

    public void addRecord(String tblname, ArrayList<String> list) throws SQLException, Exception {
        Statement stmt = conn.createStatement();
        
        if(tblname.equals("rierecords")){
            if(list.size()!=13)throw new Exception("File may not be correctly formatted");
            stmt.executeUpdate("DELETE FROM "+tblname+" WHERE id="+list.get(0));
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
            if(list.size()!=3)throw new Exception("File may not be correctly formatted");
            stmt.executeUpdate("DELETE FROM "+tblname+" WHERE title=\""+list.get(0)+"\"");
            String addQuery ="INSERT INTO publication() VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(addQuery);
            ps.setString(1, list.get(0));
            ps.setString(2, list.get(1));
            if(list.get(2).isEmpty())   ps.setNull(3, java.sql.Types.INTEGER);
            else    ps.setInt(3, Integer.parseInt(list.get(2)));
            ps.executeUpdate();
        }
        setChanged();
        notifyObservers();
        
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
        setChanged();
        notifyObservers();
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
        
        setChanged();
        notifyObservers();
    }

    public ResultSet checkPubAndRec() {
        String query= "Select distinct id from rierecords, publication where "
                + "category =14 and ((rierecords.desc1 like "
                +"concat('%',publication.title,'%') "
                + "and not(rierecords.desc1=publication.desc1 and "
                + "rierecords.year =publication.year) ) or "
                + "id not in (select id from rierecords rr, publication p where "
                + "rr.desc1 like concat('%',p.title,'%') ))";
        return getResultSet(query);
    }

}

