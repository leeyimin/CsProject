
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
            String password = "65644157";
            
            //Create a connect object (via getConnection)
            conn = DriverManager.getConnection(url+dbName,userName,password);
            System.out.println("Connected to the database");
        } //to be updated
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

