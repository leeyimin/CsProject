
import java.io.*;
import java.sql.ResultSet;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer
 */
public class Controller extends Observable {

    private Model m;
    public final static int RIE = 0;
    
    String updateStr;
    
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1F142A4E-D39E-0BD4-1AFB-745615ACC24F]
    // </editor-fold> 
    public Controller () {
        m=new Model();
    }
    
    
/*
    
    public void uploadPublication (File pFile) throws FileNotFoundException {
        boolean flag=m.updatePublication(pFile);
        if(flag==true){
            setChanged();
            updateStr = "Publication List (internal)";
            notifyObservers();
            System.out.println(this.countObservers());
        }
        else{
            
        }//to be updated
    }
    */
    public ArrayList<ArrayList<String> > parseCSV (File cFile) throws FileNotFoundException{
        //this assumes that every field is surrounded by " and separated by ,
        ArrayList< ArrayList<String> > res = new ArrayList< ArrayList<String> >();
        Scanner sc= new Scanner(cFile);
        StringTokenizer st;
        boolean open = false, pComm=false;
        int prev=0;
        ArrayList<String> list;
        while(sc.hasNextLine()){
            String line=sc.nextLine();
            list=new ArrayList<String>();
            for(int i=0;i<line.length();i++){
                if(open){
                    pComm=false;
                    switch (line.charAt(i)){
                        case '\"':{
                            open=false;
                            list.add(line.substring(prev,i));
                            break;
                        }
                    }
                            
                }
                else{
                    switch (line.charAt(i)){
                        case '\"':{
                            pComm=false;
                            open=true;
                            prev=i+1;
                            break;
                        }
                        case ',':{
                            if(pComm)list.add("");
                            pComm=true;
                            break;
                        }
                    }
                }
            }
            res.add(list);
            System.out.println("HERE");
        }
        
        return res;
    }//to be improved
    
    public void mergeAndUpdate (String tblname, File file) throws Exception {
      
        ArrayList<ArrayList<String> > addList = parseCSV(file);
        for(ArrayList<String> list: addList){
            ResultSet rs = m.hasConflict(tblname, list);
            if(rs!=null){
                String nstr="";
                for(String s:list) nstr+="\""+s+"\",";
                nstr=nstr.substring(0, nstr.length()-1);
                String ostr ="";
                for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
                    ostr+="\""+rs.getString(i)+"\",";
                ostr=ostr.substring(0, ostr.length()-1);
                int n =JOptionPane.showConfirmDialog(null, "Do you want to replace\n"+ostr
                        +"\nwith\n"+nstr, "Overwrite current record?", JOptionPane.YES_NO_OPTION);
                if(n==JOptionPane.YES_OPTION)   m.addRecord(tblname,list);
            }//show dialog for user to choose
            else{
                m.addRecord(tblname,list);
            }
            
        }
        //show dialog for successful update?
        JOptionPane.showMessageDialog(null, "The records have been updated.");
    }
    
    public ResultSet getResultSet(String s) {
        return m.getResultSet(s);
    }

    void updateIndividualRecord(int table, int id, String colName, String newValue) {
        String tableName = "";
        
        switch (table) {
            case RIE: 
                tableName = "RIERECORDS";
                break;
            default:
                //is an error
                //TODO add handling code in future
        }
        
        m.updateIndividualRecord(tableName, id, colName, newValue);
    }

    void deleteRecord(int table, int id) {
        String tableName = "";
        switch (table) {
            case RIE: 
                tableName = "RIERECORDS";
                break;
            default:
                //is an error
                //TODO add handling code in future
        }
        
        m.deleteRecord(tableName, id);
    }
    
}
