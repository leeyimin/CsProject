
import java.io.*;
import java.sql.ResultSet;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer
 */
public class Controller extends Observable{

    private Model m;
    
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
        ArrayList<ArrayList<String> > res = new ArrayList<>();
        Scanner sc= new Scanner(cFile);
        StringTokenizer st;
        ArrayList<String> list;
        while(sc.hasNextLine()){
            st=new StringTokenizer(sc.nextLine(),"\"");
            list= new ArrayList<>();
            while(st.hasMoreTokens()){
                String str =st.nextToken();
                if(str.equals(",")) continue;
                list.add(str);
            }
            //System.out.println("l: "+list.size());
            res.add(list);
        }
        //System.out.println("r: "+res.size());
        
        return res;
    }//to be improved
    
    public void mergeAndUpdate (String tblname, File file) throws Exception {
      
        ArrayList<ArrayList<String> > addList = parseCSV(file);
        for(ArrayList<String> list: addList){
            ResultSet rs = m.hasConflict(tblname, list);
            if(rs!=null){
                
            }//show dialog for user to choose
            m.addRecord(tblname,list);
        }
        //show dialog for successful update?
    }
    
    public ResultSet getResultSet(String s) {
        return m.getResultSet(s);
    }
    
}
