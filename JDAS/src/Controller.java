
import java.io.*;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer
 */
public class Controller {

    private Model m;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1F142A4E-D39E-0BD4-1AFB-745615ACC24F]
    // </editor-fold> 
    public Controller () {
        m=new Model();
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B60F2BDE-46D1-8EA8-6203-F46C3D0AB2D0]
    // </editor-fold> 
    public void uploadPublication (File pFile) throws FileNotFoundException {
        ArrayList<ArrayList<String> > list = parseCSV(pFile);
        boolean flag=m.updatePublication(list);
        if(flag==true){
            
        }
        else{
            
        }//to be updated
    }
    
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
    }
    
    public void mergeAndUpdateRIERecords (File file) {
        
    }
    
}
